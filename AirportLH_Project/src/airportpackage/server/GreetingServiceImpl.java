package airportpackage.server;

import airportpackage.client.GreetingService;
import airportpackage.shared.Aircraft;
import airportpackage.shared.AircraftDAO;
import airportpackage.shared.Crew;
import airportpackage.shared.CrewDAO;
import airportpackage.shared.Fleet;
import airportpackage.shared.FleetDAO;
import airportpackage.shared.Staff;
import airportpackage.shared.StaffDAO;
import airportpackage.shared.Timetable;
import airportpackage.shared.TimetableDAO;
import airportpackage.shared.User;
import airportpackage.shared.UserDAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	/**
	 * Create server logger
	 */
	final static Logger serverLogger = Logger.getLogger("serverLogger");

	public String greetServer(String input) throws IllegalArgumentException {

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * 
	 */
	@Override
	public String registration(String login, String password) {

		serverLogger.log(Level.INFO, "GreetingServiceImpl, UserDAO, method registration(String login, String password)");
		serverLogger.log(Level.INFO, "GreetingServiceImpl, method registration(), entered login: " + login);

		UserDAO userDAO = new UserDAO();
		List<User> user = userDAO.getUsers();

		for (User user2 : user) {
			if (login.equals(user2.getLogin())) {

				if (password.equals(user2.getPassword())) {
					serverLogger.log(Level.INFO, "GreetingServiceImpl, method registration(), getting rights: " + user2.getRights());
					return user2.getRights();
				} else
					serverLogger.log(Level.INFO, "GreetingServiceImpl, method registration(), entered wrong password ");
			}
		}
		serverLogger.log(Level.INFO, "GreetingServiceImpl, method registration(), entered wrong login or password ");
		return "";

	}

	/**
	 * 
	 */
	@Override
	public String newUser(String login, String password) {

		serverLogger.log(Level.INFO, "GreetingServiceImpl, UserDAO, method newUser(login, password)");
		serverLogger.log(Level.INFO, "GreetingServiceImpl, method newUser(), entered login: " + login);
		
		UserDAO userDAO = new UserDAO();
		userDAO.newUser(login, password);

		return "inserted";
	}

	/**
	 * 
	 */
	@Override
	public List<Staff> getStaff() {

		StaffDAO staffDAO = new StaffDAO();
		List<Staff> staffList = staffDAO.getStaff();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, StaffDAO, method getStaff()");
		return staffList;
	}

	/**
	 * 
	 */
	@Override
	public List<Staff> getCrewList() {

		StaffDAO staffDAO = new StaffDAO();
		List<Staff> staffList = staffDAO.getCrewList();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, StaffDAO, method getCrewList()");

		return staffList;
	}

	/**
	 * 
	 */
	@Override
	public List<Aircraft> getAircraft() {

		AircraftDAO aircraftDAO = new AircraftDAO();
		List<Aircraft> aircraftList = aircraftDAO.getAircraft();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, for AircraftDAO, method getAircraft()");

		return aircraftList;
	}

	/**
	 * 
	 */
	@Override
	public List<Aircraft> getFleet() {

		AircraftDAO aircraftDAO = new AircraftDAO();
		List<Aircraft> aircraftList = aircraftDAO.getFleet();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, AircraftDAO, method getFleet()");

		return aircraftList;
	}

	/**
	 * 
	 */
	@Override
	public List<Timetable> getTimetableDetails() {

		TimetableDAO timetableDAO = new TimetableDAO();
		List<Timetable> timetableList = timetableDAO.getTimetableDetails();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, TimetableDAO, method getTimetableDetails()");

		return timetableList;
	}

	/**
	 * 
	 */
	@Override
	public List<Timetable> getTimetableDetailsAll() {

		TimetableDAO timetableDAO = new TimetableDAO();
		List<Timetable> timetableList = timetableDAO.getTimetableDetailsAll();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, TimetableDAO, method getTimetableDetailsAll()");

		return timetableList;
	}

	/**
	 * 
	 */
	@Override
	public String updateTimetableByFlightNumber(Timetable timetable) {

		TimetableDAO timetableDAO = new TimetableDAO();
		timetableDAO.updateTimetableByFlightNumber(timetable);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, TimetableDAO, method updateTimetableByFlightNumber()");

		return "";
	}

	/**
	 * 
	 */
	// @Override
	// public List<Staff> getCrewList() {
	//
	// StaffDAO staffDAO = new StaffDAO();
	// List<Staff> staffList = staffDAO.getCrewList();
	// serverLogger.log(Level.INFO, "Server calls for StaffDAO, method
	// getStaffByPosition(String position)");
	//
	// return staffList;
	// }

	/**
	 * 
	 */
	@Override
	public List<Fleet> getAerofleet() {

		FleetDAO fleetDAO = new FleetDAO();
		List<Fleet> fleetList = fleetDAO.getAerofleet();
		serverLogger.log(Level.INFO, "GreetingServiceImpl, FleetDAO, method getStaffByPosition(String position)");

		return fleetList;
	}

	/**
	 * 
	 */
	@Override
	public List<Staff> getCrewListById(int valueCrew) {

		StaffDAO staffDAO = new StaffDAO();
		List<Staff> staffList = staffDAO.getCrewListById(valueCrew);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, StaffDAO, method getCrewListByID(Integer valueCrew)");

		return staffList;
	}

	/**
	 * 
	 */
	@Override
	public List<Staff> getStaffByPosition(String valueStaff) {

		StaffDAO staffDAO = new StaffDAO();
		List<Staff> staffList = staffDAO.getStaffByPosition(valueStaff);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, StaffDAO, method getStaffByPosition(valueStaff)");

		return staffList;
	}

	/**
	 * 
	 */
	@Override
	public Crew getCrewById(int forwardCrewId) {
		
		CrewDAO crewDAO = new CrewDAO();
		Crew crew = crewDAO.getCrewById(forwardCrewId);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, CrewDAO, method getCrewById(forwardCrewId)");
		
		return crew;
	}

	/**
	 * 
	 */
	@Override
	public String updateCrew(Crew crew) {
	
		CrewDAO crewDAO = new CrewDAO();
		crewDAO.updateCrew(crew);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, CrewDAO, method updateCrew()");

		return "";
	}

	/**
	 * 
	 */
	@Override
	public String updateFlag(int forwardStaffId, boolean flag) {
		
		StaffDAO staffDAO = new StaffDAO();
		staffDAO.updateFlag(forwardStaffId, flag);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, StaffDAO, method updateStaffCrewIdRefB(int forwardStaffIdNew) ");

		return "";
	}

	/**
	 * 
	 */
	@Override
	public String deleteTimetableByFlightNumber(String flightNumber) {
		
		TimetableDAO timetableDAO = new TimetableDAO();
		timetableDAO.deleteTimetableByFlightNumber(flightNumber);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, TimetableDAO, method deleteTimetableByFlightNumber(String flightNumber)");

		return "";
	}

	/**
	 * 
	 */
	@Override
	public String deleteCrewById(int crewId) {
		
		CrewDAO crewDAO = new CrewDAO();
		crewDAO.deleteCrewById(crewId);
		serverLogger.log(Level.INFO, "GreetingServiceImpl, CrewDAO, method deleteCrewById(int crewId)");

		return "";
	}

}
