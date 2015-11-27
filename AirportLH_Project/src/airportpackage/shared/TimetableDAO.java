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
 * @author Mikhail Telegin Class TimetableDAO
 */

/*
 * MyBatis utility class to get SqlSessionFactory. Using SqlSession.getMapper(),
 * we retrieve mapper interface implementation by MyBatis and call the methods
 * declared in interface as given in DAO class.
 */

public class TimetableDAO {

	private static SqlSessionFactory sqlSessionFactory;
	/** 
	 * Create DAO logger
	 */
	Logger daoLogger = Logger.getLogger("daoLogger");
	
	/**
	 * Insert flight crew into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 */
	public void insertTimetable(Timetable timetable) {
		SqlSession session = getSqlSessionFactory().openSession();
		TimetableMapper mapper = session.getMapper(TimetableMapper.class);
		daoLogger.log(Level.INFO, "TimetableDAO calls for TimetableMapper, method insertTimetable(Timetable timetable)");
		mapper.insertTimetable(timetable);
		session.commit();
		session.close();
	}
	
	/**
	 * Update flight crew into table TIMETABLE, database AIRPORT
	 * @param timetable - object Timetable
	 */
	public void updateTimetableByFlightNumber(Timetable timetable) {
		SqlSession session = getSqlSessionFactory().openSession();
		TimetableMapper mapper = session.getMapper(TimetableMapper.class);
		daoLogger.log(Level.INFO, "TimetableDAO calls for TimetableMapper, method updateTimetable(Timetable timetable)");
		mapper.updateTimetableByFlightNumber(timetable);
		session.commit();
		session.close();
	}
	
	/**
	 * Delete flight by flightNumber from table TIMETABLE, database AIRPORT
	 * @param flightNumber - flight number
	 */
	public void deleteTimetableByFlightNumber(String flightNumber) {
		SqlSession session = getSqlSessionFactory().openSession();
		TimetableMapper mapper = session.getMapper(TimetableMapper.class);
		daoLogger.log(Level.INFO, "TimetableDAO calls for TimetableMapper, method deleteTimetable(String flightNumber)");
		mapper.deleteTimetable(flightNumber);
		session.commit();
		session.close();
	}
	
	/**
	 * Get all flights from table TIMETABLE, database AIRPORT
	 * @return Timetable list
	 */
	public List<Timetable> getTimetable() {
		SqlSession session = getSqlSessionFactory().openSession();
		TimetableMapper mapper = session.getMapper(TimetableMapper.class);
		List<Timetable> timetableList = mapper.getTimetable();
		daoLogger.log(Level.INFO, "TimetableDAO calls for TimetableMapper, method getTimetable()");
		session.close();
		return timetableList;
	}
	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * исключая неназначенные рейсы
	 * @return Timetable list
	 */
	public List<Timetable> getTimetableDetails() {
		SqlSession session = getSqlSessionFactory().openSession();
		TimetableMapper mapper = session.getMapper(TimetableMapper.class);
		daoLogger.log(Level.INFO, "TimetableDAO calls for TimetableMapper, method getTimetableDetails()");
		List<Timetable> timetableList = mapper.getTimetableDetails();
		session.close();
		return timetableList;
	}
	
	
	/**
	 * Get all flights from table TIMETABLE and table ROUTES
	 * and table AIRLINES and table AIRPORTS, database AIRPORT
	 * включая неназначенные рейсы
	 * @return Timetable list
	 */
	public List<Timetable> getTimetableDetailsAll() {
		SqlSession session = getSqlSessionFactory().openSession();
		TimetableMapper mapper = session.getMapper(TimetableMapper.class);
		daoLogger.log(Level.INFO, "TimetableDAO calls for TimetableMapper, method getTimetableDetailsAll()");
		List<Timetable> timetableList = mapper.getTimetableDetailsAll();
		session.close();
		return timetableList;
	}

	
	
	
	public SqlSessionFactory getSqlSessionFactory() {
		try {
			Reader resourceReader = Resources
					.getResourceAsReader("airportpackage/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(resourceReader);

			sqlSessionFactory.getConfiguration().addMapper(
					TimetableMapper.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
}
