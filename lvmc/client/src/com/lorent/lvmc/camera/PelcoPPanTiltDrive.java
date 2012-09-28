package com.lorent.lvmc.camera;

public class PelcoPPanTiltDrive extends PanTiltDrive {
	public static final byte HEADER = (byte) 0xa0;
	public static final byte ENDER = (byte) 0xaf;

	@Override
	public byte[] up(int address, int panSpeed, int tiltSpeed) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, 0x08,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), ENDER, 0x00 });
	}

	@Override
	public byte[] down(int address, int panSpeed, int tiltSpeed) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, (byte) 0x10,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), ENDER, 0x00 });
	}

	@Override
	public byte[] left(int address, int panSpeed, int tiltSpeed) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, (byte) 0x04,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), ENDER, 0x00 });
	}

	@Override
	public byte[] right(int address, int panSpeed, int tiltSpeed) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, (byte) 0x02,
				getPanSpeed(panSpeed), getTiltSpeed(tiltSpeed), ENDER, 0x00 });
	}

	@Override
	public byte[] stop(int address) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, 0x00,
				0x00, 0x00, ENDER, 0x00 });
	}
	
	@Override
	public byte[] zoomWide(int address) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, 0x40,
				0x00, 0x00, ENDER, 0x00 });
	}

	@Override
	public byte[] zoomTele(int address) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, 0x20,
				0x00, 0x00, ENDER, 0x00 });
	}

	@Override
	public byte[] reset(int address) {
		return appendMask(new byte[] { HEADER, getAddress(address), 0x00, 0x07,
				0x00, 0x22, ENDER, 0x00 });
	}

	@Override
	public byte[] zoomStop(int address) {
		return stop(address);
	}

	public byte getAddress(int address){
		return (byte)(0xff&address);
	}
	
	public byte[] appendMask(byte[] data){
		byte mask=data[0];
		for(int i=1;i<7;i++){
			mask^=data[i];
		}
		data[7]=mask;
		return data;
	}

	@Override
	public int getMinPanSpeed() {
		return 1;
	}

	@Override
	public int getMaxPanSpeed() {
		return 63;
	}

	@Override
	public int getMinTiltSpeed() {
		return 1;
	}

	@Override
	public int getMaxTiltSpeed() {
		return 63;
	}

	@Override
	public String toString() {
		return "Pelco P";
	}

}
