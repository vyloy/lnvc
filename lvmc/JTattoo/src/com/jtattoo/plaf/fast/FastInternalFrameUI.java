/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf.fast;

import javax.swing.*;
import javax.swing.plaf.*;

import com.jtattoo.plaf.*;

/**
 * @author Michael Hagen
 */
public class FastInternalFrameUI extends BaseInternalFrameUI {

    public FastInternalFrameUI(JInternalFrame b) {
        super(b);
    }

    public static ComponentUI createUI(JComponent c) {
        return new FastInternalFrameUI((JInternalFrame) c);
    }

    /*protected JComponent createNorthPane(JInternalFrame w) {
        titlePane = new FastInternalFrameTitlePane(w);
        return titlePane;
    }*/
}

