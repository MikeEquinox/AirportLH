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
 * Class AircraftDAO
 */ 

public class AircraftDAO {

	private static SqlSessionFactory sqlSessionFactory;
	/** 
	 * Create DAO logger
	 */
	Logger daoLogger = Logger.getLogger("daoLogger");
		
		/**
		 * Get all aicrafts from table AIRCRAFTS, database AIRPORT
		 * @return Aircraft list
		 */
		public List<Aircraft> getAircraft() {
			 SqlSession session = getSqlSessionFactory().openSession();	
			 AircraftMapper mapper = session.getMapper(AircraftMapper.class);
			 daoLogger.log(Level.INFO, "AircraftDAO calls for AircraftMapper, method getAircraft()");
			  List<Aircraft> aircraftList = mapper.getAircraft();
			  session.close();
			  return aircraftList;
		}
		
		
		/**
		 * Get aircraft by id from table AIRCRAFTS, database AIRPORT
		 * @param aircraftId - aircraftId
		 * @return Aircraft
		 */
		public Aircraft getAircraftById(int aircraftId) {
			SqlSession session = getSqlSessionFactory().openSession();	
			AircraftMapper mapper = session.getMapper(AircraftMapper.class);
			daoLogger.log(Level.INFO, "AircraftDAO calls for AircraftMapper, method getAircraftById(int aircraftId)");
			Aircraft aircraft = mapper.getAircraftById(aircraftId);
			session.close();
			return aircraft;
		}
		
		
		/**
		 * Get all fleet from table AIRCRAFTS, database AIRPORT
		 * @return Aircraft list
		 */			
		public List<Aircraft> getFleet() {
			SqlSession session = getSqlSessionFactory().openSession();	
			daoLogger.log(Level.INFO, "AircraftDAO calls for AircraftMapper, method getFleet()");
			AircraftMapper mapper = session.getMapper(AircraftMapper.class);
			List<Aircraft> aircraftList = mapper.getFleet();
			session.close();
			return aircraftList;
		}
		
		
		public SqlSessionFactory getSqlSessionFactory() {
		try {
			Reader resourceReader = Resources.getResourceAsReader("airportpackage/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceReader);
			
			sqlSessionFactory.getConfiguration().addMapper(AircraftMapper.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
		}
}
