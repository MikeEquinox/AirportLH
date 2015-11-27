package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin
 * Класс список рейсов
 * Timetable class
 */ 

/**	
 *  timetableId - ID порядковый
 *  flightNumber - номер рейса
 *  airline - название авиакомпании
 *  routeId - ID маршрута
 *  aircraftRegId - регистрационный ID самолета
 *  crewId - ID летного состава
 *  departureDate - дата вылета
 *  departureTime - время вылета
 *  sourceAirport - аэропорт вылета
 *  arrivalDate - дата прибытия
 *  arrivalTime - время прибытия
 *  destinationAirport - аэропорт прибытия
 */
public class Timetable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int timetableId;
	private String flightNumber; 
	private String airline;
	private int routeId; 
	private int aircraftRegId; 
	private int crewId;
	private String departureDate;
	private String departureTime;
	private String sourceAirport;
	private String arrivalDate;
	private String arrivalTime;
	private String destinationAirport;
	
//	Default constructor
	
//	Getters and setters
	public int getTimetableId() {
		return timetableId;
	}
	
	public void setTimetableId(int timetableId) {
		this.timetableId = timetableId;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getAirline() {
		return airline;
	}
	
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	public int getRouteId() {
		return routeId;
	}
	
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	
	public int getAircraftRegId() {
		return aircraftRegId;
	}
	
	public void setAircraftRegId(int aircraftRegId) {
		this.aircraftRegId = aircraftRegId;
	}
	
	public int getCrewId() {
		return crewId;
	}
	
	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	
	public String getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public String getSourceAirport() {
		return sourceAirport;
	}
	
	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}
	
	public String getArrivalDate() {
		return arrivalDate;
	}
	
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDestinationAirport() {
		return destinationAirport;
	}
	
	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
		
}
