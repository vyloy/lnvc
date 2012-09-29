package com.lorent.lvmc.camera;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;

public class CameraController {
	private OutputStream out;
	private InputStream in;
	private CommPort commPort;
	private PanTiltDrive panTiltDrive;
	
	public CameraController(PanTiltDrive panTiltDrive) {
		this.panTiltDrive = panTiltDrive;
	}

	public void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			throw new RuntimeException("Error: Port is currently in use");
		} else {
			commPort = portIdentifier.open(this.getClass().getName(),
					10);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				in = serialPort.getInputStream();
				out = serialPort.getOutputStream();

			} else {
				throw new RuntimeException("Error: Only serial ports are handled by this example.");
			}
		}
	}
	
	public void close(){
		if(commPort!=null)
			commPort.close();
	}
	
	public void up(int address,int panSpeed,int tiltSpeed) throws IOException{
		write(panTiltDrive.up(address, panSpeed, tiltSpeed));
	}
	
	public void down(int address,int panSpeed,int tiltSpeed) throws IOException{
		write(panTiltDrive.down(address, panSpeed, tiltSpeed));
	}
	
	public void left(int address,int panSpeed,int tiltSpeed) throws IOException{
		write(panTiltDrive.left(address, panSpeed, tiltSpeed));
	}
	
	public void right(int address,int panSpeed,int tiltSpeed) throws IOException{
		write(panTiltDrive.right(address, panSpeed, tiltSpeed));
	}
	
	public void zoomWide(int address) throws IOException{
		write(panTiltDrive.zoomWide(address));
	}
	
	public void zoomTele(int address) throws IOException{
		write(panTiltDrive.zoomTele(address));
	}
	
	public void zoomStop(int address) throws IOException{
		write(panTiltDrive.zoomStop(address));
	}
	
	public void reset(int address) throws IOException{
		write(panTiltDrive.reset(address));
	}
	
	public void stop(int address) throws IOException{
		byte[] stop = panTiltDrive.stop(address);
		write(stop);
	}
	
	protected void write(byte[] data) throws IOException{
		out.write(data);
		out.flush();
	}
	
	public PanTiltDrive getPanTiltDrive() {
		return panTiltDrive;
	}

	public static HashSet<CommPortIdentifier> getAvailableSerialPorts(){
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
        while (thePorts.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
            switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				CommPort thePort;
				try {
					thePort = com.open("CommUtil", 50);
					thePort.close();
					h.add(com);
				} catch (PortInUseException e) {
					e.printStackTrace();
				}
			}
        }
        return h;
    }
}
