package org.jhotdraw.samples.svg.action;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.jhotdraw.app.EditableComponent;
import org.jhotdraw.util.ResourceBundleUtil;

import com.lorent.whiteboard.model.View;

public class RefreshAction extends AbstractAction {
public final static String ID = "refresh";
    
    /** Creates a new instance. */
    public RefreshAction() {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.app.Labels");
        labels.configureAction(this, ID);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Component focusOwner = KeyboardFocusManager
				.getCurrentKeyboardFocusManager().getPermanentFocusOwner();
		if (focusOwner != null && focusOwner instanceof View) {
			View view = (View) focusOwner;
			view.refreshPage(view.getPage());
		}

	}

}
