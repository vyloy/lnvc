package org.jhotdraw.samples.svg.command;

import org.jhotdraw.samples.svg.SVGPanels;
import org.jhotdraw.util.PicsGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.impl.GetImage;
import com.lorent.whiteboard.model.CommandsManager;

public class GetImageForPrint extends GetImage {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(GetImageForPrint.class);

	public GetImageForPrint(String meetingId, String whiteboardId, int page,
			int pageCount) {
		super(meetingId, whiteboardId, page, pageCount);
	}

	@Override
	public void run(CommandsManager manager) {
		SVGPanels m=(SVGPanels) manager;
		PicsGenerator generator = m.getPicsGenerator(whiteboardId);
		if(generator==null)
			return;
		generator.printImage(updater, page);
		logger.debug("generating image for page {}",page);
	}

	@Override
	public String toString() {
		return "GetImageForPrint [pageCount=" + pageCount + ", commandId="
				+ commandId + ", whiteboardId=" + whiteboardId + ", page="
				+ page + ", meetingId=" + meetingId + "]";
	}

}
