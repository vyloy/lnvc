package com.lorent.vovo.util;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

public class CountTime extends Thread {
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private JLabel label;
	private boolean flag = true;
	private Logger log = Logger.getLogger(CountTime.class);
	
	public CountTime(JLabel label) {
		this.label = label;
		this.label.setText("00:00:00");
	}

	@Override
	public void run() {
		while (flag) {
			String temp = "";
			if (second == 59) {
				if (minute == 59) {
					hour++;
					minute = 0;
				} else {
					minute++;
				}
				second = 0;
			} else {
				second++;
			}
			if (hour < 10) {
				temp = "0" + hour;
			} else {
				temp = hour + "";
			}
			temp = temp + ":";
			if (minute < 10) {
				temp = temp + "0" + minute;
			} else {
				temp = temp + minute + "";
			}
			temp = temp + ":";
			if (second < 10) {
				temp = temp + "0" + second;
			} else {
				temp = temp + second + "";
			}
			this.label.setText(temp);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error("CountTime", e);
				flag = false;
			}
		}
	}

	public void cancel() {
		flag = false;
	}

}
