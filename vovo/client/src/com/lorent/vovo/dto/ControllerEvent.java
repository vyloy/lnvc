package com.lorent.vovo.dto;

import java.awt.Image;

public class ControllerEvent {
	private Image image;
	private String exClass;
	private String exMethod;
	private Object[] paras;
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

	public Object[] getParas() {
		return paras;
	}
	public void setParas(Object[] paras) {
		this.paras = paras;
	}
	public String getExClass() {
		return exClass;
	}
	public void setExClass(String exClass) {
		this.exClass = exClass;
	}
	public String getExMethod() {
		return exMethod;
	}
	public void setExMethod(String exMethod) {
		this.exMethod = exMethod;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		
		if(obj instanceof ControllerEvent){
			ControllerEvent c = (ControllerEvent)obj;
			if(c.exClass.equals(exClass) && c.exMethod.equals(exMethod)){
				if(c.paras.length == paras.length){
					int size = c.paras.length;
					boolean flag = true;
					for(int i = 0; i < size; i++){
						if(!c.paras[i].equals(paras[i])){
							flag = false;
							break;
						}
					}
					return flag;
				}
			}
		}
		return false;
	}
}
