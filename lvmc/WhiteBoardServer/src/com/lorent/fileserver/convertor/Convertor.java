package com.lorent.fileserver.convertor;

import java.io.File;
import java.io.IOException;

public interface Convertor {

	void open();
	void convert(File file,Converted converted) throws IOException;
	void close();
}