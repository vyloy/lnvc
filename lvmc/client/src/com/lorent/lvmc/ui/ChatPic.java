package com.lorent.lvmc.ui;
import java.net.URL;
import javax.swing.ImageIcon;
public class ChatPic extends ImageIcon{
/**
 *图片描述 
 */
private static final long serialVersionUID = 1L;
int im;//图片代号
String pictext;//图片代号

public int getIm() {
return im;
}
public void setIm(int im) {
this.im = im;
}
public ChatPic(URL url,int im){
super(url);
this.im = im;
}

    public String getPictext() {
        return pictext;
    }

    public void setPictext(String pictext) {
        this.pictext = pictext;
    }

}