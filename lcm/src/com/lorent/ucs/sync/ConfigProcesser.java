package com.lorent.ucs.sync;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigProcesser {
	private TimeUnit timeUnit;
	private long initialDelay;
	private long delay;
	
	private ConfigProcesser() {
	}

	public ConfigProcesser(TimeUnit timeUnit, long initialDelay, long delay) {
		this.timeUnit = timeUnit;
		this.initialDelay = initialDelay;
		this.delay = delay;
	}
	
	public static ConfigProcesser parse(String t){
		try{
			if(t==null||t.isEmpty())
				return null;
			Pattern pattern = Pattern.compile("(\\d+),(\\d+),([dhms])");
			Matcher matcher = pattern.matcher(t);
			boolean result = matcher.matches();
			if(!result){
				return null;
			}
			ConfigProcesser r = new ConfigProcesser();
			r.initialDelay=Integer.parseInt(matcher.group(1));
			r.delay=Integer.parseInt(matcher.group(2));
			if(r.initialDelay<1&&r.delay<1)
				return null;
			switch(matcher.group(3).charAt(0)){
			case 'd':
				r.timeUnit=TimeUnit.DAYS;
				break;
			case 'h':
				r.timeUnit=TimeUnit.HOURS;
				break;
			case 'm':
				r.timeUnit=TimeUnit.MINUTES;
				break;
			case 's':
				r.timeUnit=TimeUnit.SECONDS;
				break;
			}
			return r;
			
		}catch(Exception e){
			return null;
		}
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public long getInitialDelay() {
		return initialDelay;
	}

	public long getDelay() {
		return delay;
	}
	
	@Override
	public String toString() {
		return "ConfigProcesser [timeUnit=" + timeUnit + ", initialDelay=" + initialDelay
				+ ", delay=" + delay + "]";
	}

}
