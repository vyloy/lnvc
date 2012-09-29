package com.jtattoo.plaf.vovoglass;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.UIResource;

import com.jtattoo.plaf.BaseTabbedPaneUI;
import com.jtattoo.plaf.JTattooUtilities;
import com.jtattoo.plaf.BaseTabbedPaneUI.ScrollableTabSupport;



public class VoVoTabbedPaneUI extends BaseTabbedPaneUI{

	private static BufferedImage selectedImage = null;
	private static BufferedImage selectedBackImage = null;
	
	static{
		try {
			selectedImage = ImageIO.read(VoVoTabbedPaneUI.class.getResource(
			"/com/jtattoo/resource/image/whitealpha.png"));
			selectedBackImage = ImageIO.read(VoVoTabbedPaneUI.class.getResource(
			"/com/jtattoo/resource/image/whitealpha2.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		
		Color colorArr[] = getTabColors(tabIndex, isSelected);
        switch (tabPlacement) {
            case TOP:
            default:
                if (isSelected) {
//                    JTattooUtilities.fillHorGradient(g, colorArr, x , y , w , h );
                	g.drawImage(selectedBackImage, x, y, w, h, null);
                } else {
//                    JTattooUtilities.fillHorGradient(g, colorArr, x + 1, y + 1, w - 1, h - 1);
                	if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
                		boolean isEnabled = tabPane.isEnabledAt(tabIndex);
    		            Color backColor = tabPane.getBackgroundAt(tabIndex);
    		            if ((backColor instanceof UIResource)) {
    		            	if (isSelected) {
//    		                    colorArr = AbstractLookAndFeel.getTheme().getSelectedColors();
    		                } else if (tabIndex == rolloverIndex && isEnabled) {
//    		                    colorArr = AbstractLookAndFeel.getTheme().getRolloverColors();
//    		                	JTattooUtilities.fillHorGradient(g, colorArr, x , y , w , h );
    		                	g.drawImage(selectedImage, x, y, w, h, null);
    		                } 
    		            }
                	}
                }
//                if (roundedTabs) {
//                    Color c = AbstractLookAndFeel.getTheme().getTabColors()[AbstractLookAndFeel.getTheme().getTabColors().length - 1];
//                    g.setColor(c);
//                    g.drawLine(x + 1, y + 1, x + 2, y + 1);
//                    g.drawLine(x + w - 1, y + 1, x + w, y + 1);
//                }
                break;
            case LEFT:
                if (isSelected) {
//                    JTattooUtilities.fillHorGradient(g, colorArr, x + 1, y + 1, w, h - 1);
//                	JTattooUtilities.fillHorGradient(g, colorArr, x , y , w, h );
                	g.drawImage(selectedBackImage, x, y, w, h, null);
                } else {
//                    JTattooUtilities.fillHorGradient(g, colorArr, x + 1, y + 1, w - 1, h - 1);
                	if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
                		boolean isEnabled = tabPane.isEnabledAt(tabIndex);
    		            Color backColor = tabPane.getBackgroundAt(tabIndex);
    		            if ((backColor instanceof UIResource)) {
    		            	if (isSelected) {
//    		                    colorArr = AbstractLookAndFeel.getTheme().getSelectedColors();
    		                } else if (tabIndex == rolloverIndex && isEnabled) {
//    		                    colorArr = AbstractLookAndFeel.getTheme().getRolloverColors();
//    		                	JTattooUtilities.fillHorGradient(g, colorArr, x + 1, y + 1, w - 1, h - 1);
//    		                	JTattooUtilities.fillHorGradient(g, colorArr, x , y , w , h);
    		                	
    		                	g.drawImage(selectedImage, x, y, w, h, null);
    		                } 
    		            }
                	}
                }
                break;
            case BOTTOM:
            	if (isSelected) {
              	g.drawImage(selectedBackImage, x, y, w, h, null);
              } else {
              	if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
              		boolean isEnabled = tabPane.isEnabledAt(tabIndex);
  		            Color backColor = tabPane.getBackgroundAt(tabIndex);
  		            if ((backColor instanceof UIResource)) {
  		            	if (isSelected) {
  		                } else if (tabIndex == rolloverIndex && isEnabled) {
  		                	g.drawImage(selectedImage, x, y, w, h, null);
  		                } 
  		            }
              	}
              }
                break;
            case RIGHT:
                if (isSelected) {
                	g.drawImage(selectedBackImage, x, y, w, h, null);
                } else {
                	if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
                		boolean isEnabled = tabPane.isEnabledAt(tabIndex);
    		            Color backColor = tabPane.getBackgroundAt(tabIndex);
    		            if ((backColor instanceof UIResource)) {
    		            	if (isSelected) {
    		                } else if (tabIndex == rolloverIndex && isEnabled) {
    		                	g.drawImage(selectedImage, x, y, w, h, null);
    		                } 
    		            }
                	}
                }
                break;
        }
		
	}

	
	
	@Override
	protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects,
			int tabIndex, Rectangle iconRect, Rectangle textRect) {
//		super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
		
		Rectangle tabRect = rects[tabIndex];
        int selectedIndex = tabPane.getSelectedIndex();
        boolean isSelected = selectedIndex == tabIndex;
        Graphics2D g2 = null;
        Polygon cropShape = null;
        Shape savedClip = null;
        int cropx = 0;
        int cropy = 0;

        if (scrollableTabLayoutEnabled()) {
            if (g instanceof Graphics2D) {
                g2 = (Graphics2D) g;

                // Render visual for cropped tab edge...
                Rectangle viewRect = tabScroller.viewport.getViewRect();
                int cropline;
                switch (tabPlacement) {
                    case LEFT:
                    case RIGHT:
                        cropline = viewRect.y + viewRect.height;
                        if ((tabRect.y < cropline) && (tabRect.y + tabRect.height > cropline)) {
                            cropShape = createCroppedTabClip(tabPlacement, tabRect, cropline);
                            cropx = tabRect.x;
                            cropy = cropline - 1;
                        }
                        break;
                    case TOP:
                    case BOTTOM:
                    default:
                        cropline = viewRect.x + viewRect.width;
                        if ((tabRect.x < cropline) && (tabRect.x + tabRect.width > cropline)) {
                            cropShape = createCroppedTabClip(tabPlacement, tabRect, cropline);
                            cropx = cropline - 1;
                            cropy = tabRect.y;
                        }
                }
                if (cropShape != null) {
                    savedClip = g2.getClip();
                    g2.clip(cropShape);
                }
            }
        }

        paintTabBackground(g, tabPlacement, tabIndex, tabRect.x, tabRect.y, tabRect.width, tabRect.height, isSelected);
//        paintTabBorder(g, tabPlacement, tabIndex, tabRect.x, tabRect.y, tabRect.width, tabRect.height, isSelected);

        try {
            boolean doPaintContent = true;
            if (JTattooUtilities.getJavaVersion() >= 1.6) {
                doPaintContent = (tabPane.getTabComponentAt(tabIndex) == null);
            }
            if (doPaintContent) {
                String title = tabPane.getTitleAt(tabIndex);
                Font font = getTabFont(isSelected);
                FontMetrics metrics = g.getFontMetrics(font);
                Icon icon = getIconForTab(tabIndex);

                layoutLabel(tabPlacement, metrics, tabIndex, title, icon, tabRect, iconRect, textRect, isSelected);
                paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
                paintIcon(g, tabPlacement, tabIndex, icon, iconRect, isSelected);
            }
            paintFocusIndicator(g, tabPlacement, rects, tabIndex, iconRect, textRect, isSelected);
        } catch (Exception ex) {
        }

        if (cropShape != null) {
            paintCroppedTabEdge(g, tabPlacement, tabIndex, cropx, cropy);
            g2.setClip(savedClip);
        }
		
	}



	@Override
	protected void paintContentBorder(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
//		super.paintContentBorder(g, tabPlacement, selectedIndex, x, y, w, h);
		
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		
		BufferedImage bufimg = new BufferedImage(
    			c.getWidth(),
    			c.getHeight(),
    			BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufimg.createGraphics();
		g2.setColor(g.getColor());
		Graphics2D gx = (Graphics2D) g;
		gx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.0f));
		gx.drawImage(bufimg,0,0,null);
		
		super.paint(g, c);
	}

	@Override
	protected void installComponents() {
		super.installComponents();
		ScrollableTabSupport tabScrollersuSupport = getTabScroller();
		tabScrollersuSupport.getViewport().setOpaque(false);
		tabScrollersuSupport.getTabPanel().setOpaque(false);
	}
	
}
