package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin
 * Класс аэропорт
 * Airport class
 */ 

/**	
 *  airportId- id аэропорта
 *  airportName - название аэропорта
 *  iata - код аэропорта IATA
 */
public class Airport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int airportId;
	private String airportName;
	private String iata;
	
//	Default constructor
	
//	Getters and setters
	public int getAirportId() {
		return airportId;
	}
	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}

}
