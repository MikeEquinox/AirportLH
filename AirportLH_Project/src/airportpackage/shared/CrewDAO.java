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
 * @author Mikhail Telegin Class CrewDAO
 */

public class CrewDAO {

	private static SqlSessionFactory sqlSessionFactory;
	/** 
	 * Create DAO logger
	 */
	Logger daoLogger = Logger.getLogger("daoLogger");
	
	/**
	 * Insert crew into table CREWS, database AIRPORT
	 * @param crew - object Crew
	 */
	public void insertCrew(Crew crew) {
		SqlSession session = getSqlSessionFactory().openSession();
		daoLogger.log(Level.INFO, "CrewDAO calls for CrewMapper, method insertCrew(Crew crew)");
		CrewMapper mapper = session.getMapper(CrewMapper.class);
		mapper.insertCrew(crew);
		session.commit();
		session.close();
	}
	
	/**
	 * Update crew into table CREWS, database AIRPORT
	 * @param crew - object Crew
	 */
	public void updateCrew(Crew crew) {
		SqlSession session = getSqlSessionFactory().openSession();
		CrewMapper mapper = session.getMapper(CrewMapper.class);
		daoLogger.log(Level.INFO, "CrewDAO calls for CrewMapper, method updateCrew(Crew crew)");
		mapper.updateCrew(crew);
		session.commit();
		session.close();
	}

	
	/**
	 * Get all crews from table CREWS, database AIRPORT
	 * @return Crew list
	 */
	public List<Crew> getCrew() {
		SqlSession session = getSqlSessionFactory().openSession();
		CrewMapper mapper = session.getMapper(CrewMapper.class);
		daoLogger.log(Level.INFO, "CrewDAO calls for CrewMapper, method List<Crew> getCrew()");
		List<Crew> crewList = mapper.getCrew();
		session.close();
		return crewList;
	}

	
	/**
	 * Get crew from table CREWS, database AIRPORT
	 * @param crewId - crewId
	 * @return Crew
	 */
	public Crew getCrewById(int crewId) {
		SqlSession session = getSqlSessionFactory().openSession();
		CrewMapper mapper = session.getMapper(CrewMapper.class);
		daoLogger.log(Level.INFO, "CrewDAO calls for CrewMapper, method Crew getCrewById(int crewId)");
		Crew crew = mapper.getCrewById(crewId);
		session.close();
		return crew;
	}
	
	/**
	 * Delete crew by crewId from table CREWS, database AIRPORT
	 * @param crewId - crewId
	 * @return ""
	 */
	public String deleteCrewById(int crewId) {
		SqlSession session = getSqlSessionFactory().openSession();
		CrewMapper mapper = session.getMapper(CrewMapper.class);
		daoLogger.log(Level.INFO, "CrewDAO calls for CrewMapper, method String deleteCrewById(int crewId)");
		mapper.deleteCrewById(crewId);
		session.commit();
		session.close();
		return "";
	}
	
	
	
	public SqlSessionFactory getSqlSessionFactory() {
		try {
			Reader resourceReader = Resources
					.getResourceAsReader("airportpackage/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(resourceReader);

			sqlSessionFactory.getConfiguration().addMapper(CrewMapper.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}

}
