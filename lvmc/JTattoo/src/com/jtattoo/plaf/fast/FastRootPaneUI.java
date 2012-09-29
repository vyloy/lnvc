/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf.fast;

import javax.swing.*;
import javax.swing.plaf.*;

import com.jtattoo.plaf.*;

/**
 * @author  Michael Hagen
 */
public class FastRootPaneUI extends BaseRootPaneUI {

    public static ComponentUI createUI(JComponent c) {
        return new FastRootPaneUI();
    }

    public BaseTitlePane createTitlePane(JRootPane root) {
        return new FastTitlePane(root, this);
    }
}
