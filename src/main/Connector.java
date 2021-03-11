package main;

import com.etas.vrta.comms.*;

public class Connector {
	
	
	static final String host="127.0.0.1";
	
	static final String ecu_path = "C:\\ETASData\\ISOLAR-EVE3.0\\workspace\\HBS\\HBS\\VECUs\\MinGW\\Bin\\HBS.exe";
	
	private String VideoSensor = "VideoSensor.Value";
	private String ButtonSensor = "ButtonSensor.Value";
	private String SoundSettingSensor = "SoundSettingSensor.Value";
	private String DisplaySettingSensor = "DisplaySettingSensor.Value";
	private String SeatSettingSensor = "SeatSettingSensor.Value";
	private String WiperSensor = "WiperSensor.Value";
	private String VelocitySensor = "VelocitySensor.Value";
	private String DirectSensor = "DirectSensor.Value";
	
	private String SoundActuator = "SoundActuator.Value";
	private String DisplayActuator = "DisplayActuator.Value";
	private String SeatActuator = "SeatActuator.Value";
	
	
	protected VECUConnection m_connection;
	
	
	public void setUp() throws Exception {
		m_connection = VECUFinder.attach(host,ecu_path);
		m_connection.start();
	}
	
	public void sendVideoSensorValue(Object Accel){
		m_connection.action(VideoSensor).send(Accel);
	}
	public void sendButtonSensorValue(Object Accel){
		m_connection.action(ButtonSensor).send(Accel);
	}
	
	//setting 애들은 외부값
	public void sendDisplaySettingSensorValue(Object Accel){
		m_connection.action(DisplaySettingSensor).send(Accel);
	}
	public void sendSeatSettingSensorValue(Object Accel){
		m_connection.action(SeatSettingSensor).send(Accel);
	}
	public void sendSoundSettingSensorValue(Object Accel){
		m_connection.action(SoundSettingSensor).send(Accel);
	}
	
	public void sendWiperSensorValue(Object Accel){
		m_connection.action(WiperSensor).send(Accel);
	}
	public void sendVelocitySensorValue(Object Accel){
		m_connection.action(VelocitySensor).send(Accel);
	}
	public void sendDirectSensorValue(Object Accel){
		m_connection.action(DirectSensor).send(Accel);
	}
	
	public Object getSoundsetActuator() {
		return m_connection.event(SoundActuator).state();
	}
	public Object getDisplaysetActuator() {
		return m_connection.event(DisplayActuator).state();
	}
	public Object getSeatsetActuator() {
		return m_connection.event(SeatActuator).state();
	}

	
	
}
