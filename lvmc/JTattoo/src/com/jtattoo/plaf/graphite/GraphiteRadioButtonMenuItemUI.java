/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf.graphite;

import com.jtattoo.plaf.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;

/**
 * @author Michael Hagen
 */
public class GraphiteRadioButtonMenuItemUI extends BaseRadioButtonMenuItemUI {

    public static ComponentUI createUI(JComponent c) {
        return new GraphiteRadioButtonMenuItemUI();
    }

    protected void paintBackground(Graphics g, JComponent c, int x, int y, int w, int h) {
        JMenuItem b = (JMenuItem) c;
        ButtonModel model = b.getModel();
        if (model.isArmed() || (c instanceof JMenu && model.isSelected())) {
            JTattooUtilities.fillHorGradient(g, AbstractLookAndFeel.getTheme().getMenuSelectionColors(), x, y, w, h);
        } else if (!AbstractLookAndFeel.getTheme().isMenuOpaque()) {
            Graphics2D g2D = (Graphics2D) g;
            Composite composite = g2D.getComposite();
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractLookAndFeel.getTheme().getMenuAlpha());
            g2D.setComposite(alpha);
            g.setColor(AbstractLookAndFeel.getMenuBackgroundColor());
            g.fillRect(x, y, w, h);
            g2D.setComposite(composite);
        } else {
            g.setColor(AbstractLookAndFeel.getMenuBackgroundColor());
            g.fillRect(x, y, w, h);
        }
        if (menuItem.isSelected() && menuItem.isArmed()) {
            g.setColor(AbstractLookAndFeel.getMenuSelectionForegroundColor());
        } else {
            g.setColor(AbstractLookAndFeel.getMenuForegroundColor());
        }
    }

}
