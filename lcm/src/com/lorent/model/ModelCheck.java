package com.lorent.model;

import com.lorent.exception.ModelCheckFailException;

public interface ModelCheck {
	boolean checkModel()throws ModelCheckFailException;
}
