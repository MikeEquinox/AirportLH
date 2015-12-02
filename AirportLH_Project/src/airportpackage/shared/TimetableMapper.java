package airportpackage.shared;

/**
 * @author Mikhail Telegin
 * Interface TimetableMapper
 */ 

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TimetableMapper {

	/**
	 * Get all flights from table TIMETABLE, database AIRPORT
	 * @return Timetable list
	 */
	@Results({
		@Result(property = "timetableId", column = "TIMETABLE_ID"),
		@Result(property = "flightNumber", column = "FLIGHT_NUMBER"),
		@Result(property = "airline", column = "AIRLINE"),
		@Result(property = "routeId", column = "ROUTE_ID"),
		@Result(property = "aircraftRegId", column = "AIRCRAFT_REG_ID"),
		@Result(property = "crewId", column = "CREW_ID"),
		@Result(property = "departureDate", column = "DEPARTURE_DATE"),
		@Result(property = "departureTime", column = "DEPARTURE_TIME"),
		@Result(property = "sourceAirport", column = "SOURCE_AIRPORT"),
		@Result(property = "arrivalDate", column = "ARRIVAL_DATE"),
		@Result(property = "arrivalTime", column = "ARRIVAL_TIME"),
		@Result(property = "destinationAirport", column = "DESTINATION_AIRPORT"),
	})
	@Select("SELECT * FROM TIMETABLE")
	List<Timetable> getTimetable();
	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * исключая неназначенные рейсы
	 * @return Timetable list
	 */
	@Results({
		@Result(property = "timetableId", column = "TIMETABLE_ID"),
		@Result(property = "flightNumber", column = "FLIGHT_NUMBER"),
//		@Result(property = "airline", column = "AIRLINE"),
		@Result(property = "routeId", column = "ROUTE_ID"),
		@Result(property = "aircraftRegId", column = "AIRCRAFT_REG_ID"),
		@Result(property = "crewId", column = "CREW_ID"),
		@Result(property = "departureDate", column = "DEPARTURE_DATE"),
		@Result(property = "departureTime", column = "DEPARTURE_TIME"),
//		@Result(property = "sourceAirport", column = "SOURCE_AIRPORT"),
		@Result(property = "arrivalDate", column = "ARRIVAL_DATE"),
		@Result(property = "arrivalTime", column = "ARRIVAL_TIME"),
//		@Result(property = "destinationAirport", column = "DESTINATION_AIRPORT"),
		@Result(property = "airline", column = "AIRLINE_NAME"),
		@Result(property = "sourceAirport", column = "SOURCE_CITY"),
		@Result(property = "destinationAirport", column = "DESTINATION_CITY"),
	})
	@Select("SELECT T.CREW_ID, T.FLIGHT_NUMBER, A.AIRLINE_NAME, T.SOURCE_AIRPORT, A1.CITY AS 'SOURCE_CITY',"
			+ " T.DEPARTURE_DATE, T.DEPARTURE_TIME, T.DESTINATION_AIRPORT, A2.CITY AS 'DESTINATION_CITY',"
			+ " T.ARRIVAL_DATE, T.ARRIVAL_TIME, T.CREW_ID"
			+ " FROM ROUTES R, AIRLINES A, AIRPORTS A1, AIRPORTS A2, TIMETABLE T"
			+ " WHERE R.SOURCE_AIRPORT=T.SOURCE_AIRPORT and A.AIRLINE_ID=R.AIRLINE_ID"
			+ " and R.SOURCE_AIRPORT=A1.IATA and R.DESTINATION_AIRPORT=A2.IATA"
			+ " and R.DESTINATION_AIRPORT=T.DESTINATION_AIRPORT"
			+ " and T.DEPARTURE_DATE IS NOT NULL and T.ARRIVAL_DATE IS NOT NULL"
			+ " and T.DEPARTURE_TIME IS NOT NULL and T.ARRIVAL_TIME IS NOT NULL"
			+ " ORDER BY T.DEPARTURE_DATE, T.DEPARTURE_TIME")
	List<Timetable> getTimetableDetails();
	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * включая неназначенные рейсы
	 * @return Timetable list
	 */
	@Results({
		@Result(property = "timetableId", column = "TIMETABLE_ID"),
		@Result(property = "flightNumber", column = "FLIGHT_NUMBER"),
//		@Result(property = "airline", column = "AIRLINE"),
		@Result(property = "routeId", column = "ROUTE_ID"),
		@Result(property = "aircraftRegId", column = "AIRCRAFT_REG_ID"),
		@Result(property = "crewId", column = "CREW_ID"),
		@Result(property = "departureDate", column = "DEPARTURE_DATE"),
		@Result(property = "departureTime", column = "DEPARTURE_TIME"),
//		@Result(property = "sourceAirport", column = "SOURCE_AIRPORT"),
		@Result(property = "arrivalDate", column = "ARRIVAL_DATE"),
		@Result(property = "arrivalTime", column = "ARRIVAL_TIME"),
//		@Result(property = "destinationAirport", column = "DESTINATION_AIRPORT"),
		
		@Result(property = "airline", column = "AIRLINE_NAME"),
		@Result(property = "sourceAirport", column = "SOURCE_CITY"),
		@Result(property = "destinationAirport", column = "DESTINATION_CITY"),
	})
	@Select("SELECT T.FLIGHT_NUMBER, A.AIRLINE_NAME, T.SOURCE_AIRPORT, A1.CITY AS 'SOURCE_CITY',"
			+ " T.DEPARTURE_DATE, T.DEPARTURE_TIME, T.DESTINATION_AIRPORT, A2.CITY AS 'DESTINATION_CITY',"
			+ " T.ARRIVAL_DATE, T.ARRIVAL_TIME, T.CREW_ID"
			+ " FROM ROUTES R, AIRLINES A, AIRPORTS A1, AIRPORTS A2, TIMETABLE T"
			+ " WHERE R.SOURCE_AIRPORT=T.SOURCE_AIRPORT and A.AIRLINE_ID=R.AIRLINE_ID"
			+ " and R.SOURCE_AIRPORT=A1.IATA and R.DESTINATION_AIRPORT=A2.IATA"
			+ " and R.DESTINATION_AIRPORT=T.DESTINATION_AIRPORT"
			+ " ORDER BY T.DEPARTURE_DATE, T.DEPARTURE_TIME")
	List<Timetable> getTimetableDetailsAll();

	/**
	 * Insert flight crew into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 */
//	@Insert("INSERT into Timetable() VALUES(#{}, #{}, #{}, #{})")
	void insertTimetable(Timetable timetable);

	/**
	 * Update flight crew into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 */
	@Update("UPDATE TIMETABLE SET CREW_ID=#{crewId} WHERE FLIGHT_NUMBER =#{flightNumber}")
	void updateTimetableByFlightNumber(Timetable timetable);

	/**
	 * Update flight dates into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 */
	@Update("UPDATE TIMETABLE SET CREW_ID=#{crewId} WHERE FLIGHT_NUMBER =#{flightNumber}")
	void updateTimetableDates(Timetable timetable);
	
	/**
	 * Delete flight by flightNumber from table TIMETABLE, database AIRPORT 
	 * @param flightNumber - flight number
	 */
	@Delete("DELETE FROM TIMETABLE WHERE FLIGHT_NUMBER =#{flightNumber}")
	void deleteTimetable(String flightNumber);
	
}
