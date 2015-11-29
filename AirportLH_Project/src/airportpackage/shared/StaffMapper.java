package airportpackage.shared;

/**
 * @author Mikhail Telegin
 * Interface StaffMapper
 */

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StaffMapper {

	/**
	 * Get all staff from table STAFF, database AIRPORT
	 * 
	 * @return Staff list
	 */
	@Results({ @Result(property = "staffId", column = "STAFF_ID"),
			@Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "lastName", column = "LAST_NAME"), @Result(property = "gender", column = "GENDER"),
			@Result(property = "country", column = "COUNTRY"), @Result(property = "city", column = "CITY"),
			@Result(property = "position", column = "POSITION"), })
	@Select("SELECT * FROM STAFF")
	List<Staff> getStaff();

	/**
	 * Get all staff by staffId from table STAFF, database AIRPORT
	 * 
	 * @param staffId
	 *            - staffId
	 * @return Staff
	 */
	@Results({ @Result(property = "staffId", column = "STAFF_ID"),
			@Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "lastName", column = "LAST_NAME"), @Result(property = "gender", column = "GENDER"),
			@Result(property = "country", column = "COUNTRY"), @Result(property = "city", column = "CITY"),
			@Result(property = "position", column = "POSITION"), })
	@Select("SELECT * FROM STAFF WHERE STAFF_ID = #{staffId}")
	Staff getStaffById(int staffId);

	/**
	 * Get crew list from table STAFF and table CREWS, database AIRPORT
	 * 
	 * @return Staff list
	 */
	@Results({ @Result(property = "staffId", column = "STAFF_ID"),
			@Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "lastName", column = "LAST_NAME"), @Result(property = "gender", column = "GENDER"),
			@Result(property = "country", column = "COUNTRY"), @Result(property = "city", column = "CITY"),
			@Result(property = "position", column = "POSITION"), })
	@Select("SELECT S.FIRST_NAME, S.LAST_NAME, S.POSITION FROM STAFF S, CREWS C WHERE"
			+ " C.PIC_ID = S.STAFF_ID OR C.CP_ID = S.STAFF_ID OR C.SO_ID = S.STAFF_ID OR C.PURSER1_ID = S.STAFF_ID"
			+ " OR C.PURSER2_ID = S.STAFF_ID OR C.FLIGHT_AT1_ID = S.STAFF_ID OR C.FLIGHT_AT2_ID = S.STAFF_ID"
			+ " OR C.FLIGHT_AT3_ID = S.STAFF_ID OR C.FLIGHT_AT4_ID = S.STAFF_ID OR C.FLIGHT_AT5_ID = S.STAFF_ID"
			+ " OR C.FLIGHT_AT6_ID = S.STAFF_ID OR C.FLIGHT_AT7_ID = S.STAFF_ID OR C.FLIGHT_AT8_ID = S.STAFF_ID"
			+ " OR C.FLIGHT_AT9_ID = S.STAFF_ID OR C.FLIGHT_AT10_ID = S.STAFF_ID")
	List<Staff> getCrewList();

	/**
	 * Get staff by Position from table STAFF, database AIRPORT
	 * 
	 * @param position
	 *            - position
	 * @return Staff list
	 */
	@Results({ @Result(property = "staffId", column = "STAFF_ID"),
			@Result(property = "firstName", column = "FIRST_NAME"),
			@Result(property = "lastName", column = "LAST_NAME"), @Result(property = "gender", column = "GENDER"),
			@Result(property = "country", column = "COUNTRY"), @Result(property = "city", column = "CITY"),
			@Result(property = "position", column = "POSITION"), })
	@Select("SELECT * FROM STAFF WHERE POSITION = #{position} and CREW_ID_REF NOT IN(1)")
	List<Staff> getStaffByPosition(String position);

	/**
	 * Get crew list from table STAFF and table CREWS, database AIRPORT
	 * 
	 * @param crewId
	 *            - crewId
	 * @return Staff list
	 */
//	@Select("SELECT S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME, S.POSITION FROM STAFF S, CREWS C  WHERE"
//			+ " C.CREW_ID IN(#{crewId}) and" 
//			+ " (C.PIC_ID = S.STAFF_ID OR C.CP_ID = S.STAFF_ID OR C.SO_ID = S.STAFF_ID OR C.PURSER1_ID = S.STAFF_ID"
//			+ " OR C.PURSER2_ID = S.STAFF_ID OR C.FLIGHT_AT1_ID = S.STAFF_ID OR C.FLIGHT_AT2_ID = S.STAFF_ID"
//			+ " OR C.FLIGHT_AT3_ID = S.STAFF_ID OR C.FLIGHT_AT4_ID = S.STAFF_ID OR C.FLIGHT_AT5_ID = S.STAFF_ID"
//			+ " OR C.FLIGHT_AT6_ID = S.STAFF_ID OR C.FLIGHT_AT7_ID = S.STAFF_ID OR C.FLIGHT_AT8_ID = S.STAFF_ID"
//			+ " OR C.FLIGHT_AT9_ID = S.STAFF_ID OR C.FLIGHT_AT10_ID = S.STAFF_ID)"
//			+ " ORDER BY S.POSITION")
	@Results({
		@Result(property = "staffId", column = "STAFF_ID"),
		@Result(property = "firstName", column = "FIRST_NAME"),
		@Result(property = "lastName", column = "LAST_NAME"),
		@Result(property = "gender", column = "GENDER"),
		@Result(property = "country", column = "COUNTRY"),
		@Result(property = "city", column = "CITY"),
		@Result(property = "position", column = "POSITION"),
	})
	@Select("SELECT S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME FROM STAFF S, CREWS C"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.PIC_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.CP_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.SO_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.PURSER1_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.PURSER2_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT1_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT2_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT3_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT4_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT5_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT6_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT7_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT8_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT9_ID = S.STAFF_ID UNION"
			+ " SELECT  S.POSITION, S.STAFF_ID, S.FIRST_NAME, S.LAST_NAME  FROM CREWS C, STAFF S"
			+ " WHERE C.CREW_ID IN(#{crewId}) and C.FLIGHT_AT10_ID = S.STAFF_ID")
	List<Staff> getCrewListById(int crewId);
	// этот ужас из-за mysql, который произвольно делает сортировку
	
	/**
	 * Update CREW_ID_REF to buisy state into table STAFF, database AIRPORT
	 * 
	 * @param forwardStaffIdNew
	 *            - new employee staffId
	 */
	@Update("UPDATE STAFF SET CREW_ID_REF = 1 WHERE STAFF_ID = #{forwardStaffIdNew}")
	void updateFlag1(int forwardStaffIdNew);

	/**
	 * Update CREW_ID_REF to free state into table STAFF, database AIRPORT
	 * 
	 * @param forwardStaffIdOld
	 *            - old employee StaffId
	 */
	@Update("UPDATE STAFF SET CREW_ID_REF = 0 WHERE STAFF_ID = #{forwardStaffIdOld}")
	void updateFlag0(int forwardStaffIdOld);
}
