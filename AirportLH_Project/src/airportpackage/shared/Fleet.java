package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin
 * Класс парк самолетов
 * Fleet class
 */ 

/**	
 *  fleetId - ID порядковый
 *  aircraftRegId - регистрационный ID самолета
 *  aircraftId - ID типа самолета
 *  icaoAddr - ICAO адрес самолета
 */
public class Fleet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int fleetId;
	private String aircraftRegId;
	private int aircraftId;
	private String icaoAddr;

	private int aircraftName;
	
//	Default constructor
	
	public int getAircraftName() {
		return aircraftName;
	}
	public void setAircraftName(int aircraftName) {
		this.aircraftName = aircraftName;
	}
	//	Getters and setters
	public String getAircraftRegId() {
		return aircraftRegId;
	}
	public void setAircraftRegId(String aircraftRegId) {
		this.aircraftRegId = aircraftRegId;
	}
	public int getAircraftId() {
		return aircraftId;
	}
	public void setAircraftId(int aircraftId) {
		this.aircraftId = aircraftId;
	}
	public String getIcaoAddr() {
		return icaoAddr;
	}
	public void setIcaoAddr(String icaoAddr) {
		this.icaoAddr = icaoAddr;
	}
	public int getFleetId() {
		return fleetId;
	}
	public void setFleetId(int fleetId) {
		this.fleetId = fleetId;
		
	}
}
