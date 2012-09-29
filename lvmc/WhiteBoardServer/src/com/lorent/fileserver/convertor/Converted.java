package com.lorent.fileserver.convertor;

import java.awt.image.BufferedImage;
import java.io.File;

public interface Converted {
	void converted(BufferedImage image,File file,int page,int pageCount);
}
