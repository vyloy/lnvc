package com.lorent.vovo.dto;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class FontStyle implements Serializable{
	private String name = "宋体";
	private Integer size = 14;
	private Boolean bold = false;
	private Boolean italic = false;
	private Boolean underLine = false;
	private Color color = Color.BLACK;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Boolean getBold() {
		return bold;
	}
	public void setBold(Boolean bold) {
		this.bold = bold;
	}
	public Boolean getItalic() {
		return italic;
	}
	public void setItalic(Boolean italic) {
		this.italic = italic;
	}
	public Boolean getUnderLine() {
		return underLine;
	}
	public void setUnderLine(Boolean underLine) {
		this.underLine = underLine;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}	
	
	public FontStyle(){}
	
	public FontStyle(String name, Integer size, Boolean bold, Boolean italic,
			Boolean underLine, Color color) {
		this.name = name;
		this.size = size;
		this.bold = bold;
		this.italic = italic;
		this.underLine = underLine;
		this.color = color;
	}
	public Font getFont(){
//		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
//		if(underLine){
//			fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//		}
		int i = 0;
		if(bold){
			i = i | Font.BOLD;
		}
		if(italic){
			i = i | Font.ITALIC;
		}
		Font font = new Font(name, i, size + 3);
		return font;
	}
	
	public SimpleAttributeSet getAttrSet() {
		SimpleAttributeSet attrSet = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attrSet, name);
		StyleConstants.setBold(attrSet, bold);
		StyleConstants.setItalic(attrSet, italic);
		StyleConstants.setUnderline(attrSet, underLine);
		StyleConstants.setFontSize(attrSet, size + 3);
		StyleConstants.setForeground(attrSet, color);
		return attrSet;
	}
	
}
