package repository;

import domain.Angajat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AngajatRepository implements AngajatInterface<String,String>{
    private DataBaseUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();


    public AngajatRepository(Properties props) {
        dbUtils = new DataBaseUtils(props);
    }

    @Override
    public Angajat isAngajat(String user, String password){
        logger.traceEntry("cautare angajat cu id si parola ",user,password);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("select * from Angajati where User like ? and Password like ?")){
            preStmt.setString(1,user);
            preStmt.setString(2,password);
            try(ResultSet result = preStmt.executeQuery()){
                if(result.next()){
                    String user2 = result.getString("User");
                    String password2 = result.getString("Password");
                    Angajat a = new Angajat(user2,password2);
                    return a;
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit("Niciun angajat gasit cu id",user);

        return null;
    }
}
