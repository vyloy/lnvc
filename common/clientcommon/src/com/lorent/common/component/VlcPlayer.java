/*
 * VlcPlayer.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.common.component;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import com.lorent.common.event.VlcPlayerEventAdater;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 *
 * @author  __USER__
 */
public class VlcPlayer extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(VlcPlayer.class);
	private static EmbeddedMediaPlayerComponent mediaPlayerComponent = null;
	private VlcPlayerEventAdater vlcPlayerEventAdater = null;
	private static final int SKIP_TIME_MS = 10 * 1000;
	private String mediaMRL = "";
	private static Frame fullscreenWindow = new Frame();
	private VlcPlayer vlcPlayerPanel = null;

	public String getMediaMRL() {
		return mediaMRL;
	}

	public void setMediaMRL(String mediaMRL) {
		this.mediaMRL = mediaMRL;
	}

	public VlcPlayerEventAdater getVlcPlayerEventAdater() {
		return vlcPlayerEventAdater;
	}

	public void setVlcPlayerEventAdater(
			VlcPlayerEventAdater vlcPlayerEventAdater) {
		this.vlcPlayerEventAdater = vlcPlayerEventAdater;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayerComponent.getMediaPlayer();
	}

	public void releaseMediaPlayer() {
		mediaPlayerComponent.release();
	}

	public boolean isPlaying() {
		return mediaPlayerComponent.getMediaPlayer().isPlaying();
	}

	public void play() {
		mediaPlayerComponent.getMediaPlayer().playMedia(mediaMRL);
	}

	public void stop() {
		mediaPlayerComponent.getMediaPlayer().stop();
	}

	public static void init(String vlcLibPath) {
		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				vlcLibPath);
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	}

	static {
		/*
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
		    @Override
		    public void eventDispatched(AWTEvent event) {
		        if(event instanceof KeyEvent) {
		            KeyEvent keyEvent = (KeyEvent)event;
		            if(keyEvent.getID() == KeyEvent.KEY_PRESSED) {
		                if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
		                	if (fullscreenWindow.isVisible()) {
		                		mediaPlayerComponent.getMediaPlayer().setFullScreen(false);
							}
		                }
		            }
		        }
		    }
		}, AWTEvent.KEY_EVENT_MASK);
		 */
	}

	/** Creates new form VlcPlayer */
	public VlcPlayer() {
		vlcPlayerPanel = this;
		initComponents();
		positionSlider.setValue(0);
		mediaPlayerComponent = new MyEmbeddedMediaPlayerComponent();
		add(mediaPlayerComponent, BorderLayout.CENTER);

		MyMediaPlayerEventAdater mediaPlayerEventAdapter = new MyMediaPlayerEventAdater() {

			@Override
			public void finished(MediaPlayer mediaPlayer) {
				super.finished(mediaPlayer);
				stopButton.setEnabled(false);
				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
				skipPreviousButton.setEnabled(false);
				skipNextButton.setEnabled(false);
				fullScreenButton.setEnabled(false);
				pauseButton.setText("暂停");
				pauseButton
						.setIcon(new javax.swing.ImageIcon(
								getClass()
										.getResource(
												"/com/lorent/common/resource/images/media-playback-pause-8.png")));
			}

			@Override
			public void paused(MediaPlayer mediaPlayer) {
				super.paused(mediaPlayer);
				pauseButton.setText("继续");
				pauseButton
						.setIcon(new javax.swing.ImageIcon(
								getClass()
										.getResource(
												"/com/lorent/common/resource/images/media-playback-start-8.png")));
			}

			@Override
			public void playing(MediaPlayer mediaPlayer) {
				super.playing(mediaPlayer);
				playButton.setEnabled(false);
				stopButton.setEnabled(true);
				pauseButton.setEnabled(true);
				skipPreviousButton.setEnabled(true);
				skipNextButton.setEnabled(true);
				fullScreenButton.setEnabled(true);
				pauseButton.setText("暂停");
				pauseButton
						.setIcon(new javax.swing.ImageIcon(
								getClass()
										.getResource(
												"/com/lorent/common/resource/images/media-playback-pause-8.png")));
			}

			@Override
			public void stopped(MediaPlayer mediaPlayer) {
				super.stopped(mediaPlayer);
				stopButton.setEnabled(false);
				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
				skipPreviousButton.setEnabled(false);
				skipNextButton.setEnabled(false);
				fullScreenButton.setEnabled(false);
				pauseButton.setText("暂停");
				pauseButton
						.setIcon(new javax.swing.ImageIcon(
								getClass()
										.getResource(
												"/com/lorent/common/resource/images/media-playback-pause-8.png")));
			}

			@Override
			public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
				super.positionChanged(mediaPlayer, newPosition);
				
				final float temp = newPosition;
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						int position = (int) (temp * 1000.0f);
//						log.info("positionChanged:  oldposition: "+mediaPlayerComponent
//								.getMediaPlayer().getPosition()+" newposition: "+temp);
						positionSlider.setValue(position);
						positionProgressBar.setValue(position);
						
					}
				});
			}

		};

		mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(mediaPlayerEventAdapter);
		//		executorService.scheduleAtFixedRate(new UpdateRunnable(
		//				mediaPlayerComponent.getMediaPlayer()), 0L, 1L,
		//				TimeUnit.SECONDS);
		//		fullscreenWindow.setExtendedState(state)

		fullscreenWindow.setAlwaysOnTop(true);
		fullscreenWindow.setFocusable(true);
		fullscreenWindow.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				log.info("fullscreenframe.mouseClicked");
			}

		});
		fullscreenWindow.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				mediaPlayerComponent.getMediaPlayer().setFullScreen(false);
			}

		});
		fullscreenWindow.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						if (fullscreenWindow.isVisible()) {
							mediaPlayerComponent.getMediaPlayer()
									.setFullScreen(false);
						}
					}
				}
			}

		});
		fullscreenWindow.setUndecorated(true);
		//		fullscreenDialog.setResizable(false);
		//		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(fullscreenframe);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		playerControllPanel = new javax.swing.JPanel();
		playerButtonsPanel = new javax.swing.JPanel();
		playButton = new javax.swing.JButton();
		pauseButton = new javax.swing.JButton();
		stopButton = new javax.swing.JButton();
		skipPreviousButton = new javax.swing.JButton();
		skipNextButton = new javax.swing.JButton();
		fullScreenButton = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		positionSlider = new javax.swing.JSlider();
		positionProgressBar = new javax.swing.JProgressBar();
		jPanel4 = new javax.swing.JPanel();
		volumeSlider = new javax.swing.JSlider();
		jLabel1 = new javax.swing.JLabel();

		setLayout(new java.awt.BorderLayout());

		playerControllPanel.setLayout(new java.awt.BorderLayout());

		playerButtonsPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.LEFT));

		playButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/common/resource/images/media-playback-start-8.png"))); // NOI18N
		playButton.setText("\u64ad\u653e");
		playButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playButtonActionPerformed(evt);
			}
		});
		playerButtonsPanel.add(playButton);

		pauseButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/common/resource/images/media-playback-pause-8.png"))); // NOI18N
		pauseButton.setText("\u6682\u505c");
		pauseButton.setEnabled(false);
		pauseButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				pauseButtonActionPerformed(evt);
			}
		});
		playerButtonsPanel.add(pauseButton);

		stopButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/common/resource/images/media-playback-stop-8.png"))); // NOI18N
		stopButton.setText("\u505c\u6b62");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				stopButtonActionPerformed(evt);
			}
		});
		playerButtonsPanel.add(stopButton);

		skipPreviousButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/common/resource/images/media-skip-backward-8.png"))); // NOI18N
		skipPreviousButton.setText("\u540e\u9000");
		skipPreviousButton.setEnabled(false);
		skipPreviousButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						skipPreviousButtonActionPerformed(evt);
					}
				});
		playerButtonsPanel.add(skipPreviousButton);

		skipNextButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/common/resource/images/media-skip-forward-8.png"))); // NOI18N
		skipNextButton.setText("\u524d\u8fdb");
		skipNextButton.setEnabled(false);
		skipNextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				skipNextButtonActionPerformed(evt);
			}
		});
		playerButtonsPanel.add(skipNextButton);

		fullScreenButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/common/resource/images/view-fullscreen.png"))); // NOI18N
		fullScreenButton.setText("\u5168\u5c4f");
		fullScreenButton.setEnabled(false);
		fullScreenButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fullScreenButtonActionPerformed(evt);
			}
		});
		playerButtonsPanel.add(fullScreenButton);

		playerControllPanel.add(playerButtonsPanel,
				java.awt.BorderLayout.CENTER);

		jPanel3.setLayout(new java.awt.BorderLayout());

		positionSlider.setMaximum(1000);
		positionSlider.setSnapToTicks(true);
		positionSlider.setValue(0);
		positionSlider.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				positionSliderMouseReleased(evt);
			}
		});
		positionSlider
				.addChangeListener(new javax.swing.event.ChangeListener() {
					public void stateChanged(javax.swing.event.ChangeEvent evt) {
						positionSliderStateChanged(evt);
					}
				});
		jPanel3.add(positionSlider, java.awt.BorderLayout.CENTER);

		positionProgressBar.setMaximum(1000);
		jPanel3.add(positionProgressBar, java.awt.BorderLayout.SOUTH);

		playerControllPanel.add(jPanel3, java.awt.BorderLayout.NORTH);

		jPanel4.setPreferredSize(new java.awt.Dimension(120, 36));
		jPanel4.setLayout(new java.awt.BorderLayout());

		volumeSlider.setMaximum(200);
		volumeSlider.setValue(100);
		volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				volumeSliderStateChanged(evt);
			}
		});
		jPanel4.add(volumeSlider, java.awt.BorderLayout.CENTER);

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/common/resource/images/audio-volume-high-5.png"))); // NOI18N
		jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);

		playerControllPanel.add(jPanel4, java.awt.BorderLayout.EAST);

		add(playerControllPanel, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void positionSliderStateChanged(javax.swing.event.ChangeEvent evt) {
//		log.info("positionSliderStateChanged: "+positionSlider.getValue());
	}

	private void fullScreenButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (mediaPlayerComponent.getMediaPlayer().isFullScreen()) {
			mediaPlayerComponent.getMediaPlayer().setFullScreen(false);
		} else {
			mediaPlayerComponent.getMediaPlayer().setFullScreen(true);
		}
	}

	private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {
		mediaPlayerComponent.getMediaPlayer()
				.setVolume(volumeSlider.getValue());
	}

	private void positionSliderMouseReleased(java.awt.event.MouseEvent evt) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				log.info("positionSliderMouseReleased: "
						+ positionSlider.getMousePosition().x + "/"
						+ positionSlider.getWidth());
				
				int x = positionSlider.getMousePosition().x;
				int width = positionSlider.getWidth();
				long timeValue = (mediaPlayerComponent.getMediaPlayer().getLength() * x)
						/ width;
				
				mediaPlayerComponent.getMediaPlayer().setTime(timeValue);
//				int position = (int) (mediaPlayerComponent.getMediaPlayer()
//						.getPosition() * 1000.0f);
//				positionSlider.setValue(position);
				if (vlcPlayerEventAdater != null) {
//					vlcPlayerEventAdater.mediaPlayerPositionChanged(position);
					vlcPlayerEventAdater.mediaPlayerTimeChanged(mediaPlayerComponent.getMediaPlayer().getTime());
				}
				
			}
		});
	}

	private void skipPreviousButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mediaPlayerComponent.getMediaPlayer().skip(-SKIP_TIME_MS);
				int position = (int) (mediaPlayerComponent.getMediaPlayer()
						.getPosition() * 1000.0f);
				positionSlider.setValue(position);
				if (vlcPlayerEventAdater != null) {
//					vlcPlayerEventAdater.mediaPlayerPositionChanged(mediaPlayerComponent.getMediaPlayer()
//							.getPosition());
					vlcPlayerEventAdater.mediaPlayerTimeChanged(mediaPlayerComponent.getMediaPlayer().getTime());
				}
			}
		});
	}

	private void skipNextButtonActionPerformed(java.awt.event.ActionEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mediaPlayerComponent.getMediaPlayer().skip(SKIP_TIME_MS);
				int position = (int) (mediaPlayerComponent.getMediaPlayer()
						.getPosition() * 1000.0f);
				positionSlider.setValue(position);
				if (vlcPlayerEventAdater != null) {
//					vlcPlayerEventAdater.mediaPlayerPositionChanged(position);
					vlcPlayerEventAdater.mediaPlayerTimeChanged(mediaPlayerComponent.getMediaPlayer().getTime());
				}
			}
		});
	}

	private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {
		mediaPlayerComponent.getMediaPlayer().stop();
	}

	private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {
		mediaPlayerComponent.getMediaPlayer().pause();
	}

	private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {
		mediaPlayerComponent.getMediaPlayer().playMedia(mediaMRL);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton fullScreenButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JButton pauseButton;
	private javax.swing.JButton playButton;
	private javax.swing.JPanel playerButtonsPanel;
	private javax.swing.JPanel playerControllPanel;
	private javax.swing.JProgressBar positionProgressBar;
	private javax.swing.JSlider positionSlider;
	private javax.swing.JButton skipNextButton;
	private javax.swing.JButton skipPreviousButton;
	private javax.swing.JButton stopButton;
	private javax.swing.JSlider volumeSlider;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JPanel getPlayerControllPanel() {
		return playerControllPanel;
	}

	public javax.swing.JSlider getPositionSlider() {
		return positionSlider;
	}

	public javax.swing.JProgressBar getPositionProgressBar() {
		return positionProgressBar;
	}

	public javax.swing.JPanel getPlayerButtonsPanel() {
		return playerButtonsPanel;
	}

	class MyEmbeddedMediaPlayerComponent extends EmbeddedMediaPlayerComponent {
		@Override
		protected FullScreenStrategy onGetFullScreenStrategy() {
			return new FullScreenStrategy() {

				private boolean isFullScreen = false;

				@Override
				public boolean isFullScreenMode() {
					log.info("isFullScreenMode: " + isFullScreen);
					return isFullScreen;
				}

				@Override
				public void exitFullScreenMode() {
					log.info("exitFullScreenMode");
					long time = mediaPlayerComponent.getMediaPlayer().getTime();
					mediaPlayerComponent.getMediaPlayer().stop();
					fullscreenWindow.setVisible(false);
					vlcPlayerPanel.remove(mediaPlayerComponent);
					vlcPlayerPanel.add(mediaPlayerComponent,
							BorderLayout.CENTER);
					mediaPlayerComponent.getMediaPlayer().play();
					mediaPlayerComponent.getMediaPlayer().setTime(time);
					vlcPlayerPanel.revalidate();
					isFullScreen = false;
					//					LibXUtil.setFullScreenWindow(fullscreenframe, false);
					if (vlcPlayerEventAdater != null) {
						vlcPlayerEventAdater.exitFullScreenMode();
					}
				}

				@Override
				public void enterFullScreenMode() {
					log.info("enterFullScreenMode");
					fullscreenWindow.setLocation(0, 0);
					//					fullscreenWindow.setSize(1200, 800);
					Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
							.getScreenSize();
					//					fullscreenWindow.setSize(screenSize);
					//					fullscreenframe.toFront();
					fullscreenWindow.setBounds(getGraphicsConfiguration()
							.getDevice().getDefaultConfiguration().getBounds());
					long time = mediaPlayerComponent.getMediaPlayer().getTime();
					mediaPlayerComponent.getMediaPlayer().stop();
					//			        fullscreenframe.remove(mediaPlayerComponent);
					fullscreenWindow.add(mediaPlayerComponent,
							BorderLayout.CENTER);
					fullscreenWindow.setVisible(true);
					mediaPlayerComponent.getMediaPlayer().play();
					mediaPlayerComponent.getMediaPlayer().setTime(time);
					//			        fullscreenframe.repaint();
					isFullScreen = true;
					//					LibXUtil.setFullScreenWindow(fullscreenframe, true);

					fullscreenWindow.toFront();
					/*
					java.awt.EventQueue.invokeLater(new Runnable() {
					    @Override
					    public void run() {
					    	fullscreenWindow.toFront();
					    	fullscreenWindow.repaint();
					    }
					});
					*/
					if (vlcPlayerEventAdater != null) {
						vlcPlayerEventAdater.enterFullScreenMode();
					}
				}
			};
		}
	}

	public class MyMediaPlayerEventAdater implements MediaPlayerEventListener {

		@Override
		public void backward(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.backward " + mediaPlayer);
		}

		@Override
		public void buffering(MediaPlayer mediaPlayer, float arg1) {
			//			log.info("MediaPlayerEventAdater.buffering "+mediaPlayer+","+arg1);
		}

		@Override
		public void endOfSubItems(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.endOfSubItems " + mediaPlayer);
		}

		@Override
		public void error(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.error " + mediaPlayer);
		}

		@Override
		public void finished(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.finished " + mediaPlayer);
		}

		@Override
		public void forward(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.forward " + mediaPlayer);
		}

		@Override
		public void lengthChanged(MediaPlayer mediaPlayer, long arg1) {
			log.info("MediaPlayerEventAdater.lengthChanged " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t media,
				String mrl) {
			log.info("MediaPlayerEventAdater.mediaChanged " + mediaPlayer + ","
					+ media + "," + mrl);
		}

		@Override
		public void mediaDurationChanged(MediaPlayer mediaPlayer, long arg1) {
			log.info("MediaPlayerEventAdater.mediaDurationChanged "
					+ mediaPlayer + "," + arg1);
		}

		@Override
		public void mediaFreed(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.mediaFreed " + mediaPlayer);
		}

		@Override
		public void mediaMetaChanged(MediaPlayer mediaPlayer, int arg1) {
			//			log.info("MediaPlayerEventAdater.mediaMetaChanged "+mediaPlayer+","+arg1);
		}

		@Override
		public void mediaParsedChanged(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.mediaParsedChanged " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void mediaStateChanged(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.mediaStateChanged " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void mediaSubItemAdded(MediaPlayer mediaPlayer,
				libvlc_media_t media) {
			log.info("MediaPlayerEventAdater.mediaSubItemAdded " + mediaPlayer
					+ "," + media);
		}

		@Override
		public void newMedia(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.newMedia " + mediaPlayer);
		}

		@Override
		public void opening(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.opening " + mediaPlayer);
		}

		@Override
		public void pausableChanged(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.pausableChanged " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void paused(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.paused " + mediaPlayer);
		}

		@Override
		public void playing(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.playing " + mediaPlayer);
		}

		@Override
		public void positionChanged(MediaPlayer mediaPlayer, float arg1) {
			//			log.info("MediaPlayerEventAdater.positionChanged "+mediaPlayer+","+arg1);
		}

		@Override
		public void seekableChanged(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.seekableChanged " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void snapshotTaken(MediaPlayer mediaPlayer, String arg1) {
			log.info("MediaPlayerEventAdater.snapshotTaken " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void stopped(MediaPlayer mediaPlayer) {
			log.info("MediaPlayerEventAdater.stopped " + mediaPlayer);
		}

		@Override
		public void subItemFinished(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.subItemFinished " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void subItemPlayed(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.subItemPlayed " + mediaPlayer
					+ "," + arg1);
		}

		@Override
		public void timeChanged(MediaPlayer mediaPlayer, long arg1) {
			//			log.info("MediaPlayerEventAdater.timeChanged "+mediaPlayer+","+arg1);
		}

		@Override
		public void titleChanged(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.titleChanged " + mediaPlayer + ","
					+ arg1);
		}

		@Override
		public void videoOutput(MediaPlayer mediaPlayer, int arg1) {
			log.info("MediaPlayerEventAdater.videoOutput " + mediaPlayer + ","
					+ arg1);
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("lorent media player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setSize(1080, 720);
		VlcPlayer
				.init("D:\\MyEclipseWorkspaces\\git\\lnvc2\\vovo\\client\\vlc");
		VlcPlayer vlcPlayer = new VlcPlayer();
		vlcPlayer
				.setMediaMRL("http://10.168.250.12:8800/20121218103504_291_720P.mp4");
		frame.add(vlcPlayer);
		frame.setVisible(true);
	}

}