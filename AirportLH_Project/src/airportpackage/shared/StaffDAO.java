package airportpackage.shared;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author Mikhail Telegin
 * Class DAO Staff
 */ 

/*
MyBatis utility class to get SqlSessionFactory. 
Using SqlSession.getMapper(), we retrieve mapper interface
implementation by MyBatis and call the methods declared
in interface as given in DAO class.
*/

public class StaffDAO {

	private static SqlSessionFactory sqlSessionFactory;
	/** 
	 * Create DAO logger
	 */
	Logger daoLogger = Logger.getLogger("daoLogger");
	private List<Staff> staffList;

		
		/**
		 * Get all staff from table STAFF, database AIRPORT
		 * @return Staff list
		 */
		public List<Staff> getStaff() {
			 SqlSession session = getSqlSessionFactory().openSession();	
			 StaffMapper mapper = session.getMapper(StaffMapper.class);
			 daoLogger.log(Level.INFO, "StaffDAO calls for StaffMapper, method getStaff()");
			  List<Staff> staffList = mapper.getStaff();
			  session.close();
			  return staffList;
		}
		
		/**
		 * Get all staff by staffId from table STAFF, database AIRPORT
		 * @param staffId - staffId
		 * @return Staff
		 */
		public Staff getStaffById(int staffId) {
			SqlSession session = getSqlSessionFactory().openSession();	
			StaffMapper mapper = session.getMapper(StaffMapper.class);
			daoLogger.log(Level.INFO, "StaffDAO calls for StaffMapper, getStaffById(int staffId)");
			Staff staff = mapper.getStaffById(staffId);
			session.close();
			return staff;
		}
		
		
		/**
		 * Get staff by Position from table STAFF, database AIRPORT
		 * @param position - position
		 * @return Staff list
		 */
		public List<Staff> getStaffByPosition(String position) {
			SqlSession session = getSqlSessionFactory().openSession();
			try { 	
			daoLogger.log(Level.INFO, "StaffDAO calls for StaffMapper, getStaffByPosition(String position)");
			StaffMapper mapper = session.getMapper(StaffMapper.class);
			staffList = mapper.getStaffByPosition(position);
			} finally {
			session.close();
			}
			return staffList;
		}
		
		/**
		 * Get crew list from table STAFF and table CREWS, database AIRPORT
		 * @return Staff list
		 */
		public List<Staff> getCrewList() {
			SqlSession session = getSqlSessionFactory().openSession();	
			daoLogger.log(Level.INFO, "StaffDAO calls for StaffMapper, getStaffByPosition(String position)");
			StaffMapper mapper = session.getMapper(StaffMapper.class);
			List<Staff> staffList = mapper.getCrewList();
			session.close();
			return staffList;
		}
		
		
		/**
		 * Get crew list from table STAFF and table CREWS, database AIRPORT
		 * @param crewId - crewId
		 * @return Staff list
		 */
		public List<Staff> getCrewListById(int crewId) {
			SqlSession session = getSqlSessionFactory().openSession();	
			StaffMapper mapper = session.getMapper(StaffMapper.class);
			daoLogger.log(Level.INFO, "StaffDAO calls for StaffMapper, getStaffByPosition(String position)");
			List<Staff> staffList = mapper.getCrewListById(crewId);
			session.close();
			return staffList;
		}
		
		/**
		 * Update CREW_ID_REF to 1 or 0 into table STAFF, database AIRPORT
		 * @param forwardStaffId - staffId to set flag
		 * @param flag - flag
		 * @return ""
		 */
		public String updateFlag(int forwardStaffId, boolean flag) {
			SqlSession session = getSqlSessionFactory().openSession();	
			StaffMapper mapper = session.getMapper(StaffMapper.class);
			daoLogger.log(Level.INFO, "StaffDAO calls for StaffMapper, getStaffByPosition(String position)");
			if(flag == true) mapper.updateFlag1(forwardStaffId);
			else  mapper.updateFlag0(forwardStaffId);
			session.commit();
			session.close();
			return "";
		}
		
		
		
		public SqlSessionFactory getSqlSessionFactory() {
		try {
			Reader resourceReader = Resources.getResourceAsReader("airportpackage/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceReader);
			
			sqlSessionFactory.getConfiguration().addMapper(StaffMapper.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
		}

		
}
