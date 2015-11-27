package airportpackage;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import airportpackage.shared.*;
import airportpackage.client.*;
import airportpackage.server.*;

public class TestAirport {
	
	public static void main(String[] args) {
		
		doWork();
		
	}

	
	private static void doWork() {
		
//			TEST UserDAO
//			UserDAO userDAO = new UserDAO();	
//			User user = userDAO.getUserByName("Admin");
//			System.out.println(user.getLogin());
//			System.out.println(user.getPassword());
//			user = userDAO.getUserByName("Диспетчер");
//			System.out.println(user.getLogin());
//			System.out.println(user.getPassword());
		

//			TEST TimetableDAO
//			TimetableDAO timetableDAO = new TimetableDAO();	
//			List<Timetable> ttl = timetableDAO.getTimetable();
//			List<Timetable> ttl = timetableDAO.getTimetableDetailsAll();
//			for (Timetable ttlIterator : ttl) {
//				System.out.println(ttlIterator.getTimetableId());
//				System.out.println(ttlIterator.getFlightNumber());
//				System.out.println(ttlIterator.getAirline());
//				System.out.println(ttlIterator.getRouteId());
//				System.out.println(ttlIterator.getAircraftRegId());
//				System.out.println(ttlIterator.getDepartureDate());
//				System.out.println(ttlIterator.getDepartureTime());
//				System.out.println(ttlIterator.getSourceAirport());
//				System.out.println(ttlIterator.getArrivalDate());
//				System.out.println(ttlIterator.getArrivalTime());
//				System.out.println(ttlIterator.getDestinationAirport());
//				System.out.println(ttlIterator.getCrewId());
//			}
		
//			TEST CrewDAO
//			CrewDAO crewDAO = new CrewDAO();	
//			List<Crew> cl = crewDAO.getCrew();
//			for (Crew clIterator : cl) {
//				System.out.println(clIterator.getPicId());
//				System.out.println(clIterator.getCpId());
//				System.out.println(clIterator.getSoId());
//				System.out.println(clIterator.getPurser1Id());
//				System.out.println(clIterator.getPurser2Id());
//				System.out.println(clIterator.getFlightAt1Id());
//				System.out.println(clIterator.getFlightAt2Id());
//				System.out.println(clIterator.getFlightAt3Id());
//				System.out.println(clIterator.getFlightAt4Id());
//				System.out.println(clIterator.getFlightAt5Id());
//				System.out.println(clIterator.getFlightAt6Id());
//				System.out.println(clIterator.getFlightAt7Id());
//				System.out.println(clIterator.getFlightAt8Id());
//				System.out.println(clIterator.getFlightAt9Id());
//				System.out.println(clIterator.getFlightAt10Id());
//			}
			
//			TEST StaffDAO
//			StaffDAO staffDAO = new StaffDAO();	
//			Staff staff2 = staffDAO.getStaffById(77);
//			List<Staff> sl = staffDAO.getStaffByPosition("Pilot-In-Command");
//			System.out.println(staff2.getStaffId());
//			System.out.println(staff2.getFirstName());
//			System.out.println(staff2.getLastName());
//			System.out.println(staff2.getGender());
//			System.out.println(staff2.getCountry());
//			System.out.println(staff2.getCity());
//			System.out.println(staff2.getPosition());
			
//			CrewDAO crewDAO = new CrewDAO();
//			List<Staff> sl = staffDAO.getStaff();
//			for (Staff slIterator : sl) {
//				System.out.println(slIterator.getStaffId());
//				System.out.println(slIterator.getFirstName());
//				System.out.println(slIterator.getLastName());
//				System.out.println(slIterator.getGender());
//				System.out.println(slIterator.getCountry());
//				System.out.println(slIterator.getCity());
//				System.out.println(slIterator.getPosition());
//			}
//			List<Staff> sl2 = staffDAO.getCrewList();
//			for (Staff slIterator : sl2) {
//				System.out.println(slIterator.getFirstName());
//				System.out.println(slIterator.getLastName());
//				System.out.println(slIterator.getPosition());
//			}

			
//			TEST StaffDAO
//			StaffDAO staffDAO = new StaffDAO();	
//			CrewDAO crewDAO = new CrewDAO();
//			Crew crew1 = new Crew();
//			crew1.setCpId(5);
//			crew1.setPicId(5);
//			crew1.setSoId(5);
//			crewDAO.insertCrew(crew1);
//			crewDAO.deleteCrew(11);
//			Crew crew1 = crewDAO.getCrewById(7);
//			crew1.setCpId(5);
//			crew1.setPicId(4);
//			crew1.setSoId(4);
//			crew1.setCrewId(7);
//			crewDAO.updateCrew(crew1);
			
			
//			TEST AircraftDAO
			AircraftDAO aircraftDAO = new AircraftDAO();	
//			Aircraft aircraft = aircraftDAO.getAircraftById(4);
//			System.out.println(aircraft.getAircraftId());
//			System.out.println(aircraft.getAircraftName());
//			System.out.println(aircraft.getLength());
//			System.out.println(aircraft.getWingspan());
//			System.out.println(aircraft.getHeight());
//			System.out.println(aircraft.getMaxTakeoffWeight());
//			System.out.println(aircraft.getMaxCruiseSpeed());
//			System.out.println(aircraft.getMaxCruiseAltitude());
//			System.out.println(aircraft.getEngines());
//			System.out.println(aircraft.getSeats());
			List<Aircraft> al = aircraftDAO.getAircraft();
//			for (Aircraft alIterator : al) {
//				System.out.println(alIterator.getAircraftId());
//				System.out.println(alIterator.getAircraftName());
//				System.out.println(alIterator.getLength());
//				System.out.println(alIterator.getWingspan());
//				System.out.println(alIterator.getHeight());
//				System.out.println(alIterator.getMaxTakeoffWeight());
//				System.out.println(alIterator.getMaxCruiseSpeed());
//				System.out.println(alIterator.getMaxCruiseAltitude());
//				System.out.println(alIterator.getEngines());
//				System.out.println(alIterator.getSeats());
//			}
//				
//			al = aircraftDAO.getFleet();
//			for (Aircraft alIterator : al) {
//				System.out.println(alIterator.getAircraftId());
//				System.out.println(alIterator.getAircraftName());
//				System.out.println(alIterator.getLength());
//				System.out.println(alIterator.getWingspan());
//				System.out.println(alIterator.getHeight());
//				System.out.println(alIterator.getMaxTakeoffWeight());
//				System.out.println(alIterator.getMaxCruiseSpeed());
//				System.out.println(alIterator.getMaxCruiseAltitude());
//				System.out.println(alIterator.getEngines());
//				System.out.println(alIterator.getSeats());
//			}
//			
			
			
	}
	
}