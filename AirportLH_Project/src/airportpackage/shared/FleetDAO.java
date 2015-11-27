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

public class FleetDAO {

	private static SqlSessionFactory sqlSessionFactory;
	/**
	 * Create DAO logger
	 */
	Logger daoLogger = Logger.getLogger("daoLogger");

	/**
	 * Get all fleet from table FLEET, database AIRPORT
	 * 
	 * @return Fleet list
	 */
	public List<Fleet> getAerofleet() {
		SqlSession session = getSqlSessionFactory().openSession();
		FleetMapper mapper = session.getMapper(FleetMapper.class);
		daoLogger.log(Level.INFO, "FleetDAO calls for FleetMapper, method getAerofleet()");
		List<Fleet> fleetList = mapper.getAerofleet();
		session.close();
		return fleetList;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		try {
			Reader resourceReader = Resources.getResourceAsReader("airportpackage/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceReader);

			sqlSessionFactory.getConfiguration().addMapper(FleetMapper.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
}
