/*
 * @(#)DefaultDrawingView.java  3.0.2  2006-07-03
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
�
 */

package org.jhotdraw.draw;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.jhotdraw.app.EditableComponent;
import org.jhotdraw.draw.action.DefaultDrawingViewDropTargetListener;
import org.jhotdraw.gui.datatransfer.CompositeTransferable;
import org.jhotdraw.samples.svg.SVGPanel;
import org.jhotdraw.samples.svg.figures.ChangeBounds;
import org.jhotdraw.samples.svg.figures.ChangePage;
import org.jhotdraw.samples.svg.figures.Delete;
import org.jhotdraw.samples.svg.figures.Paste;
import org.jhotdraw.samples.svg.figures.SVGImage;
import org.jhotdraw.samples.svg.figures.Transform;
import org.jhotdraw.samples.svg.figures.TransformFigures;
import org.jhotdraw.samples.svg.uicomponent.PageQuickAccessPanel;
import org.jhotdraw.undo.CompositeEdit;
import org.jhotdraw.util.ReversedList;
import org.jhotdraw.xml.DOMFactory;
import org.jhotdraw.xml.NanoXMLLiteDOMInput;
import org.jhotdraw.xml.NanoXMLLiteDOMOutput;
import org.jhotdraw.xml.XMLTransferable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastStateCommand;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.RemoteFigures;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.View;
import com.sun.org.apache.xml.internal.security.encryption.Transforms;

/**
 * DefaultDrawingView.
 * 
 * @author Werner Randelshofer
 * @version 3.0.2 2006-07-03 Constrainer must be a bound property. <br>
 *          3.0.1 2006-06-11 Draw handles when this DrawingView is the focused
 *          drawing view of the DrawingEditor. <br>
 *          3.0 2006-02-17 Reworked to support multiple drawing views in a
 *          DrawingEditor. <br>
 *          2.0 2006-01-14 Changed to support double precision coordinates. <br>
 *          1.0 2003-12-01 Derived from JHotDraw 5.4b1.
 */
public class DefaultDrawingView extends JComponent implements DrawingView,
		DrawingListener, HandleListener, EditableComponent, View{
	private volatile Drawing drawing;
	private Set<Figure> dirtyFigures = new HashSet<Figure>();
	private Set<Figure> selectedFigures = new HashSet<Figure>();
	private int rainbow = 0;
	private transient LinkedList<Handle> selectionHandles = new LinkedList<Handle>();

	private transient Handle secondaryHandleOwner;
	private transient LinkedList<Handle> secondaryHandles = new LinkedList<Handle>();
	private boolean handlesAreValid = true;
	private Dimension preferredSize;
	private double scaleFactor = 1;
	private Point2D.Double translate = new Point2D.Double(0, 0);
	private int detailLevel;
	private transient DOMFactory domFactory;
	private DrawingEditor editor;
	private Constrainer constrainer = new GridConstrainer(1, 1);
	private transient JLabel emptyDrawingLabel;
	private transient final SVGPanel parentPanel;
	private final boolean doc;
	// private boolean hasPermanentFocus;

	/** Creates new instance. */
	public DefaultDrawingView(SVGPanel svgPanel, boolean doc) {
		if (svgPanel == null)
			throw new IllegalArgumentException("svgPanel == null!");
		this.parentPanel=svgPanel;
		this.doc=doc;
		initComponents();
		setFocusable(true);
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				repaint();
			}
			
			public void focusLost(FocusEvent e) {
				repaint();
			}
		});
		setDoubleBuffered(true);
		new DropTarget(this, new DefaultDrawingViewDropTargetListener(this));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents
		buttonGroup1 = new javax.swing.ButtonGroup();

		setLayout(null);

		setBackground(new java.awt.Color(255, 255, 255));
	}// GEN-END:initComponents

	public Drawing getDrawing() {
		return drawing;
	}

	public java.util.Set getTools() {
		return null;
	}

	public void setEmptyDrawingMessage(String newValue) {
		String oldValue = (emptyDrawingLabel == null) ? null
				: emptyDrawingLabel.getText();
		if (newValue == null) {
			emptyDrawingLabel = null;
		} else {
			emptyDrawingLabel = new JLabel(newValue);
			emptyDrawingLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		firePropertyChange("emptyDrawingMessage", oldValue, newValue);
		repaint();
	}

	public String getEmptyDrawingMessage() {
		return (emptyDrawingLabel == null) ? null : emptyDrawingLabel.getText();
	}

	public void paintComponent(Graphics gr) {

		Graphics2D g = (Graphics2D) gr;

		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		// g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
		// RenderingHints.VALUE_STROKE_PURE);
		g.setRenderingHint(
				RenderingHints.KEY_FRACTIONALMETRICS,
				(Options.isFractionalMetrics()) ? RenderingHints.VALUE_FRACTIONALMETRICS_ON
						: RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, (Options
				.isTextAntialiased()) ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON
				: RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

		drawBackground(g);
		drawGrid(g);
		drawDrawing(g);

		drawHandles(g);
		drawTool(g);
	}

	protected void drawBackground(Graphics2D g) {
		/*
		 * rainbow = (rainbow + 10) % 360; g.setColor( new
		 * Color(Color.HSBtoRGB((float) (rainbow / 360f), 0.3f, 1.0f)) );
		 */
		// Position of the zero coordinate point on the view
		int x = (int) (-translate.x * scaleFactor);
		int y = (int) (-translate.y * scaleFactor);

		int w = getWidth();
		int h = getHeight();

		g.setColor(getBackground());
		g.fillRect(x, y, w - x, h - y);

		// Draw a gray background for the area which is at
		// negative view coordinates.
		if (y > 0) {
			g.setColor(new Color(0xf0f0f0));
			g.fillRect(0, 0, w, y);
		}
		if (x > 0) {
			g.setColor(new Color(0xf0f0f0));
			g.fillRect(0, 0, x, h);
		}
	}

	protected void drawGrid(Graphics2D g) {
		constrainer.draw(g, this);
	}

	protected void drawDrawing(Graphics2D gr) {
		if (drawing != null) {
			if (drawing.getFigureCount() == 0 && emptyDrawingLabel != null) {
				emptyDrawingLabel.setBounds(0, 0, getWidth(), getHeight());
				emptyDrawingLabel.paint(gr);
			} else {
				Graphics2D g = (Graphics2D) gr.create();
				g.translate(-translate.x * scaleFactor, -translate.y
						* scaleFactor);
				g.scale(scaleFactor, scaleFactor);

				drawing.setFontRenderContext(g.getFontRenderContext());
				drawing.draw(g);
				// System.out.println("DefaultDrawingView.drawDrawing...drawing.draw");
				g.dispose();
			}
		}
	}

	protected void drawHandles(java.awt.Graphics2D g) {
		if (editor != null && editor.getFocusedView() == this) {
			validateHandles();
			for (Handle h : getSelectionHandles()) {
				h.draw(g);
			}
			for (Handle h : getSecondaryHandles()) {
				h.draw(g);
			}
		}
	}

	protected void drawTool(Graphics2D g) {
		if (editor != null && editor.getView() == this
				&& editor.getTool() != null) {
			editor.getTool().draw(g);
		}
	}

	public void setDrawing(Drawing d) {
		if (this.drawing != null) {
			this.drawing.removeDrawingListener(this);
			clearSelection();
		}
		this.drawing = d;
		if (this.drawing != null) {
			this.drawing.addDrawingListener(this);
		}
		invalidateDimension();
		invalidate();
		if (getParent() != null)
			getParent().validate();
		repaint();
	}

	protected void repaint(Rectangle2D.Double r) {
		Rectangle vr = drawingToView(r);
		vr.grow(2, 2);
		repaint(vr);
	}

	public void areaInvalidated(DrawingEvent evt) {
		repaint(evt.getInvalidatedArea());
		invalidateDimension();
	}

	public void areaInvalidated(HandleEvent evt) {
		repaint(evt.getInvalidatedArea());
		invalidateDimension();
	}

	public void figureAdded(DrawingEvent evt) {
		// Repaint the whole drawing to remove the message label
		if (evt.getDrawing().getFigureCount() == 1) {
			repaint();
		} else {
			repaint(evt.getInvalidatedArea());
		}
		invalidateDimension();
	}

	public void figureRemoved(DrawingEvent evt) {
		// Repaint the whole drawing to draw the message label
		if (evt.getDrawing().getFigureCount() == 0) {
			repaint();
		} else {
			repaint(evt.getInvalidatedArea());
		}
		removeFromSelection(evt.getFigure());
		invalidateDimension();
	}

	public void invalidate() {
		invalidateDimension();
		super.invalidate();
	}

	/**
	 * Adds a figure to the current selection.
	 */
	public void addToSelection(Figure figure) {
		if (figure instanceof SVGImage) {
			return;
		}
		selectedFigures.add(figure);
		invalidateHandles();
		fireSelectionChanged();
		repaint();
	}

	/**
	 * Adds a collection of figures to the current selection.
	 */
	public void addToSelection(Collection<Figure> figures) {
		LinkedList<Figure> _figures = new LinkedList<Figure>();
		for (Figure f : figures) {
			if (f instanceof SVGImage) {
				continue;
			}
			_figures.add(f);
		}
		selectedFigures.addAll(_figures);
		invalidateHandles();
		fireSelectionChanged();
		repaint();
	}

	/**
	 * Removes a figure from the selection.
	 */
	public void removeFromSelection(Figure figure) {
		if (selectedFigures.remove(figure)) {
			invalidateHandles();
			fireSelectionChanged();
		}
		repaint();
	}

	/**
	 * If a figure isn't selected it is added to the selection. Otherwise it is
	 * removed from the selection.
	 */
	public void toggleSelection(Figure figure) {
		if (selectedFigures.contains(figure)) {
			selectedFigures.remove(figure);
		} else {
			if (figure instanceof SVGImage) {
				return;
			}
			selectedFigures.add(figure);
		}
		fireSelectionChanged();
		invalidateHandles();
		repaint();
	}

	/**
	 * Selects all figures.
	 */
	public void selectAll() {
		selectedFigures.clear();
		Collection<Figure> figures = drawing.getFigures();
		LinkedList<Figure> _figures = new LinkedList<Figure>();
		for (Figure f : figures) {
			if (f instanceof SVGImage) {
				continue;
			}
			_figures.add(f);
		}
		selectedFigures.addAll(_figures);
		invalidateHandles();
		fireSelectionChanged();
		repaint();
	}

	/**
	 * Clears the current selection.
	 */
	public void clearSelection() {
		if (getSelectionCount() > 0) {
			selectedFigures.clear();
			invalidateHandles();
			fireSelectionChanged();
		}
		repaint();
	}

	/**
	 * Test whether a given figure is selected.
	 */
	public boolean isFigureSelected(Figure checkFigure) {
		return selectedFigures.contains(checkFigure);
	}

	/**
	 * Gets the current selection as a FigureSelection. A FigureSelection can be
	 * cut, copied, pasted.
	 */
	public Collection<Figure> getSelectedFigures() {
		return Collections.unmodifiableSet(selectedFigures);
	}

	/**
	 * Gets the number of selected figures.
	 */
	public int getSelectionCount() {
		return selectedFigures.size();
	}

	/**
	 * Gets the currently active selection handles.
	 */
	private java.util.List<Handle> getSelectionHandles() {
		validateHandles();
		return Collections.unmodifiableList(selectionHandles);
	}

	/**
	 * Gets the currently active secondary handles.
	 */
	private java.util.List<Handle> getSecondaryHandles() {
		validateHandles();
		return Collections.unmodifiableList(secondaryHandles);
	}

	/**
	 * Invalidates the handles.
	 */
	private void invalidateHandles() {
		if (handlesAreValid) {
			handlesAreValid = false;

			Rectangle invalidatedArea = null;
			for (Handle handle : selectionHandles) {
				handle.removeHandleListener(this);
				if (invalidatedArea == null) {
					invalidatedArea = handle.getDrawBounds();
				} else {
					invalidatedArea.add(handle.getDrawBounds());
				}
				handle.dispose();
			}
			selectionHandles.clear();
			secondaryHandles.clear();

			switch (selectedFigures.size()) {
			case 0:
				if (invalidatedArea != null) {
					repaint(invalidatedArea);
				}
				break;
			case 1:
				if (invalidatedArea != null) {
					repaint(invalidatedArea);
				}
				// validateHandles();
				break;
			default:
				repaint();
				break;
			}
		}
	}

	/**
	 * Validates the handles.
	 */
	private void validateHandles() {
		if (!handlesAreValid) {
			handlesAreValid = true;

			Rectangle invalidatedArea = null;
			int level = detailLevel;
			do {
				for (Figure figure : getSelectedFigures()) {
					for (Handle handle : figure.createHandles(level)) {
						handle.setView(this);
						selectionHandles.add(handle);
						handle.addHandleListener(this);
						if (invalidatedArea == null) {
							invalidatedArea = handle.getBounds();
						} else {
							invalidatedArea.add(handle.getBounds());
						}
					}
				}
			} while (level-- > 0 && selectionHandles.size() == 0);
			detailLevel = level + 1;

			if (invalidatedArea != null) {
				repaint(invalidatedArea);
			}
		}

	}

	/**
	 * Finds a handle at a given coordinates.
	 * 
	 * @return A handle, null if no handle is found.
	 */
	public Handle findHandle(Point p) {
		validateHandles();

		for (Handle handle : new ReversedList<Handle>(getSecondaryHandles())) {
			if (handle.contains(p)) {
				return handle;
			}
		}
		for (Handle handle : new ReversedList<Handle>(getSelectionHandles())) {
			if (handle.contains(p)) {
				return handle;
			}
		}
		return null;
	}

	/**
	 * Gets compatible handles.
	 * 
	 * @return A collection containing the handle and all compatible handles.
	 */
	public Collection<Handle> getCompatibleHandles(Handle master) {
		validateHandles();

		HashSet<Figure> owners = new HashSet<Figure>();
		LinkedList<Handle> compatibleHandles = new LinkedList<Handle>();
		owners.add(master.getOwner());
		compatibleHandles.add(master);

		for (Handle handle : getSelectionHandles()) {
			if (!owners.contains(handle.getOwner())
					&& handle.isCombinableWith(master)) {
				owners.add(handle.getOwner());
				compatibleHandles.add(handle);
			}
		}
		return compatibleHandles;

	}

	/**
	 * Finds a figure at a given coordinates.
	 * 
	 * @return A figure, null if no figure is found.
	 */
	public Figure findFigure(Point p) {
		return getDrawing().findFigure(viewToDrawing(p));
	}

	public Collection<Figure> findFigures(Rectangle r) {
		return getDrawing().findFigures(viewToDrawing(r));
	}

	public Collection<Figure> findFiguresWithin(Rectangle r) {
		return getDrawing().findFiguresWithin(viewToDrawing(r));
	}

	public void addFigureSelectionListener(FigureSelectionListener fsl) {
		listenerList.add(FigureSelectionListener.class, fsl);
	}

	public void removeFigureSelectionListener(FigureSelectionListener fsl) {
		listenerList.remove(FigureSelectionListener.class, fsl);
	}

	/**
	 * Notify all listenerList that have registered interest for notification on
	 * this event type.
	 */
	protected void fireSelectionChanged() {
		if (listenerList.getListenerCount() > 0) {
			FigureSelectionEvent event = null;
			// Notify all listeners that have registered interest for
			// Guaranteed to return a non-null array
			Object[] listeners = listenerList.getListenerList();
			// Process the listeners last to first, notifying
			// those that are interested in this event
			for (int i = listeners.length - 2; i >= 0; i -= 2) {
				if (listeners[i] == FigureSelectionListener.class) {
					// Lazily create the event:
					if (event == null)
						event = new FigureSelectionEvent(this);
					((FigureSelectionListener) listeners[i + 1])
							.selectionChanged(event);
				}
			}
		}
	}

	public void handleRequestRemove(HandleEvent e) {
		selectionHandles.remove(e.getHandle());
		e.getHandle().dispose();
		invalidateHandles();
		// validateHandles();
		repaint(e.getInvalidatedArea());
	}

	protected void invalidateDimension() {
		preferredSize = null;
	}

	public Constrainer getConstrainer() {
		return constrainer;
	}

	public void setConstrainer(Constrainer newValue) {
		Constrainer oldValue = constrainer;
		constrainer = newValue;
		repaint();
		firePropertyChange("constrainer", oldValue, newValue);
	}

	/**
	 * Side effect: Changes view Translation!!!
	 */
	public Dimension getPreferredSize() {
		if (preferredSize == null) {
			Dimension d = new Dimension();
			if (drawing != null) {
				try{
					for (Figure f : drawing.getFigures()) {
						Rectangle2D.Double r = f.getDrawBounds();
						if(r.x<0||r.y<0)
							continue;
						d.width = (int) Math.max(d.width, r.x + r.width);
						d.height = (int) Math.max(d.height, r.y + r.height);
					}
				}catch(RuntimeException e){
					return getPreferredSize();
				}
			}
			preferredSize = new Dimension(
					(int) ((d.width) * scaleFactor)+5,
					(int) ((d.height) * scaleFactor)+5);
			fireViewTransformChanged();
			repaint();
		}
		return preferredSize;
	}

	public void setPreferredSize(Dimension d) {
		preferredSize = d;
	}

	/**
	 * Converts drawing coordinates to view coordinates.
	 */
	public Point drawingToView(Point2D.Double p) {
		return new Point((int) ((p.x - translate.x) * scaleFactor),
				(int) ((p.y - translate.y) * scaleFactor));
	}

	/**
	 * Converts view coordinates to drawing coordinates.
	 */
	public Point2D.Double viewToDrawing(Point p) {
		return new Point2D.Double(p.x / scaleFactor + translate.x, p.y
				/ scaleFactor + translate.y);
	}

	public Rectangle drawingToView(Rectangle2D.Double r) {
		return new Rectangle((int) ((r.x - translate.x) * scaleFactor),
				(int) ((r.y - translate.y) * scaleFactor),
				(int) (r.width * scaleFactor), (int) (r.height * scaleFactor));
	}

	public Rectangle2D.Double viewToDrawing(Rectangle r) {
		return new Rectangle2D.Double(r.x / scaleFactor + translate.x, r.y
				/ scaleFactor + translate.y, r.width / scaleFactor, r.height
				/ scaleFactor);
	}

	public Container getContainer() {
		return this;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(double newValue) {
		double oldValue = scaleFactor;
		scaleFactor = newValue;

		fireViewTransformChanged();

		firePropertyChange("scaleFactor", oldValue, newValue);

		invalidateDimension();
		invalidate();
		if (getParent() != null)
			getParent().validate();
		repaint();
	}

	protected void fireViewTransformChanged() {
		if(selectionHandles==null||secondaryHandles==null)
			return;
		for (Handle handle : selectionHandles) {
			handle.viewTransformChanged();
		}
		for (Handle handle : secondaryHandles) {
			handle.viewTransformChanged();
		}
	}

	public void setHandleDetailLevel(int newValue) {
		detailLevel = newValue;
		invalidateHandles();
		repaint();
	}

	public int getHandleDetailLevel() {
		return detailLevel;
	}

	public void handleRequestSecondaryHandles(HandleEvent e) {
		// if (e.getHandle() != secondaryHandleOwner) {
		secondaryHandleOwner = e.getHandle();
		secondaryHandles.clear();
		secondaryHandles.addAll(secondaryHandleOwner.createSecondaryHandles());
		for (Handle h : secondaryHandles) {
			h.setView(this);
			h.addHandleListener(this);
		}
		repaint();
		// }
	}

	public AffineTransform getDrawingToViewTransform() {
		AffineTransform t = new AffineTransform();
		t.scale(scaleFactor, scaleFactor);
		t.translate(-translate.x, -translate.y);
		return t;
	}

	public void setDOMFactory(DOMFactory newValue) {
		DOMFactory oldValue = domFactory;
		this.domFactory = newValue;
		firePropertyChange("DOMFactory", oldValue, newValue);
	}

	public DOMFactory getDOMFactory() {
		return domFactory;
	}

	public void copy() {
		if (domFactory == null) {
			getToolkit().beep();
			return;
		}

		copy(getSelectedFigures());
	}

	public void copy(Collection<Figure> figures) {
		if (domFactory == null) {
			getToolkit().beep();
			return;
		}

		HashSet<Figure> toBeCopied = new HashSet<Figure>(figures);
		if (toBeCopied.size() == 0)
			return;

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			NanoXMLLiteDOMOutput domo = new NanoXMLLiteDOMOutput(domFactory);
			domo.openElement("DrawingClip");
			for (Figure f : getDrawing().getFigures()) {
				if (toBeCopied.contains(f)) {
					domo.writeObject(f);
				}
			}
			domo.closeElement();
			domo.save(out);
			byte[] data = out.toByteArray();
			CompositeTransferable transfer = new CompositeTransferable();
			transfer.add(new XMLTransferable(data,
					"application/x-drawing-clip", "DrawingClip"));
			transfer.add(new XMLTransferable(data, "text/xml", "DrawingClip"));
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(transfer, transfer);
		} catch (IOException e) {
			e.printStackTrace();
			getToolkit().beep();
		}
	}

	public void cut() {
		if (domFactory == null) {
			getToolkit().beep();
			return;
		}
		copy();
		delete();
	}

	public void cut(Collection<Figure> figures) {
		copy(figures);
		delete(figures);
	}

	public void delete() {
		ArrayList<Figure> toBeDeleted = new ArrayList<Figure>(
				getSelectedFigures());
		Client.getInstance().broadcast(new Delete().add(toBeDeleted));
		clearSelection();
		getDrawing().removeAll(toBeDeleted);
	}

	public void delete(Collection<Figure> figures) {
		clearSelection();
		getDrawing().removeAll(figures);
	}

	public void paste() {
		if (domFactory == null) {
			getToolkit().beep();
			return;
		}
		try {
			ArrayList<Figure> toBeSelected = new ArrayList<Figure>(
					getSelectedFigures());
			DataFlavor flavor = new DataFlavor("application/x-drawing-clip",
					"Drawing Clip");
			Transferable transfer = Toolkit.getDefaultToolkit()
					.getSystemClipboard().getContents(this);
			if (transfer.isDataFlavorSupported(flavor)) {
				CompositeEdit ce = new CompositeEdit("Paste");
				getDrawing().fireUndoableEditHappened(ce);
				for (Figure f : new LinkedList<Figure>()) {
					getDrawing().remove(f);
				}

				InputStream in = null;
				try {
					in = (InputStream) transfer.getTransferData(flavor);
					NanoXMLLiteDOMInput domi = new NanoXMLLiteDOMInput(
							domFactory, in);
					domi.openElement("DrawingClip");
					if(domi.getElementCount()==0){
						return;
					}
					Paste paste = new Paste();
					for (int i = 0, n = domi.getElementCount(); i < n; i++) {
						Figure f = (Figure) domi.readObject(i);
						paste.add(f);
					}
					Client.getInstance().broadcast(paste);
				} finally {
					if (in != null)
						in.close();
				}
				clearSelection();
				addToSelection(toBeSelected);
				getDrawing().fireUndoableEditHappened(ce);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void paste(Collection<Figure> figures) {
		if (domFactory == null) {
			getToolkit().beep();
			return;
		}
		try {
			ArrayList<Figure> toBeSelected = new ArrayList<Figure>(figures);
			DataFlavor flavor = new DataFlavor("application/x-drawing-clip",
					"Drawing Clip");
			Transferable transfer = Toolkit.getDefaultToolkit()
					.getSystemClipboard().getContents(this);
			if (transfer.isDataFlavorSupported(flavor)) {
				CompositeEdit ce = new CompositeEdit("Paste");
				getDrawing().fireUndoableEditHappened(ce);
				for (Figure f : new LinkedList<Figure>()) {
					getDrawing().remove(f);
				}

				InputStream in = null;
				try {
					in = (InputStream) transfer.getTransferData(flavor);
					NanoXMLLiteDOMInput domi = new NanoXMLLiteDOMInput(
							domFactory, in);
					domi.openElement("DrawingClip");
					for (int i = 0, n = domi.getElementCount(); i < n; i++) {
						Figure f = (Figure) domi.readObject(i);
						getDrawing().add(f);
						toBeSelected.add(f);
					}
				} finally {
					if (in != null)
						in.close();
				}
				clearSelection();
				addToSelection(toBeSelected);
				getDrawing().fireUndoableEditHappened(ce);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void duplicate() {
		Collection<Figure> sorted = getDrawing().sort(getSelectedFigures());
		HashMap<Figure, Figure> originalToDuplicateMap = new HashMap<Figure, Figure>(
				sorted.size());

		clearSelection();
		Drawing drawing = getDrawing();
		ArrayList<Figure> duplicates = new ArrayList<Figure>(sorted.size());
		AffineTransform tx = new AffineTransform();
		tx.translate(5, 5);
		for (Figure f : sorted) {
			Figure d = (Figure) f.clone();
			d.basicTransform(tx);
			duplicates.add(d);
			originalToDuplicateMap.put(f, d);
			drawing.add(d);
		}
		for (Figure f : duplicates) {
			f.remap(originalToDuplicateMap);
		}
		addToSelection(duplicates);
	}

	public void add(Figure f) {
		getDrawing().add(f);
	}

	public void removeNotify(DrawingEditor editor) {
		this.editor = null;
		repaint();
	}

	public void addNotify(DrawingEditor editor) {
		this.editor = editor;
		repaint();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup buttonGroup1;
	// End of variables declaration//GEN-END:variables

	private static final Logger logger = LoggerFactory
			.getLogger(DefaultDrawingView.class);

	private volatile int page = 1;
	
	private final AtomicLong commandIdGenerator=new AtomicLong();

	private ConcurrentHashMap<Integer, ConcurrentSkipListSet<BroadcastCommand>> pages = new ConcurrentHashMap<Integer, ConcurrentSkipListSet<BroadcastCommand>>();
	
	private ConcurrentHashMap<Integer, BroadcastStateCommand> stateCommands = new ConcurrentHashMap<Integer, BroadcastStateCommand>();

	private String id,displayName;
	
	private transient Object draggingLock=new Object();
	
	private transient volatile boolean dragging = false;
	
	private transient LinkedBlockingQueue<Updater<View>> waitQueue = new LinkedBlockingQueue<Updater<View>>();
	
	private PageQuickAccessPanel pageQuickAccessPanel=new PageQuickAccessPanel();
	
	private transient Thread exec=new Thread(){
		
		{
			setDaemon(true);
			setName("MovedUpdaterExecutor");
		}

		@Override
		public void run() {
			try{
				while(!isInterrupted()){
					Updater<View> u = waitQueue.take();
					synchronized(draggingLock){
						while(dragging){
							draggingLock.wait(300);
						}
					}
					u.change(DefaultDrawingView.this);
					logger.info("Executed {}",u);
				}
			}catch(InterruptedException e){
			}
		}
		
	};
	
	{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				exec.start();
			}
		});
	}
	
	@Override
	public boolean execute(BroadcastCommand command) {
		boolean accepted = commandIdGenerator.compareAndSet(command.getCommandId()-1, command.getCommandId());
		if(!accepted){
			logger.info("Ignored Command {} current Id is {}",command,commandIdGenerator.get());
			return false;
		}
		
		executeWithoutCheck(command);
		return true;
	}
	
	@Override
	public void executeWithoutCheck(BroadcastCommand command) {
		int pn = command.getPage();
		Updater<View> updater = command.peekUpdater();
		boolean changePage=ChangePage.class.isInstance(updater);
		boolean transform=isTransform(updater);
		boolean execute;
		
		
		synchronized (pages) {
			if ((execute=(changePage||page == pn))) {
				if(updater==null){
					updater=command.getUpdater();
				}
				if(updater!=null){
					synchronized(draggingLock){
						//不在移动figure或移动类操作
						if(!dragging||transform){
							updater.change(this);
						}else{
							waitQueue.offer(updater);
						}
					}
				}
			}
		}
		
		if(!execute||(!changePage&&!transform)){
			ConcurrentSkipListSet<BroadcastCommand> pageCommands = getPageCommands(pn);
			pageCommands.add(command);
		}
		logger.debug("DefaultDrawingView executed command {}", command);
	}
	
	/**
	 * 判断updater是否移动类操作
	 * @param updater
	 * @return true 当updater是移动类操作
	 */
	public boolean isTransform(Updater<?> updater) {
		return updater instanceof TransformFigures
				|| updater instanceof Transform
				|| updater instanceof ChangeBounds
				|| updater instanceof Transforms;
	}
	
	@Override
	public void dragging(){
		dragging=true;
	}
	@Override
	public void dragged(){
		synchronized(draggingLock){
			dragging=false;
			draggingLock.notifyAll();
		}
	}

	public SVGPanel getParentPanel() {
		return parentPanel;
	}

	public boolean isDoc() {
		return doc;
	}
	
	private static class BroadcastCommandOrderComparator implements Comparator<BroadcastCommand>,Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(BroadcastCommand o1, BroadcastCommand o2) {
			long l = o1.getCommandId()-o2.getCommandId();
			if(l>0)return 1;
			else if(l==0)return 0;
			else return -1;
		}
		
	}
	
	@Override
	public ConcurrentSkipListSet<BroadcastCommand> getPageCommands(int page) {
		pageQuickAccessPanel.setPagecount(page);
		ConcurrentSkipListSet<BroadcastCommand> pageCommands = pages.get(page);
		if (pageCommands == null) {
			pageCommands = new ConcurrentSkipListSet<BroadcastCommand>(new BroadcastCommandOrderComparator());
			ConcurrentSkipListSet<BroadcastCommand> result = pages.putIfAbsent(
					page, pageCommands);
			if (result != null) {
				pageCommands = result;
			}
		}
		return pageCommands;
	}

	@Override
	public void addCommandToPage(int page, BroadcastCommand command) {
		if (isTransform(command.getUpdater())) {
			return;
		}
		getPageCommands(page).add(command);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public int getPage() {
		return page;
	}
	
	@Override
	public int getPagecount(){
		return pageQuickAccessPanel.getPagecount();
	}

	@Override
	public void setPage(int page,boolean broadcast) {
		if(broadcast){
			Client.getInstance().broadcastState(new ChangePage(page));
		}else{
			synchronized(pages){
				if (this.page == page || page < 1) {
					return;
				}
				this.page = page;
				loadPage(page);
			}
		}
	}
	
	private void loadPage(int page){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DefaultDrawingView.this.clearSelection();
				DefaultDrawingView.this.clear();
			}
		});
		
		LinkedList<BroadcastCommand> needToDelete=null;
		for (BroadcastCommand c : getPageCommands(page)) {
			Updater<View> updater = c.getUpdater();
			updater.change(this);
			if(!isNeedToRedraw(updater)){
				if(needToDelete==null){
					needToDelete = new LinkedList<BroadcastCommand>();
				}
				needToDelete.add(c);
			}
			
		}

		if(doc)
			pageQuickAccessPanel.setPage(page);
		
		if(needToDelete!=null){
			getPageCommands(page).removeAll(needToDelete);
		}
	}
	
	@Override
	public void refreshPage(int page){
		synchronized(pages){
			if(this.page==page){
				loadPage(page);
			}
		}
	}
	
	@Override
	public void setPage(int page) {
		setPage(page,true);
	}
	
	@Override
	public boolean isNeedToRedraw(Updater<?> updater){
		return updater instanceof RemoteFigure||updater instanceof Delete||updater instanceof RemoteFigures;
	}

	@Override
	public void nextPage() {
		setPage(page + 1);
	}

	@Override
	public void prePage() {
		if (page == 1)
			return;
		setPage(page - 1);
	}

	@Override
	public void firstPage() {
		setPage(1);
	}

	@Override
	public void lastPage() {
		setPage(pageQuickAccessPanel.getPagecount());
	}

	public void clear() {
		this.getDrawing().removeAll(this.getDrawing().getFigures());
	}

	public AtomicLong getCommandIdGenerator() {
		return commandIdGenerator;
	}

	@Override
	public long getFutureCommandId(){
		return commandIdGenerator.get() + 1;
	}
	
	@Override
	public boolean acceptCommandId(long commandId) {
		return commandIdGenerator.compareAndSet(commandId-1, commandId);
	}
	
	@Override
	public Map<Integer, Long> getStateCommandIds() {
		HashMap<Integer, Long> result = new HashMap<Integer,Long>();
		for(Entry<Integer, BroadcastStateCommand> e:stateCommands.entrySet()){
			result.put(e.getKey(), e.getValue().getCommandId());
		}
		return result;
	}
	
	private void executeState(BroadcastCommand command, Integer stateType){
		synchronized(stateCommands){
			if(stateCommands.get(stateType).equals(command))
				executeWithoutCheck(command);
		}
	}

	@Override
	public boolean acceptState(BroadcastStateCommand command) {
		Integer stateType = command.getStateType();
		BroadcastStateCommand pre = stateCommands.get(stateType);
		if(pre==null){
			pre = stateCommands.putIfAbsent(stateType, command);
			if(pre==null){
				executeState(command,stateType);
				return true;
			}
		}
		while(pre.getCommandId() < command.getCommandId()){
			boolean replaced = stateCommands.replace(stateType, pre, command);
			if(replaced){
				executeState(command,stateType);
				return true;
			}
			pre = stateCommands.get(stateType);
		}
		logger.warn("Ignored StateCommand {},pre {}",command.getCommandId(),pre.getCommandId());
		return false;
	}

	public PageQuickAccessPanel getPageQuickAccessPanel() {
		return pageQuickAccessPanel;
	}

	public void dispose(){
		exec.interrupt();
	}

	public void setCommandId(long commandId) {
		this.commandIdGenerator.set(commandId);
	}
	
	public boolean hasSVGImage(){
		try{
			for(Figure f:getDrawing().getFigures()){
				if(f instanceof SVGImage)
					return true;
			}
			return false;
		}catch(Exception e){
			return hasSVGImage();
		}
	}
}
