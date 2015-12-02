package airportpackage.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import airportpackage.shared.Aircraft;
import airportpackage.shared.Crew;
import airportpackage.shared.Fleet;
import airportpackage.shared.Staff;
import airportpackage.shared.Timetable;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	/**
	 * Registration proc
	 * @param login - user login
	 * @param password - user password
	 * @return ""
	 */
	String registration(String login, String password);

	
	/**
	 * new user creation
	 * @param login - user login
	 * @param password - user password
	 * @return ""
	 */
	String newUser(String login, String password);
	
	
	/**
	 * Getting all airline's staff
	 * @return Staff list
	 */
	List<Staff> getStaff();

	
	/**
	 * Getting all airline's crews
	 * @return Staff list
	 */
	List<Staff> getCrewList();

	
	/**
	 * Getting all airlines' aircrafts
	 * @return Aircraft list
	 */
	List<Aircraft> getAircraft();

	
	/**
	 * Getting all airline's fleet
	 * @return Aircraft list
	 */
	List<Aircraft> getFleet();

	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * исключая неназначенные рейсы
	 * @return Timetable list
	 */
	List<Timetable> getTimetableDetails();

	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * включая неназначенные рейсы
	 * @return Timetable list
	 */
	List<Timetable> getTimetableDetailsAll();

	
	/**
	 * Update flight crew into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 * @return ""
	 */
	String updateTimetableByFlightNumber(Timetable timetable);

	
	/**
	 * Get staff by Position from table STAFF, database AIRPORT
	 * @param position - position
	 * @return Staff list
	 */
	List<Staff> getStaffByPosition(String position);

	
	/**
	 * Get all fleet from table FLEET, database AIRPORT
	 * @return Fleet list
	 */
	List<Fleet> getAerofleet();

	
	/**
	 * Get all staff by staffId from table STAFF, database AIRPORT
	 * @param valueCrew - crewId
	 * @return Staff list
	 */
	List<Staff> getCrewListById(int valueCrew);

	
	/**
	 * Get crew from table CREWS, database AIRPORT
	 * @param forwardCrewId - forwardCrewId
	 * @return Crew
	 */
	Crew getCrewById(int forwardCrewId);

	
	/**
	 * Update crew into table CREWS, database AIRPORT
	 * @param crew - object Crew
	 * @return ""
	 */
	String updateCrew(Crew crew);

	/**
	 * Update CREW_ID_REF to 1 or 0 into table STAFF, database AIRPORT
	 * @param forwardStaffId - staffId
	 * @param flag - flag
	 * @return ""
	 */
	String updateFlag(int forwardStaffId, boolean flag);

	/**
	 * Delete flight by flightNumber from table TIMETABLE, database AIRPORT
	 * @param flightNumber - flight number
	 * @return ""
	 */
	String deleteTimetableByFlightNumber(String flightNumber);

	/**
	 * Delete crew by crewId from table CREW, database AIRPORT
	 * @param crewId - crewId
	 * @return ""
	 */
	String deleteCrewById(int crewId);

	/**
	 * Insert crew by crewId into table CREW, database AIRPORT
	 * @param crew - object Crew
	 * @return ""
	 */
	String insertCrew(Crew crew);

	/**
	 * Get template crew from table CREW, database AIRPORT
	 * @return ""
	 */
	String getCrew();



}
