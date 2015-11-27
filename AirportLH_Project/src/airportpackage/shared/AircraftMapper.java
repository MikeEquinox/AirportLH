package airportpackage.shared;

/**
 * @author Mikhail Telegin
 * Class TimetableMapper
 */ 

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AircraftMapper {

	/**
	 * Get all aicrafts from table AIRCRAFTS, database AIRPORT
	 * @return list of aircrafts
	 */
	@Results({
		@Result(property = "aircraftId", column = "AIRCRAFT_ID"),
		@Result(property = "aircraftName", column = "AIRCRAFT_NAME"),
		@Result(property = "length", column = "LENGTH"),
		@Result(property = "wingspan", column = "WINGSPAN"),
		@Result(property = "weight", column = "WEIGHT"),
		@Result(property = "maxTakeoffWeight", column = "MAX_TAKEOFF_WEIGHT"),
		@Result(property = "maxCruiseSpeed", column = "MAX_CRUISE_SPEED"),
		@Result(property = "maxCruiseAltitude", column = "MAX_CRUISE_ALTITUDE"),
		@Result(property = "flightRange", column = "FLIGHT_RANGE"),
		@Result(property = "engines", column = "ENGINES"),
		@Result(property = "seats", column = "SEATS"),
	})
	@Select("SELECT * FROM AIRCRAFTS")
	List<Aircraft> getAircraft();
	
	/**
	 * Get aicraft by id from table AIRCRAFTS, database AIRPORT
	 * @param aircraftId - aircraftId
	 * @return aircraft
	 */
	@Results({
		@Result(property = "aircraftId", column = "AIRCRAFT_ID"),
		@Result(property = "aircraftName", column = "AIRCRAFT_NAME"),
		@Result(property = "length", column = "LENGTH"),
		@Result(property = "wingspan", column = "WINGSPAN"),
		@Result(property = "weight", column = "WEIGHT"),
		@Result(property = "maxTakeoffWeight", column = "MAX_TAKEOFF_WEIGHT"),
		@Result(property = "maxCruiseSpeed", column = "MAX_CRUISE_SPEED"),
		@Result(property = "maxCruiseAltitude", column = "MAX_CRUISE_ALTITUDE"),
		@Result(property = "flightRange", column = "FLIGHT_RANGE"),
		@Result(property = "engines", column = "ENGINES"),
		@Result(property = "seats", column = "SEATS"),
	})
	@Select("SELECT * FROM AIRCRAFTS WHERE AIRCRAFT_ID = #{aircraftId}")
	Aircraft getAircraftById(int aircraftId);
	
	
	/**
	 * Get all fleet from table AIRCRAFTS, database AIRPORT
	 * @return list of fleet
	 */
	@Results({
		@Result(property = "aircraftId", column = "AIRCRAFT_ID"),
		@Result(property = "aircraftName", column = "AIRCRAFT_NAME"),
		@Result(property = "length", column = "LENGTH"),
		@Result(property = "wingspan", column = "WINGSPAN"),
		@Result(property = "weight", column = "WEIGHT"),
		@Result(property = "maxTakeoffWeight", column = "MAX_TAKEOFF_WEIGHT"),
		@Result(property = "maxCruiseSpeed", column = "MAX_CRUISE_SPEED"),
		@Result(property = "maxCruiseAltitude", column = "MAX_CRUISE_ALTITUDE"),
		@Result(property = "flightRange", column = "FLIGHT_RANGE"),
		@Result(property = "engines", column = "ENGINES"),
		@Result(property = "seats", column = "SEATS"),
	})
	@Select("SELECT * FROM AEROFLEET INNER JOIN AIRCRAFTS ON AIRCRAFTS.AIRCRAFT_ID = AEROFLEET.AIRCRAFT_ID")
	List<Aircraft> getFleet();


}
