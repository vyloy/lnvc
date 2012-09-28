package com.lorent.lvmc.camera;

public class PanTiltDrive {
	public static final byte SENDER_HEADER=(byte) 0x80; 

	public byte[] up(int address,int panSpeed,int tiltSpeed){
		return new byte[] { getSenderHeader(address), 0x01, 0x06, 0x01,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), 0x03, 0x01, (byte) 0xff };
	}
	
	public byte[] down(int address,int panSpeed,int tiltSpeed){
		return new byte[] { getSenderHeader(address), 0x01, 0x06, 0x01,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), 0x03, 0x02, (byte) 0xff };
	}
	
	public byte[] left(int address,int panSpeed,int tiltSpeed){
		return new byte[] { getSenderHeader(address), 0x01, 0x06, 0x01,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), 0x01, 0x03, (byte) 0xff };
	}
	
	public byte[] right(int address,int panSpeed,int tiltSpeed){
		return new byte[] { getSenderHeader(address), 0x01, 0x06, 0x01,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), 0x02, 0x03, (byte) 0xff };
	}
	
	public byte[] stop(int address){
		return new byte[] { getSenderHeader(address), 0x01, 0x06, 0x01,
				0x00, 0x00, 0x03, 0x03, (byte) 0xff };
	}
	
	public byte[] zoomWide(int address){
		return new byte[] { getSenderHeader(address), 0x01, 0x04, 0x07, 0x03, (byte) 0xff };
	}
	
	public byte[] zoomTele(int address){
		return new byte[] { getSenderHeader(address), 0x01, 0x04, 0x07, 0x02, (byte) 0xff };
	}
	
	public byte[] zoomStop(int address){
		return new byte[] { getSenderHeader(address), 0x01, 0x04, 0x07, 0x00, (byte) 0xff };
	}
	
	public byte[] reset(int address){
		return new byte[] { getSenderHeader(address), 0x01, 0x06, 0x05, (byte) 0xff };
	}
	
	public static byte getSenderHeader(int address){
		return (byte) (0xff&address|SENDER_HEADER);
	}
	
	public byte getPanSpeed(int panSpeed){
		return (byte) (0xff & panSpeed);
	}
	
	public byte getTiltSpeed(int tiltSpeed){
		return (byte) (0xff & tiltSpeed);
	}
	
	public int getMinPanSpeed(){
		return 1;
	}
	
	public int getMaxPanSpeed(){
		return 18;
	}
	
	public int getDefaultPanSpeed(){
		return getMaxPanSpeed()/2;
	}
	
	public int getMinTiltSpeed(){
		return 1;
	}
	
	public int getMaxTiltSpeed(){
		return 17;
	}
	
	public int getDefaultTiltSpeed(){
		return getMaxTiltSpeed()/2;
	}

	@Override
	public String toString() {
		return "Sony D70/D70P";
	}
	
	
}
