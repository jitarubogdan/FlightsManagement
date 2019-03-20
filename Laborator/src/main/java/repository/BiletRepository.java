package repository;

import domain.Bilet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BiletRepository implements BiletInterface {
    private DataBaseUtils dbUtils;
    private static final Logger logger = LogManager.getLogger();

    public BiletRepository(Properties prop){
        dbUtils = new DataBaseUtils(prop);
    }

    @Override
    public void save(Bilet entity) {
        logger.traceEntry("salvare bilet",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Bilete values (?,?,?,?,?)")){
            preStmt.setInt(1,entity.getID());
            preStmt.setInt(2,entity.getIdZbor());
            preStmt.setInt(3,entity.getIdClient());
            preStmt.setString(4, entity.getTuristi());
            preStmt.setInt(5,entity.getNrLocuri());
            int result = preStmt.executeUpdate();

        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit();
    }

    @Override
    public Bilet findOne(Integer integer) {
        logger.traceEntry("cautare bilet cu id ",integer);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("select * from Bilete where Id = ?")){
            preStmt.setInt(1,integer);
            try(ResultSet result = preStmt.executeQuery()){
                if(result.next()){
                    int id = result.getInt("Id");
                    int idZbor = result.getInt("IdZbor");
                    int idClient = result.getInt("IdClient");
                    String turisti = result.getString("Turisti");
                    int locuri = result.getInt("Locuri");
                    Bilet b = new Bilet(id,idZbor,idClient,turisti,locuri);
                    return b;
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit("Niciun bilet gasit ",integer);
        return null;
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("stergere element cu id ",integer);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("delete from Bilete where id=?")){
            preStmt.setInt(1,integer);
            int result = preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Bilet> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Bilet> bilete = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Bilete")){
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    int idZbor = result.getInt("IdZbor");
                    int idClient = result.getInt("IdClient");
                    String turisti = result.getString("Turisti");
                    int locuri = result.getInt("Locuri");
                    Bilet b = new Bilet(id,idZbor,idClient,turisti,locuri);
                    bilete.add(b);
                }
            }
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        logger.traceExit(bilete);
        return bilete;
    }
}
