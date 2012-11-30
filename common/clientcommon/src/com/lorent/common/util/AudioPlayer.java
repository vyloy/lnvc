package com.lorent.common.util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent.Type;

import org.apache.log4j.Logger;

public class AudioPlayer {
	private Logger log = Logger.getLogger(AudioPlayer.class);

	private URL url;

	public AudioPlayer(URL url) {
		try {
			this.url = url;
		} catch (Exception e) {
			log.error("construct error", e);
		}
	}

	public void stop() {
		this.repeat = false;
		if(clip!=null)
			clip.stop();
	}

	public void start(boolean repeat, long repeatInterval) {
		this.repeat = repeat;
		this.repeatInterval = repeatInterval;
		new PlayThread().start();
	}

	private Clip clip;

	private boolean repeat;
	private long repeatInterval;

	private class PlayThread extends Thread {

		private class AudioListener implements LineListener {
			private boolean done = false;

			@Override
			public synchronized void update(LineEvent event) {
				Type eventType = event.getType();
				if (eventType == Type.STOP || eventType == Type.CLOSE) {
					done = true;
					notifyAll();
				}
			}

			public synchronized void waitUntilDone()
					throws InterruptedException {
				while (!done) {
					wait();
				}
			}
		}

		@Override
		public void run() {
			do {
				AudioListener listener = new AudioListener();
				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(url);
//					System.out.println( "frame length: " + audioInputStream.getFrameLength() );
//					System.out.println( "frame size: " + audioInputStream.getFormat() );
					clip = AudioSystem.getClip();
					clip.addLineListener(listener);
					clip.open(audioInputStream);

					clip.start();
					listener.waitUntilDone();
				} catch (Exception e) {
					log.error("PlayThread", e);
				} finally {
					clip.close();
					try {
						audioInputStream.close();
						if(repeatInterval != 0){
							Thread.sleep(repeatInterval);
						}
					} catch (Exception e) {
						log.error("PlayThread", e);
					}
				}
			} while (repeat);
		}

	}

	public static void main(String[] args) throws Exception {
//		AudioPlayer test = new AudioPlayer(AudioPlayer.class.getResource("/com/rgm/test/close.wav"));
//		test.start(false);
//		Thread.sleep(20000);
//		System.out.println("time out");
//		test.stop();

	}
}
