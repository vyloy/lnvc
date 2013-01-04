/*
 * @(#)ScreenRecorderMain.java  
 *
 * Copyright (c) 2011 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package org.monte.screenrecorder;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.monte.media.AudioFormatKeys.ChannelsKey;
import static org.monte.media.AudioFormatKeys.SampleRateKey;
import static org.monte.media.AudioFormatKeys.SampleSizeInBitsKey;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MIME_QUICKTIME;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.COMPRESSOR_NAME_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.HeightKey;
import static org.monte.media.VideoFormatKeys.QualityKey;
import static org.monte.media.VideoFormatKeys.WidthKey;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.MenuElement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.gui.JLabelHyperlinkHandler;
import org.monte.media.gui.Worker;
import org.monte.media.gui.datatransfer.DropFileTransferHandler;
import org.monte.media.image.Images;
import org.monte.media.math.Rational;

/**
 * ScreenRecorderMain.
 *
 * @author Werner Randelshofer
 * @version $Id: ScreenRecorderCompactMain.java 285 2012-11-21 21:04:07Z werner
 * $
 */
public class ScreenRecorderCompactMain extends javax.swing.JFrame {

    private void setSelectedIndex(ButtonGroup group, int index) {
        Enumeration<AbstractButton> e = group.getElements();
        AbstractButton b = null;
        for (int i = 0; i <= index; i++) {
            if (e.hasMoreElements()) {
                b = e.nextElement();
            }
        }
        group.setSelected(b.getModel(), true);
    }

    private int getSelectedIndex(ButtonGroup group) {
        int index = 0;
        for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements();) {
            AbstractButton b = e.nextElement();
            if (b.isSelected()) {
                break;
            }
            index++;
        }
        return index;
    }

    private AbstractButton getSelectedItem(ButtonGroup group) {
        for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements();) {
            AbstractButton b = e.nextElement();
            if (b.isSelected()) {
                return b;
            }
        }
        return null;
    }

    private void buildAudioSourceMenu() {
        Preferences prefs = Preferences.userNodeForPackage(ScreenRecorderCompactMain.class);

        audioSource = prefs.getInt("ScreenRecording.audioSource", 0); //$NON-NLS-1$


        Vector<AudioSourceItem> items = getAudioSources();
        audioSource = max(0, min(items.size() - 1, audioSource));
        System.out.println("audioSource:" + audioSource); //$NON-NLS-1$
        int i = 0;
        for (AudioSourceItem item : items) {
            JRadioButtonMenuItem mi = new JRadioButtonMenuItem(item.title);
            mi.putClientProperty("AudioSourceItem", item); //$NON-NLS-1$
            audioSourceGroup.add(mi);
            if (i == audioSource) {
                mi.setSelected(true);
            }
            audioMenu.insert(mi, i);
            i++;
        }
    }

    private class Handler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            ScreenRecorder r = screenRecorder;
            if (r != null && r.getState() == ScreenRecorder.State.FAILED) {
                recordingFailed();
            }
        }
    }
    private Handler handler = new Handler();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH.mm.ss"); //$NON-NLS-1$
    private volatile Worker recorder;
    private ScreenRecorder screenRecorder;
    private int depth;
//    private int format;
    //private int encoding;
    private int cursor;
    private int audioRate;
    private int channels;
    private int area;
    private int fps;
    private int audioSource;
    private File movieFolder;
    private Rectangle customAreaRect;
    private Timer timer;
    private String infoLabelText;

    private static class AudioItem {

        private String title;
        private int sampleRate;
        private int bitsPerSample;

        public AudioItem(String title, int sampleRate, int bitsPerSample) {
            this.title = title;
            this.sampleRate = sampleRate;
            this.bitsPerSample = bitsPerSample;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    private static class AreaItem {

        private String title;
        /**
         * Area or null for entire screen.
         */
        private Dimension inputDimension;
        /**
         * null if same value as input dimension.
         */
        private Dimension outputDimension;
        /**
         * SwingConstants.CENTER, .NORTH_WEST, SOUTH_WEST.
         */
        private int alignment;
        private Point location;

        public AreaItem(String title, Dimension dim, int alignment) {
            this(title, dim, null, alignment, new Point(0, 0));
        }

        public AreaItem(String title, Dimension inputDim, Dimension outputDim, int alignment, Point location) {
            this.title = title;
            this.inputDimension = inputDim;
            this.outputDimension = outputDim;
            this.alignment = alignment;
            this.location = location;
        }

        @Override
        public String toString() {
            return title;
        }

        public Rectangle getBounds(GraphicsConfiguration cfg) {
            Rectangle areaRect = null;
            if (inputDimension != null) {
                areaRect = new Rectangle(0, 0, inputDimension.width, inputDimension.height);
            }
            outputDimension = outputDimension;
            Rectangle screenBounds = cfg.getBounds();
            if (areaRect == null) {
                areaRect = (Rectangle) screenBounds.clone();
            }
            switch (alignment) {
                case SwingConstants.CENTER:
                    areaRect.x = screenBounds.x + (screenBounds.width - areaRect.width) / 2;
                    areaRect.y = screenBounds.y + (screenBounds.height - areaRect.height) / 2;
                    break;
                case SwingConstants.NORTH_WEST:
                    areaRect.x = screenBounds.x;
                    areaRect.y = screenBounds.y;
                    break;
                case SwingConstants.SOUTH_WEST:
                    areaRect.x = screenBounds.x;
                    areaRect.y = screenBounds.y + screenBounds.height - areaRect.height;
                    break;
                default:
                    break;
            }
            areaRect.translate(location.x, location.y);

            areaRect = areaRect.intersection(screenBounds);
            return areaRect;

        }
    }

    private static class AudioSourceItem {

        private String title;
        private Mixer.Info mixerInfo;
        private boolean isEnabled;

        public AudioSourceItem(String title, Mixer.Info mixerInfo) {
            this(title, mixerInfo, true);
        }

        public AudioSourceItem(String title, Mixer.Info mixerInfo, boolean isEnabled) {
            this.title = title;
            this.mixerInfo = mixerInfo;
            this.isEnabled = isEnabled;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    /**
     * Creates new form ScreenRecorderMain
     */
    public ScreenRecorderCompactMain() {
        setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics gr) {
                Graphics2D g = (Graphics2D) gr;
                g.setPaint(new LinearGradientPaint(0, 0, 0, getHeight(),
                        new float[]{0, 0.499f, 0.5f, 1.0f},
                        new Color[]{new Color(0x404040),
                            new Color(0x2f2f2f),
                            new Color(0x1e1e1e),
                            new Color(0x151515)}));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(1, 2, 2, 2));
        setMinimumSize(new Dimension(Math.max(240, getPreferredSize().width), getPreferredSize().height));
        initComponents();

        String version = ScreenRecorderCompactMain.class.getPackage().getImplementationVersion();
        if (version != null) {
            int p = version.indexOf(' ');
            setTitle(getTitle() + " " + version.substring(0, p == -1 ? version.length() : p)); //$NON-NLS-1$
        }

        final Preferences prefs = Preferences.userNodeForPackage(ScreenRecorderCompactMain.class);
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) { //$NON-NLS-1$ //$NON-NLS-2$
            movieFolder = new File(System.getProperty("user.home") + File.separator + "Videos"); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            movieFolder = new File(System.getProperty("user.home") + File.separator + "Movies"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        movieFolder = new File(prefs.get("ScreenRecorder.movieFolder", movieFolder.toString())); //$NON-NLS-1$

        Image img = Images.createImage(ScreenRecorderCompactMain.class, "images/Recorder.StartStop.png"); //$NON-NLS-1$
        BufferedImage[] imgs = Images.split(img, 4, true);
        startStopButton.setUI((ButtonUI) BasicButtonUI.createUI(startStopButton));
        startStopButton.setIcon(new ImageIcon(imgs[0]));
        startStopButton.setDisabledIcon(new ImageIcon(imgs[1]));
        startStopButton.setSelectedIcon(new ImageIcon(imgs[2]));
        startStopButton.setPressedIcon(new ImageIcon(imgs[3]));
        startStopButton.setText(null);
        startStopButton.setBorderPainted(false);
        startStopButton.setOpaque(false);
        startStopButton.setMargin(new Insets(0, 0, 0, 0));
        startStopButton.setBorder(new EmptyBorder(2, 2, 2, 2));
        img = Images.createImage(ScreenRecorderCompactMain.class, "images/Recorder.Options.png"); //$NON-NLS-1$
        imgs = Images.split(img, 2, true);
        optionsButton.setUI((ButtonUI) BasicButtonUI.createUI(startStopButton));
        optionsButton.setIcon(new ImageIcon(imgs[0]));
        optionsButton.setDisabledIcon(new ImageIcon(imgs[1]));
        optionsButton.setText(null);
        optionsButton.setBorderPainted(false);
        optionsButton.setOpaque(false);
        optionsButton.setMargin(new Insets(0, 0, 0, 0));
        optionsButton.setBorder(new EmptyBorder(2, 2, 2, 2));


        infoLabelText = infoLabel.getText();
        updateInfoLabel();
        /*
         infoLabel.setMinimumSize(new Dimension(Math.max(200,infoLabel.getPreferredSize().width),infoLabel.getPreferredSize().height));
         timeLabel.setMinimumSize(new Dimension(Math.max(200,timeLabel.getPreferredSize().width),timeLabel.getPreferredSize().height));
         */

        new JLabelHyperlinkHandler(infoLabel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File f = new File(new URI(e.getActionCommand()));
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    Desktop.getDesktop().open(f);
                } catch (URISyntaxException ex) {
                    System.err.println("ScreenRecorderMain bad href " + e.getActionCommand() + ", " + ex); //$NON-NLS-1$ //$NON-NLS-2$
                } catch (IOException ex) {
                    System.err.println("ScreenRecorderMain io exception: " + ex); //$NON-NLS-1$
                }
            }
        });
        infoLabel.setTransferHandler(new DropFileTransferHandler(JFileChooser.DIRECTORIES_ONLY, null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieFolder = new File(e.getActionCommand());
                prefs.put("ScreenRecorder.movieFolder", movieFolder.toString()); //$NON-NLS-1$
                updateInfoLabel();
            }
        }));


        depth = min(max(0, prefs.getInt("ScreenRecording.colorDepth", 3)), colorGroup.getButtonCount() - 1); //$NON-NLS-1$
        setSelectedIndex(colorGroup, depth);
        //format = min(max(0, prefs.getInt("ScreenRecording.format", 1)), formatGroup.getButtonCount() - 1); //$NON-NLS-1$
        //setSelectedIndex(formatGroup, format);
        //encoding = min(max(0, prefs.getInt("ScreenRecording.encoding", 0)), encodingChoice.getItemCount() - 1);
        //encodingChoice.setSelectedIndex(encoding);
        cursor = min(max(0, prefs.getInt("ScreenRecording.cursor", 1)), cursorGroup.getButtonCount() - 1); //$NON-NLS-1$
        setSelectedIndex(cursorGroup, cursor);

        fps = min(max(0, prefs.getInt("ScreenRecording.fps", 1)), fpsGroup.getButtonCount() - 1); //$NON-NLS-1$
        setSelectedIndex(fpsGroup, fps);


        audioRate = min(max(0, prefs.getInt("ScreenRecording.audioRate", 1)), audioRateGroup.getButtonCount() - 1); //$NON-NLS-1$
        setSelectedIndex(audioRateGroup, audioRate);

        channels = min(max(0, prefs.getInt("ScreenRecording.channels", 0)), channelsGroup.getButtonCount() - 1); //$NON-NLS-1$
        setSelectedIndex(channelsGroup, channels);

        Dimension customDim = new Dimension(prefs.getInt("ScreenRecording.customAreaWidth", 1024), //$NON-NLS-1$
                prefs.getInt("ScreenRecording.customAreaHeight", 768)); //$NON-NLS-1$
        Point customLoc = new Point(
                prefs.getInt("ScreenRecording.customAreaX", 100), //$NON-NLS-1$
                prefs.getInt("ScreenRecording.customAreaY", 100)); //$NON-NLS-1$

        customAreaRect = new Rectangle(customLoc.x, customLoc.y, customDim.width, customDim.height);
        area = min(max(0, prefs.getInt("ScreenRecording.area", 0)), areaGroup.getButtonCount() - 1); //$NON-NLS-1$
        setSelectedIndex(areaGroup, area);

        buildAudioSourceMenu();

        try{
	        URL r = getClass().getResource("/com/lorent/lvmc/resource/images/trayicon/trayicon_base.png"); //$NON-NLS-1$
	        if(r!=null)
				setIconImage(Toolkit.getDefaultToolkit().createImage(r));
        }catch(Exception e){
        }
        //getRootPane().setDefaultButton(startStopButton);
        pack();
        setLocationRelativeTo(null);
    }

    private void updateInfoLabel() {
        String name = movieFolder.getName();
        if (name.length() > 16) {
            name = name.substring(0, 14) + "..."; //$NON-NLS-1$
        }
        infoLabel.setText(MessageFormat.format(infoLabelText, "<a href=\"" + movieFolder.toURI() + "\">" + name + "</a>")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        optionsMenu = new javax.swing.JPopupMenu();
//        formatAviItem = new javax.swing.JRadioButtonMenuItem();
//        formatQuicktimeItem = new javax.swing.JRadioButtonMenuItem();
//        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        areaScreenItem = new javax.swing.JRadioButtonMenuItem();
        areaCustomItem = new javax.swing.JRadioButtonMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        videoMenu = new javax.swing.JMenu();
        colorMillionsItem = new javax.swing.JRadioButtonMenuItem();
        colorThousandsItem = new javax.swing.JRadioButtonMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        fps20Item = new javax.swing.JRadioButtonMenuItem();
        fps10Item = new javax.swing.JRadioButtonMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        cursorBlackItem = new javax.swing.JRadioButtonMenuItem();
        cursorWhiteItem = new javax.swing.JRadioButtonMenuItem();
        cursoYellowItem = new javax.swing.JRadioButtonMenuItem();
        cursorNoneItem = new javax.swing.JRadioButtonMenuItem();
        audioMenu = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        audio44kHzItem = new javax.swing.JRadioButtonMenuItem();
        audio22kHzItem = new javax.swing.JRadioButtonMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        monoItem = new javax.swing.JRadioButtonMenuItem();
        stereoItem = new javax.swing.JRadioButtonMenuItem();
//        formatGroup = new javax.swing.ButtonGroup();
        areaGroup = new javax.swing.ButtonGroup();
        colorGroup = new javax.swing.ButtonGroup();
        audioRateGroup = new javax.swing.ButtonGroup();
        cursorGroup = new javax.swing.ButtonGroup();
        fpsGroup = new javax.swing.ButtonGroup();
        channelsGroup = new javax.swing.ButtonGroup();
        audioSourceGroup = new javax.swing.ButtonGroup();
        startStopButton = new javax.swing.JToggleButton();
        timeLabel = new javax.swing.JLabel();
        audioMonitor = new org.monte.screenrecorder.JAudioMonitor();
        infoLabel = new javax.swing.JLabel();
        optionsButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

//        formatGroup.add(formatAviItem);
//        formatAviItem.setText("AVI"); //$NON-NLS-1$
//        optionsMenu.add(formatAviItem);
//
//        formatGroup.add(formatQuicktimeItem);
//        formatQuicktimeItem.setSelected(true);
//        formatQuicktimeItem.setText("QuickTime"); //$NON-NLS-1$
//        optionsMenu.add(formatQuicktimeItem);
//        optionsMenu.add(jSeparator4);

        areaGroup.add(areaScreenItem);
        areaScreenItem.setSelected(true);
        areaScreenItem.setText(Messages.getString("ScreenRecorderCompactMain.entireScreen")); //$NON-NLS-1$
        areaScreenItem.setActionCommand("Screen"); //$NON-NLS-1$
        optionsMenu.add(areaScreenItem);

        areaGroup.add(areaCustomItem);
        areaCustomItem.setText(Messages.getString("ScreenRecorderCompactMain.customArea")); //$NON-NLS-1$
        areaCustomItem.setActionCommand("Custom"); //$NON-NLS-1$
        areaCustomItem.addActionListener(formListener);
        optionsMenu.add(areaCustomItem);
        optionsMenu.add(jSeparator1);

        videoMenu.setText(Messages.getString("ScreenRecorderCompactMain.video")); //$NON-NLS-1$

        colorGroup.add(colorMillionsItem);
        colorMillionsItem.setSelected(true);
        colorMillionsItem.setText(Messages.getString("ScreenRecorderCompactMain.millionsOfColors")); //$NON-NLS-1$
        colorMillionsItem.setActionCommand("Millions"); //$NON-NLS-1$
        videoMenu.add(colorMillionsItem);

        colorGroup.add(colorThousandsItem);
        colorThousandsItem.setText(Messages.getString("ScreenRecorderCompactMain.thousandsOfColors")); //$NON-NLS-1$
        colorThousandsItem.setActionCommand("Thousands"); //$NON-NLS-1$
        videoMenu.add(colorThousandsItem);
        videoMenu.add(jSeparator5);

        fpsGroup.add(fps20Item);
        fps20Item.setSelected(true);
        fps20Item.setText(Messages.getString("ScreenRecorderCompactMain.20fps")); //$NON-NLS-1$
        fps20Item.setActionCommand("20"); //$NON-NLS-1$
        videoMenu.add(fps20Item);

        fpsGroup.add(fps10Item);
        fps10Item.setText(Messages.getString("ScreenRecorderCompactMain.10fps")); //$NON-NLS-1$
        fps10Item.setActionCommand("10"); //$NON-NLS-1$
        videoMenu.add(fps10Item);
        videoMenu.add(jSeparator3);

        cursorGroup.add(cursorBlackItem);
        cursorBlackItem.setSelected(true);
        cursorBlackItem.setText(Messages.getString("ScreenRecorderCompactMain.blackCursor")); //$NON-NLS-1$
        cursorBlackItem.setActionCommand("Black"); //$NON-NLS-1$
        videoMenu.add(cursorBlackItem);

        cursorGroup.add(cursorWhiteItem);
        cursorWhiteItem.setText(Messages.getString("ScreenRecorderCompactMain.whiteCursor")); //$NON-NLS-1$
        cursorWhiteItem.setActionCommand("White"); //$NON-NLS-1$
        videoMenu.add(cursorWhiteItem);

        cursorGroup.add(cursoYellowItem);
        cursoYellowItem.setText(Messages.getString("ScreenRecorderCompactMain.yellowCursor")); //$NON-NLS-1$
        cursoYellowItem.setActionCommand("White"); //$NON-NLS-1$
        videoMenu.add(cursoYellowItem);

        cursorGroup.add(cursorNoneItem);
        cursorNoneItem.setText(Messages.getString("ScreenRecorderCompactMain.noCursor")); //$NON-NLS-1$
        cursorNoneItem.setActionCommand("None"); //$NON-NLS-1$
        videoMenu.add(cursorNoneItem);

        optionsMenu.add(videoMenu);

        audioMenu.setText(Messages.getString("ScreenRecorderCompactMain.audio")); //$NON-NLS-1$
        audioMenu.add(jSeparator2);

        audioRateGroup.add(audio44kHzItem);
        audio44kHzItem.setSelected(true);
        audio44kHzItem.setText(Messages.getString("ScreenRecorderCompactMain.highAudio")); //$NON-NLS-1$
        audio44kHzItem.setActionCommand("44100"); //$NON-NLS-1$
        audioMenu.add(audio44kHzItem);

        audioRateGroup.add(audio22kHzItem);
        audio22kHzItem.setText(Messages.getString("ScreenRecorderCompactMain.normalAudio")); //$NON-NLS-1$
        audio22kHzItem.setActionCommand("22050"); //$NON-NLS-1$
        audioMenu.add(audio22kHzItem);
        audioMenu.add(jSeparator6);

        channelsGroup.add(monoItem);
        monoItem.setSelected(true);
        monoItem.setText(Messages.getString("ScreenRecorderCompactMain.mono")); //$NON-NLS-1$
        monoItem.setActionCommand("44100"); //$NON-NLS-1$
        audioMenu.add(monoItem);

        channelsGroup.add(stereoItem);
        stereoItem.setText(Messages.getString("ScreenRecorderCompactMain.stereo")); //$NON-NLS-1$
        stereoItem.setActionCommand("22050"); //$NON-NLS-1$
        audioMenu.add(stereoItem);

        optionsMenu.add(audioMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Messages.getString("ScreenRecorderCompactMain.title")); //$NON-NLS-1$
        setResizable(false);
        addWindowListener(formListener);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        startStopButton.setText(Messages.getString("ScreenRecorderCompactMain.start")); //$NON-NLS-1$
        startStopButton.setToolTipText(Messages.getString("ScreenRecorderCompactMain.startTips")); //$NON-NLS-1$
        startStopButton.addActionListener(formListener);
        getContentPane().add(startStopButton, new java.awt.GridBagConstraints());

        timeLabel.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N //$NON-NLS-1$
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setText("<html>--:--"); //$NON-NLS-1$
        timeLabel.setToolTipText(Messages.getString("ScreenRecorderCompactMain.timeLabelTips")); //$NON-NLS-1$
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        getContentPane().add(timeLabel, gridBagConstraints);

        audioMonitor.setForeground(new java.awt.Color(255, 255, 255));
        audioMonitor.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N //$NON-NLS-1$
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(audioMonitor, gridBagConstraints);

        infoLabel.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N //$NON-NLS-1$
        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        infoLabel.setText(Messages.getString("ScreenRecorderCompactMain.infoLabelText")); //$NON-NLS-1$
        infoLabel.setToolTipText(Messages.getString("ScreenRecorderCompactMain.infoLabelTips")); //$NON-NLS-1$
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(infoLabel, gridBagConstraints);

        optionsButton.setText("Options"); //$NON-NLS-1$
        optionsButton.addActionListener(formListener);
        getContentPane().add(optionsButton, new java.awt.GridBagConstraints());

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.WindowListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == startStopButton) {
                ScreenRecorderCompactMain.this.startStopPerformed(evt);
            }
            else if (evt.getSource() == optionsButton) {
                ScreenRecorderCompactMain.this.optionsPerformed(evt);
            }
            else if (evt.getSource() == areaCustomItem) {
                ScreenRecorderCompactMain.this.selectAreaPerformed(evt);
            }
        }

        public void windowActivated(java.awt.event.WindowEvent evt) {
        }

        public void windowClosed(java.awt.event.WindowEvent evt) {
        }

        public void windowClosing(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == ScreenRecorderCompactMain.this) {
                ScreenRecorderCompactMain.this.formWindowClosing(evt);
            }
        }

        public void windowDeactivated(java.awt.event.WindowEvent evt) {
        }

        public void windowDeiconified(java.awt.event.WindowEvent evt) {
            if (evt.getSource() == ScreenRecorderCompactMain.this) {
                ScreenRecorderCompactMain.this.formWindowDeiconified(evt);
            }
        }

        public void windowIconified(java.awt.event.WindowEvent evt) {
        }

        public void windowOpened(java.awt.event.WindowEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void updateValues() {
        Preferences prefs = Preferences.userNodeForPackage(ScreenRecorderCompactMain.class);
//        format = getSelectedIndex(formatGroup);
//        prefs.putInt("ScreenRecording.format", format); //$NON-NLS-1$
        //encoding = encodingChoice.getSelectedIndex();
        //prefs.putInt("ScreenRecording.encoding", encoding);
        depth = getSelectedIndex(colorGroup);
        prefs.putInt("ScreenRecording.colorDepth", depth); //$NON-NLS-1$
        cursor = getSelectedIndex(cursorGroup);
        prefs.putInt("ScreenRecording.cursor", cursor); //$NON-NLS-1$
        audioRate = getSelectedIndex(audioRateGroup);
        prefs.putInt("ScreenRecording.audioRate", audioRate); //$NON-NLS-1$

        audioSource = getSelectedIndex(audioSourceGroup);
        prefs.putInt("ScreenRecording.audioSource", audioSource); //$NON-NLS-1$

        channels = getSelectedIndex(channelsGroup);
        prefs.putInt("ScreenRecording.channels", channels); //$NON-NLS-1$
        area = getSelectedIndex(areaGroup);
        prefs.putInt("ScreenRecording.area", area); //$NON-NLS-1$
        fps = getSelectedIndex(fpsGroup);
        prefs.putInt("ScreenRecording.fps", fps); //$NON-NLS-1$

        prefs.putInt("ScreenRecording.customAreaX", customAreaRect.x); //$NON-NLS-1$
        prefs.putInt("ScreenRecording.customAreaY", customAreaRect.y); //$NON-NLS-1$
        prefs.putInt("ScreenRecording.customAreaWidth", customAreaRect.width); //$NON-NLS-1$
        prefs.putInt("ScreenRecording.customAreaHeight", customAreaRect.height); //$NON-NLS-1$
    }

    private void start() throws IOException, AWTException {
        updateValues();

        if (screenRecorder == null) {
            setSettingsEnabled(false);
            //timeLabel.setForeground(Color.RED);
            //timeLabel.setText("Recording...");

            String mimeType;
            String videoFormatName, compressorName;
            float quality = 1.0f;
            int bitDepth;
            switch (depth) {
                default:
                case 0:
                    bitDepth = 24;
                    break;
                case 1:
                    bitDepth = 16;
                    break;
                case 2:
                    bitDepth = 24;
                    break;
            }
            mimeType = MIME_AVI;
            videoFormatName = ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
            compressorName = COMPRESSOR_NAME_AVI_TECHSMITH_SCREEN_CAPTURE;
//            switch (format) {
//                default:
//                case 0:
//                    mimeType = MIME_AVI;
//                    videoFormatName = ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
//                    compressorName = COMPRESSOR_NAME_AVI_TECHSMITH_SCREEN_CAPTURE;
//                    break;
//                case 1:
//                    mimeType = MIME_QUICKTIME;
//                    videoFormatName = ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
//                    compressorName = COMPRESSOR_NAME_AVI_TECHSMITH_SCREEN_CAPTURE;
//                    break;
//            }

            int audioRate;
            int audioBitsPerSample;
            int audioChannels;
            AudioSourceItem asi = (AudioSourceItem) getSelectedItem(audioSourceGroup).getClientProperty("AudioSourceItem"); //$NON-NLS-1$

            switch (this.audioRate) {
                default:
                case 0:
                    audioRate = 44100;
                    audioBitsPerSample = 16;
                    break;
                case 1:
                    audioRate = 22050;
                    audioBitsPerSample = 8;
                    break;
                case 2:
                    audioRate = 0;
                    audioBitsPerSample = 0;
                    break;
            }
            switch (channels) {
                default:
                case 0:
                    audioChannels = 1;
                    break;
                case 1:
                    audioChannels = 2;
                    break;
            }
            String crsr;
            int mouseRate;
            switch (cursor) {
                default:
                case 3:
                    crsr = null;
                    mouseRate = 0;
                    break;
                case 0:
                    crsr = ScreenRecorder.ENCODING_BLACK_CURSOR;
                    mouseRate = 30;
                    break;
                case 1:
                    crsr = ScreenRecorder.ENCODING_WHITE_CURSOR;
                    mouseRate = 30;
                    break;
                case 2:
                    crsr = ScreenRecorder.ENCODING_YELLOW_CURSOR;
                    mouseRate = 30;
                    break;
            }
            GraphicsConfiguration cfg = getGraphicsConfiguration();
            Rectangle areaRect = null;
            Dimension outputDimension = null;
            switch (area) {
                default:
                case 0:
                    areaRect = cfg.getBounds();
                    break;
                case 1:
                    areaRect = customAreaRect.getBounds();
                    break;
            }
            outputDimension = areaRect.getSize();

            int screenRate;
            switch (fps) {
                default:
                case 0:
                    screenRate = 20;
                    break;
                case 1:
                    screenRate = 10;
                    break;
            }


            screenRecorder = new ScreenRecorder(cfg, areaRect,
                    // the file format:
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, mimeType),
                    //
                    // the output format for screen capture:
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, videoFormatName,
                    CompressorNameKey, compressorName,
                    WidthKey, outputDimension.width,
                    HeightKey, outputDimension.height,
                    DepthKey, bitDepth, FrameRateKey, Rational.valueOf(screenRate),
                    QualityKey, quality,
                    KeyFrameIntervalKey, (int) (screenRate * 60) // one keyframe per minute is enough
                    ),
                    //
                    // the output format for mouse capture:
                    crsr == null ? null : new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, crsr,
                    FrameRateKey, Rational.valueOf(mouseRate)),
                    //
                    // the output format for audio capture:
                    !asi.isEnabled ? null : new Format(MediaTypeKey, MediaType.AUDIO,
                    //EncodingKey, audioFormatName,
                    SampleRateKey, Rational.valueOf(audioRate),
                    SampleSizeInBitsKey, audioBitsPerSample,
                    ChannelsKey, audioChannels),
                    //
                    // the storage location of the movie
                    movieFolder);
            screenRecorder.setAudioMixer(asi.mixerInfo == null ? null : AudioSystem.getMixer(asi.mixerInfo));
            System.out.println("mxier:" + asi); //$NON-NLS-1$
            System.out.println("screenRecoder.mixer=" + screenRecorder.getAudioMixer()); //$NON-NLS-1$

            if (timer == null) {
                timer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateTimeLabel();
                    }
                });
                timer.setRepeats(true);
                timer.start();
            }

            //startStopButton.setText("Stop");
            screenRecorder.addChangeListener(handler);
            screenRecorder.start();

            audioMonitor.setScreenRecorder(screenRecorder);
            audioMonitor.start();
        }
    }

    private void updateTimeLabel() {
        if (screenRecorder != null) {
            long t = System.currentTimeMillis() - screenRecorder.getStartTime();
            long h = t / (1000 * 3600);
            long m = t / (1000 * 60) - (h * 60);
            long s = t / (1000) - (h * 3600 + m * 60);
            timeLabel.setText((h == 0 ? "" : (h < 10 ? "0" + h : h) + ":") + (m < 10 ? "0" + m : m) + ":" + ((s < 10 ? "0" + s : s))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        }
    }

    public void setSettingsEnabled(boolean b) {
        for (MenuElement c : optionsMenu.getSubElements()) {
            if (c instanceof JComponent) {
                ((JComponent) c).setEnabled(b);
            }
        }

    }

    private void stop() {

        if (screenRecorder != null) {
            audioMonitor.setScreenRecorder(null);
            audioMonitor.stop();

            final ScreenRecorder r = screenRecorder;
            startStopButton.setEnabled(false);
            if (timer != null) {
                timer.stop();
                timer = null;
            }

            timeLabel.setText(Messages.getString("ScreenRecorderCompactMain.timeLabelStoppingText")); //$NON-NLS-1$
            screenRecorder = null;
            new Worker() {
                @Override
                protected Object construct() throws Exception {
                    r.stop();
                    return null;
                }

                @Override
                protected void finished() {
                    ScreenRecorder.State state = r.getState();
                    setSettingsEnabled(true);
                    startStopButton.setEnabled(true);
                    //startStopButton.setText("Start");
                    timeLabel.setText("--:--"); //$NON-NLS-1$
                }
            }.start();
        }
    }

    private void recordingFailed() {
        if (screenRecorder != null) {
            screenRecorder = null;
            startStopButton.setEnabled(true);
            //startStopButton.setText("Start");
            timeLabel.setText(Messages.getString("ScreenRecorderCompactMain.timeLabelfailedText")); //$NON-NLS-1$
            setExtendedState(Frame.NORMAL);
            JOptionPane.showMessageDialog(ScreenRecorderCompactMain.this,
                    Messages.getString("ScreenRecorderCompactMain.failedText"), //$NON-NLS-1$
                    "Screen Recorder", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stop();
        setVisible(false);
        //System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        // stop();
    }//GEN-LAST:event_formWindowDeiconified

    private void selectAreaPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAreaPerformed
        final JRecordingAreaFrame f = new JRecordingAreaFrame();
        Rectangle r = customAreaRect;
        f.setBounds(r);
        f.updateLabel();
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                customAreaRect = f.getBounds();
                f.setVisible(false);
                f.dispose();
                setVisible(true);
                f.removeWindowListener(this);
                final Preferences prefs = Preferences.userNodeForPackage(ScreenRecorderMain.class);
                prefs.putInt("ScreenRecorder.customAreaX", customAreaRect.x); //$NON-NLS-1$
                prefs.putInt("ScreenRecorder.customAreaY", customAreaRect.y); //$NON-NLS-1$
                prefs.putInt("ScreenRecorder.customAreaWidth", customAreaRect.width); //$NON-NLS-1$
                prefs.putInt("ScreenRecorder.customAreaHeight", customAreaRect.height); //$NON-NLS-1$
                ((JComponent) getContentPane()).invalidate();
                ((JComponent) getContentPane()).revalidate();
            }
        });
        setVisible(false);
        f.setVisible(true);
    }//GEN-LAST:event_selectAreaPerformed

    private void optionsPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsPerformed
        optionsMenu.show(optionsButton, 0, optionsButton.getHeight());
    }//GEN-LAST:event_optionsPerformed

    private void startStopPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStopPerformed
        if (screenRecorder == null) {
            setExtendedState(Frame.ICONIFIED);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        start();
                    } catch (Throwable t) {
                        t.printStackTrace();
                        setExtendedState(Frame.NORMAL);
                        JOptionPane.showMessageDialog(ScreenRecorderCompactMain.this,
                                "<html><b>Sorry. Screen Recording failed.</b><br>" + t.getMessage(),
                                "Screen Recorder", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
                        stop();
                    }
                }
            });
        } else {
            stop();
        }
    }//GEN-LAST:event_startStopPerformed
    private static Vector<AudioSourceItem> getAudioSources() {
        Vector<AudioSourceItem> l = new Vector<AudioSourceItem>();

        l.add(new AudioSourceItem(Messages.getString("ScreenRecorderCompactMain.none"), null, false)); //$NON-NLS-1$
        l.add(new AudioSourceItem(Messages.getString("ScreenRecorderCompactMain.defaultInput"), null, true)); //$NON-NLS-1$
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        DataLine.Info lineInfo = new DataLine.Info(
                TargetDataLine.class,
                new javax.sound.sampled.AudioFormat(
                44100.0f,
                16,
                2,
                true,
                true));

        for (Mixer.Info info : mixers) {
            Mixer mixer = AudioSystem.getMixer(info);
            if (mixer.isLineSupported(lineInfo)) {
            	
                String name = info.getName();
                try {
					name=new String(name.getBytes("iso8859-1"),System.getProperty("sun.jnu.encoding", "gbk")); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (UnsupportedEncodingException e) {
				}
                l.add(new AudioSourceItem(name, info));
            }
        }
        return l;
    }

    @Override
	public void dispose() {
		stop();
		super.dispose();
	}

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    //UIManager.put(area, area);
                    ToolTipManager.sharedInstance().setDismissDelay(20000);
                } catch (Exception e) {
                    //ignore
                }
                new ScreenRecorderCompactMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem areaCustomItem;
    private javax.swing.ButtonGroup areaGroup;
    private javax.swing.JRadioButtonMenuItem areaScreenItem;
    private javax.swing.JRadioButtonMenuItem audio22kHzItem;
    private javax.swing.JRadioButtonMenuItem audio44kHzItem;
    private javax.swing.JMenu audioMenu;
    private org.monte.screenrecorder.JAudioMonitor audioMonitor;
    private javax.swing.ButtonGroup audioRateGroup;
    private javax.swing.ButtonGroup audioSourceGroup;
    private javax.swing.ButtonGroup channelsGroup;
    private javax.swing.ButtonGroup colorGroup;
    private javax.swing.JRadioButtonMenuItem colorMillionsItem;
    private javax.swing.JRadioButtonMenuItem colorThousandsItem;
    private javax.swing.JRadioButtonMenuItem cursoYellowItem;
    private javax.swing.JRadioButtonMenuItem cursorBlackItem;
    private javax.swing.ButtonGroup cursorGroup;
    private javax.swing.JRadioButtonMenuItem cursorNoneItem;
    private javax.swing.JRadioButtonMenuItem cursorWhiteItem;
//    private javax.swing.JRadioButtonMenuItem formatAviItem;
//    private javax.swing.ButtonGroup formatGroup;
//    private javax.swing.JRadioButtonMenuItem formatQuicktimeItem;
    private javax.swing.JRadioButtonMenuItem fps10Item;
    private javax.swing.JRadioButtonMenuItem fps20Item;
    private javax.swing.ButtonGroup fpsGroup;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
//    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JRadioButtonMenuItem monoItem;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPopupMenu optionsMenu;
    private javax.swing.JToggleButton startStopButton;
    private javax.swing.JRadioButtonMenuItem stereoItem;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JMenu videoMenu;
    // End of variables declaration//GEN-END:variables
}
