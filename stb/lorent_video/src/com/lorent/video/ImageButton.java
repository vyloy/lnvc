package com.lorent.video;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageButton extends LinearLayout {

	private ImageView mImage;

	private TextView mText;
	
	private int imageR;

	public ImageButton(Context context, AttributeSet attrs) {

		super(context, attrs);

		mImage = new ImageView(context, attrs);

		mImage.setPadding(0, 0, 0, 0);

		mText = new TextView(context, attrs);

		mText.setGravity(android.view.Gravity.CENTER_HORIZONTAL);

		mText.setPadding(0, 0, 0, 0);

		setClickable(true);

		setFocusable(true);

		setBackgroundResource(android.R.drawable.btn_default);

		setOrientation(LinearLayout.VERTICAL);

		addView(mImage);

		addView(mText);

	}
	
	public void setText(String str){
		mText.setText(str);
	}
	
	public void setText(int rid){
		mText.setText(rid);
	}
	
	public void setIcon(int rid){
		imageR = rid;
	}
}
