package repository;

import domain.HasID;
import domain.Zbor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZborRepository implements ZborInterface {
    private DataBaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public ZborRepository(Properties prop){
        dbUtils = new DataBaseUtils(prop);
    }


    @Override
    public void save(Zbor entity) {
        logger.traceEntry("salvare zbor",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Zboruri values (?,?, datetime(?),?,?)")){
            preStmt.setInt(1,entity.getID());
            preStmt.setString(2,entity.getDestinatie());
            //preStmt.setDate(3,java.sql.Date.valueOf(entity.getPlecare().toLocalDate()));
            preStmt.setString(3,entity.getPlecare().toString());
            preStmt.setString(4,entity.getAeroport());
            preStmt.setInt(5,entity.getLocuri());
            int result = preStmt.executeUpdate();

        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit();
    }

    @Override
    public Zbor findOne(Integer integer) {
        logger.traceEntry("cautare zbor cu id ",integer);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("select * from Zboruri where Id = ?")){
            preStmt.setInt(1,integer);
            try(ResultSet result = preStmt.executeQuery()){
                if(result.next()){
                    int id = result.getInt("Id");
                    String destinatie = result.getString("Destinatie");
                    //LocalDateTime plecare= result.getTimestamp("Plecare").toLocalDateTime();
                    LocalDateTime plecare= LocalDateTime.parse(result.getString("Plecare"));
                    String aeroport = result.getString("Aeroport");
                    Integer locuri = result.getInt("Locuri");
                    Zbor z = new Zbor(id,destinatie,plecare,aeroport,locuri);
                    return z;
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit("Niciun zbor gasit ",integer);
        return null;
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("stergere element cu id ",integer);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("delete from Zboruri where Id=?")){
            preStmt.setInt(1,integer);
            int result = preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Zbor> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Zbor> zboruri = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Zboruri")){
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    String destinatie = result.getString("Destinatie");
                    LocalDateTime plecare= LocalDateTime.parse(result.getString("Plecare"));
                    String aeroport = result.getString("Aeroport");
                    Integer locuri = result.getInt("Locuri");
                    Zbor z = new Zbor(id,destinatie,plecare,aeroport,locuri);
                    zboruri.add(z);
                }
            }
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit(zboruri);
        return zboruri;
    }

    @Override
    public Iterable<Zbor> findByDestinatieData(String destinatie, String data){
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Zbor> zboruri = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Zboruri where Destinatie=? and date(Plecare)=?")){
            preStmt.setString(1,destinatie);
            preStmt.setString(2,data);
            try(ResultSet result = preStmt.executeQuery()){
                while(result.next()){
                    int id = result.getInt("Id");
                    String destinatie2 = result.getString("Destinatie");
                    LocalDateTime plecare= LocalDateTime.parse(result.getString("Plecare"));
                    String aeroport = result.getString("Aeroport");
                    Integer locuri = result.getInt("Locuri");
                    Zbor z = new Zbor(id,destinatie2,plecare,aeroport,locuri);
                    zboruri.add(z);
                }
            }
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit(zboruri);
        return zboruri;

    }

    @Override
    public Zbor findByDestinatieAndTime(String destinatie, String data) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Zboruri where Destinatie=? and Plecare=?")){
            preStmt.setString(1,destinatie);
            preStmt.setString(2,data);
            try(ResultSet result = preStmt.executeQuery()){
                while(result.next()){
                    int id = result.getInt("Id");
                    String destinatie2 = result.getString("Destinatie");
                    LocalDateTime plecare= LocalDateTime.parse(result.getString("Plecare"));
                    String aeroport = result.getString("Aeroport");
                    Integer locuri = result.getInt("Locuri");
                    Zbor z = new Zbor(id,destinatie2,plecare,aeroport,locuri);
                    return z;
                }
            }
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public void updateLocuri(Integer id, int locuriRamase) {

        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("update Zboruri set Locuri = ? where Id = ?")) {
            preStmt.setInt(1, locuriRamase);
            preStmt.setInt(2, id);
            int result = preStmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit();
    }

}
