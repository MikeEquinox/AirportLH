package airportpackage.shared;

/**
 * @author Mikhail Telegin
 * Class FleetMapper
 */ 

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface FleetMapper {

	/**
	 * Get all fleet from table FLEET, database AIRPORT
	 * @return Fleet list
	 */
	@Results({
		@Result(property = "fleetId", column = "FLEET_ID"),
		@Result(property = "aircraftRegId", column = "AIRCRAFT_REG_ID"),
		@Result(property = "aircraftId", column = "AIRCRAFT_ID"),
		@Result(property = "icaoAddr", column = "ICAO_ADDR"),
	})
//	@Select("SELECT AF.AIRCRAFT_REG_ID, AC.AIRCRAFT_NAME, AF.ICAO_ADDR FROM AEROFLEET AF, AIRCRAFTS AC WHERE AC.AIRCRAFT_ID = AF.AIRCRAFT_ID")
	@Select("SELECT * FROM AEROFLEET")
	List<Fleet> getAerofleet();

}
