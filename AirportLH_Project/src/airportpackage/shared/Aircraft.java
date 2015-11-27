package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin
 * Класс самолеты
 * Aircraft class
 */ 

/**	
 *  aircraftName - название самолета
 *  length - длина самолета
 *  wingspan - размах крыльев
 *  height -высота самолета
 *  maxTakeoffWeight - макс. грузоподъемность
 *  maxCruiseSpeed - макс. крейсерская скорость
 *  maxCruiseAltitude - макс. высота полета
 *  flightRange - дальность полета
 *  engines - тип двигателей
 *  seats - места
 */
public class Aircraft implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int aircraftId;
	private String aircraftName;
	private float length;
	private float wingspan;
	private float height;
	private int maxTakeoffWeight;
	private int maxCruiseSpeed;
	private int maxCruiseAltitude;
	private int flightRange;
	private String engines;
	private String seats;
	
//	Default constructor
	
//	Getters and setters
	public int getAircraftId() {
		return aircraftId;
	}
	public void setAircraftId(int aircraftId) {
		this.aircraftId = aircraftId;
	}
	public String getAircraftName() {
		return aircraftName;
	}
	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public float getWingspan() {
		return wingspan;
	}
	public void setWingspan(float wingspan) {
		this.wingspan = wingspan;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getMaxTakeoffWeight() {
		return maxTakeoffWeight;
	}
	public void setMaxTakeoffWeight(int maxTakeoffWeight) {
		this.maxTakeoffWeight = maxTakeoffWeight;
	}
	public int getMaxCruiseSpeed() {
		return maxCruiseSpeed;
	}
	public void setMaxCruiseSpeed(int maxCruiseSpeed) {
		this.maxCruiseSpeed = maxCruiseSpeed;
	}
	public int getMaxCruiseAltitude() {
		return maxCruiseAltitude;
	}
	public void setMaxCruiseAltitude(int maxCruiseAltitude) {
		this.maxCruiseAltitude = maxCruiseAltitude;
	}
	public int getFlightRange() {
		return flightRange;
	}
	public void setFlightRange(int flightRange) {
		this.flightRange = flightRange;
	}
	public String getEngines() {
		return engines;
	}
	public void setEngines(String engines) {
		this.engines = engines;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	
}
