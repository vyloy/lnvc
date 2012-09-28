/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package com.jtattoo.plaf.luna;

import javax.swing.*;
import com.jtattoo.plaf.*;

/**
 * @author Michael Hagen
 */
public class LunaIconFactory implements AbstractIconFactory {

    private static LunaIconFactory instance = null;

    private LunaIconFactory() {
    }

    public static synchronized LunaIconFactory getInstance() {
        if (instance == null) {
            instance = new LunaIconFactory();
        }
        return instance;
    }

    public Icon getOptionPaneErrorIcon() {
        return LunaIcons.getOptionPaneErrorIcon();
    }

    public Icon getOptionPaneWarningIcon() {
        return LunaIcons.getOptionPaneWarningIcon();
    }

    public Icon getOptionPaneInformationIcon() {
        return LunaIcons.getOptionPaneInformationIcon();
    }

    public Icon getOptionPaneQuestionIcon() {
        return LunaIcons.getOptionPaneQuestionIcon();
    }

    public Icon getFileChooserDetailViewIcon() {
        return LunaIcons.getFileChooserDetailViewIcon();
    }

    public Icon getFileChooserHomeFolderIcon() {
        return LunaIcons.getFileChooserHomeFolderIcon();
    }

    public Icon getFileChooserListViewIcon() {
        return LunaIcons.getFileChooserListViewIcon();
    }

    public Icon getFileChooserNewFolderIcon() {
        return LunaIcons.getFileChooserNewFolderIcon();
    }

    public Icon getFileChooserUpFolderIcon() {
        return LunaIcons.getFileChooserUpFolderIcon();
    }

    public Icon getMenuIcon() {
        return LunaIcons.getMenuIcon();
    }

    public Icon getIconIcon() {
        return LunaIcons.getIconIcon();
    }

    public Icon getMaxIcon() {
        return LunaIcons.getMaxIcon();
    }

    public Icon getMinIcon() {
        return LunaIcons.getMinIcon();
    }

    public Icon getCloseIcon() {
        return LunaIcons.getCloseIcon();
    }

    public Icon getPaletteCloseIcon() {
        return LunaIcons.getPaletteCloseIcon();
    }

    public Icon getRadioButtonIcon() {
        return LunaIcons.getRadioButtonIcon();
    }

    public Icon getCheckBoxIcon() {
        return LunaIcons.getCheckBoxIcon();
    }

    public Icon getComboBoxIcon() {
        return LunaIcons.getComboBoxIcon();
    }

    public Icon getTreeComputerIcon() {
        return LunaIcons.getTreeComputerIcon();
    }

    public Icon getTreeFloppyDriveIcon() {
        return LunaIcons.getTreeFloppyDriveIcon();
    }

    public Icon getTreeHardDriveIcon() {
        return LunaIcons.getTreeHardDriveIcon();
    }

    public Icon getTreeFolderIcon() {
        return LunaIcons.getTreeFolderIcon();
    }

    public Icon getTreeLeafIcon() {
        return LunaIcons.getTreeLeafIcon();
    }

    public Icon getTreeCollapsedIcon() {
        return LunaIcons.getTreeControlIcon(true);
    }

    public Icon getTreeExpandedIcon() {
        return LunaIcons.getTreeControlIcon(false);
    }

    public Icon getMenuArrowIcon() {
        return LunaIcons.getMenuArrowIcon();
    }

    public Icon getMenuCheckBoxIcon() {
        return LunaIcons.getMenuCheckBoxIcon();
    }

    public Icon getMenuRadioButtonIcon() {
        return LunaIcons.getMenuRadioButtonIcon();
    }

    public Icon getUpArrowIcon() {
        return LunaIcons.getUpArrowIcon();
    }

    public Icon getDownArrowIcon() {
        return LunaIcons.getDownArrowIcon();
    }

    public Icon getLeftArrowIcon() {
        return LunaIcons.getLeftArrowIcon();
    }

    public Icon getRightArrowIcon() {
        return LunaIcons.getRightArrowIcon();
    }

    public Icon getSplitterDownArrowIcon() {
        return LunaIcons.getSplitterDownArrowIcon();
    }

    public Icon getSplitterHorBumpIcon() {
        return LunaIcons.getSplitterHorBumpIcon();
    }

    public Icon getSplitterLeftArrowIcon() {
        return LunaIcons.getSplitterLeftArrowIcon();
    }

    public Icon getSplitterRightArrowIcon() {
        return LunaIcons.getSplitterRightArrowIcon();
    }

    public Icon getSplitterUpArrowIcon() {
        return LunaIcons.getSplitterUpArrowIcon();
    }

    public Icon getSplitterVerBumpIcon() {
        return LunaIcons.getSplitterVerBumpIcon();
    }

    public Icon getThumbHorIcon() {
        return LunaIcons.getThumbHorIcon();
    }

    public Icon getThumbVerIcon() {
        return LunaIcons.getThumbVerIcon();
    }

    public Icon getThumbHorIconRollover() {
        return LunaIcons.getThumbHorIconRollover();
    }

    public Icon getThumbVerIconRollover() {
        return LunaIcons.getThumbVerIconRollover();
    }
}
