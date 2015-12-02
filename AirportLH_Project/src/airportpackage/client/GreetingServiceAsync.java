package airportpackage.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import airportpackage.shared.Aircraft;
import airportpackage.shared.Crew;
import airportpackage.shared.Fleet;
import airportpackage.shared.Staff;
import airportpackage.shared.Timetable;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	/**
	 * Registration proc
	 * @param login - user login
	 * @param password - user password 
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void registration(String login, String password, AsyncCallback<String> asyncCallback);

	
	/**
	 * new user creation
	 * @param login - user login
	 * @param password - user password
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void newUser(String login, String password, AsyncCallback<String> asyncCallback);

	
	/**
	 * Getting all airline's staff
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getStaff(AsyncCallback<List<Staff>> asyncCallback);

	
	/**
	 * Getting all airline's crews
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getCrewList(AsyncCallback<List<Staff>> asyncCallback);

	
	/** 
	 * Getting all airlines' aircrafts
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getAircraft(AsyncCallback<List<Aircraft>> asyncCallback);

	
	/**
	 * Getting all airline's fleet
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getFleet(AsyncCallback<List<Aircraft>> asyncCallback);

	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * исключая неназначенные рейсы
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getTimetableDetails(AsyncCallback<List<Timetable>> asyncCallback);

	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * включая неназначенные рейсы
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getTimetableDetailsAll(AsyncCallback<List<Timetable>> asyncCallback);

	
	/**
	 * Update flight crew into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void updateTimetableByFlightNumber(Timetable timetable, AsyncCallback<String> asyncCallback);

	
	/**
	 * Get staff by Position from table STAFF, database AIRPORT
	 * @param position - user position
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getStaffByPosition(String position, AsyncCallback<List<Staff>> asyncCallback);

	
	/**
	 * Get all fleet from table FLEET, database AIRPORT
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getAerofleet(AsyncCallback<List<Fleet>> asyncCallback);

	
	/**
	 * Get all staff by staffId from table STAFF, database AIRPORT
	 * @param valueCrew - crewId
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getCrewListById(int valueCrew, AsyncCallback<List<Staff>> asyncCallback);

	
	/**
	 * Get crew from table CREWS, database AIRPORT
	 * @param forwardCrewId - crewId
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getCrewById(int forwardCrewId, AsyncCallback<Crew> asyncCallback);

	
	/**
	 * Update crew into table CREWS, database AIRPORT
	 * @param crew - object Crew
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void updateCrew(Crew crew, AsyncCallback<String> asyncCallback);

	/**
	 * Update CREW_ID_REF to 1 or 0 into table STAFF, database AIRPORT
	 * @param flag - employee status: in crew or not in crew
	 * @param forwardStaffIdNew - employee Id
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void updateFlag(int forwardStaffIdNew, boolean flag, AsyncCallback<String> asyncCallback);

	/**
	 * Delete flight by flightNumber from table TIMETABLE, database AIRPORT
	 * @param flightNumber - flight number
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void deleteTimetableByFlightNumber(String flightNumber, AsyncCallback<String> asyncCallback);

	/**
	 * Delete crew by crewId from table CREW, database AIRPORT
	 * @param crewId - crewId
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void deleteCrewById(int crewId, AsyncCallback<String> asyncCallback);

	/**
	 * Insert crew by crewId into table CREW, database AIRPORT
	 * @param crew - object Crew
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void insertCrew(Crew crew, AsyncCallback<String> asyncCallback);

	/**
	 * Get template crew from table CREW, database AIRPORT
	 * @param asyncCallback - handler to handle RPC callback in which server returns the Message back to client
	 */
	void getCrew(AsyncCallback<String> asyncCallback);

	

}
