package repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DataBaseUtils {
    private Properties props;
    private static final Logger logger = LogManager.getLogger();

    public DataBaseUtils(Properties jprops) {
        props = jprops;
    }

    private Connection instance = null;
    private Connection getNewConnection(){
        logger.traceEntry();
        String driver = props.getProperty("zboruri.jdbc.driver");
        String url = props.getProperty("zboruri.jdbc.url");
        logger.info("connect to database ",url);
        Connection conn = null;
        try{
            Class.forName(driver);
            logger.info("Loaded driver ",driver);
            conn = DriverManager.getConnection(url);
        }catch(ClassNotFoundException e){
            logger.error(e);
            System.out.println(e);

        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        return conn;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try{
            if(instance == null || instance.isClosed())
                instance = getNewConnection();
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit(instance);
        return instance;
    }
}
