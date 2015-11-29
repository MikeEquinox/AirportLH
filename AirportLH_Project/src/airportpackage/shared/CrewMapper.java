package airportpackage.shared;

/**
 * @author Mikhail Telegin
 * Class TimetableMapper
 */ 

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CrewMapper {

	/**
	 * Get all crews from table CREWS, database AIRPORT
	 * @return Crew list
	 */
	@Results({
		@Result(property = "crewId", column = "CREW_ID"),
		@Result(property = "picId", column = "PIC_ID"),
		@Result(property = "cpId", column = "CP_ID"),
		@Result(property = "soId", column = "SO_ID"),
		@Result(property = "purser1Id", column = "PURSER1_ID"),
		@Result(property = "purser2Id", column = "PURSER2_ID"),
		@Result(property = "flightAt1Id", column = "FLIGHT_AT1_ID"),
		@Result(property = "flightAt2Id", column = "FLIGHT_AT2_ID"),
		@Result(property = "flightAt3Id", column = "FLIGHT_AT3_ID"),
		@Result(property = "flightAt4Id", column = "FLIGHT_AT4_ID"),
		@Result(property = "flightAt5Id", column = "FLIGHT_AT5_ID"),
		@Result(property = "flightAt6Id", column = "FLIGHT_AT6_ID"),
		@Result(property = "flightAt7Id", column = "FLIGHT_AT7_ID"),
		@Result(property = "flightAt8Id", column = "FLIGHT_AT8_ID"),
		@Result(property = "flightAt9Id", column = "FLIGHT_AT9_ID"),
		@Result(property = "flightAt10Id", column = "FLIGHT_AT10_ID"),
	})
	@Select("SELECT * FROM CREWS")
	List<Crew> getCrew();
	
	/**
	 * Get crew from table CREWS, database AIRPORT
	 * @param crewId - crewId
	 * @return Crew
	 */
	@Results({
		@Result(property = "crewId", column = "CREW_ID"),
		@Result(property = "picId", column = "PIC_ID"),
		@Result(property = "cpId", column = "CP_ID"),
		@Result(property = "soId", column = "SO_ID"),
		@Result(property = "purser1Id", column = "PURSER1_ID"),
		@Result(property = "purser2Id", column = "PURSER2_ID"),
		@Result(property = "flightAt1Id", column = "FLIGHT_AT1_ID"),
		@Result(property = "flightAt2Id", column = "FLIGHT_AT2_ID"),
		@Result(property = "flightAt3Id", column = "FLIGHT_AT3_ID"),
		@Result(property = "flightAt4Id", column = "FLIGHT_AT4_ID"),
		@Result(property = "flightAt5Id", column = "FLIGHT_AT5_ID"),
		@Result(property = "flightAt6Id", column = "FLIGHT_AT6_ID"),
		@Result(property = "flightAt7Id", column = "FLIGHT_AT7_ID"),
		@Result(property = "flightAt8Id", column = "FLIGHT_AT8_ID"),
		@Result(property = "flightAt9Id", column = "FLIGHT_AT9_ID"),
		@Result(property = "flightAt10Id", column = "FLIGHT_AT10_ID"),	
	})
	@Select("SELECT  CREW_ID, PIC_ID, CP_ID, SO_ID, PURSER1_ID, PURSER2_ID, FLIGHT_AT1_ID,"
			+ " FLIGHT_AT2_ID, FLIGHT_AT3_ID, FLIGHT_AT4_ID, FLIGHT_AT5_ID, FLIGHT_AT6_ID,"
			+ " FLIGHT_AT7_ID, FLIGHT_AT8_ID, FLIGHT_AT9_ID, FLIGHT_AT10_ID FROM CREWS"
			+ " WHERE CREW_ID = #{crewId}")
	Crew getCrewById(int crewId);
	
	/**
	 * Get all crews from table CREWS, database AIRPORT
	 * @return Crew list
	 */
	@Select("SELECT S.FIRST_NAME, S.LAST_NAME, S.POSITION FROM STAFF S, CREWS C WHERE"
			+ " C.PIC_ID = S.STAFF_ID OR C.CP_ID = S.STAFF_ID OR C.SO_ID = S.STAFF_ID OR"
			+ " C.PURSER1_ID = S.STAFF_ID OR C.PURSER2_ID = S.STAFF_ID")
	List<Crew> getCrewList();
	
	/**
	 * Insert crew into table CREWS, database AIRPORT
	 * @param crew - object Crew
	 */
	@Insert("INSERT INTO CREWS(CREW_ID, PIC_ID, CP_ID, SO_ID, PURSER1_ID, PURSER2_ID,"
			+ " FLIGHT_AT1_ID, FLIGHT_AT2_ID, FLIGHT_AT3_ID, FLIGHT_AT4_ID, FLIGHT_AT5_ID,"
			+ " FLIGHT_AT6_ID, FLIGHT_AT7_ID, FLIGHT_AT8_ID, FLIGHT_AT9_ID, FLIGHT_AT10_ID)"
			+ " VALUES(#{crewId}, #{picId}, #{cpId}, #{soId}, #{purser1Id}, #{purser2Id},"
			+ "#{flightAt1Id}, #{flightAt2Id}, #{flightAt3Id}, #{flightAt4Id},"
			+ "#{flightAt5Id}, #{flightAt6Id}, #{flightAt7Id}, #{flightAt8Id},"
			+ "#{flightAt9Id}, #{flightAt10Id})")
	void insertCrew(Crew crew);
	
	/**
	 * Delete crew by crewId from table CREWS, database AIRPORT
	 * @param crewId - crewId
	 */
	@Delete("DELETE FROM CREWS WHERE CREW_ID=#{crewId}")
	void deleteCrewById(int crewId);
	
	/**
	 * Update crew into table CREWS, database AIRPORT
	 * @param crew - object Crew
	 */
	@Update("UPDATE CREWS SET PIC_ID = #{picId}, CP_ID = #{cpId}, SO_ID = #{soId}, PURSER1_ID = #{purser1Id},"
			+ " PURSER2_ID = #{purser2Id}, FLIGHT_AT1_ID = #{flightAt1Id}, FLIGHT_AT2_ID = #{flightAt2Id},"
			+ " FLIGHT_AT3_ID = #{flightAt3Id}, FLIGHT_AT4_ID = #{flightAt4Id}, FLIGHT_AT5_ID = #{flightAt5Id},"
			+ " FLIGHT_AT6_ID = #{flightAt6Id}, FLIGHT_AT7_ID = #{flightAt7Id}, FLIGHT_AT8_ID = #{flightAt8Id},"
			+ " FLIGHT_AT9_ID = #{flightAt9Id}, FLIGHT_AT10_ID = #{flightAt10Id}"
			+ " WHERE CREW_ID = #{crewId}")
	void updateCrew(Crew crew); 

}
