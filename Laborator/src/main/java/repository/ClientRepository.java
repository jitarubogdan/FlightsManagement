package repository;

import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientRepository implements ClientInterface {
    private DataBaseUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ClientRepository(Properties props) {
        dbUtils = new DataBaseUtils(props);
    }

    @Override
    public void save(Client entity) {
        logger.traceEntry("salvare client ",entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Clienti values (?,?,?)")){
            preStmt.setInt(1,entity.getID());
            preStmt.setString(2,entity.getNume());
            preStmt.setString(3,entity.getAdresa());
            int result = preStmt.executeUpdate();
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer){
        logger.traceEntry("stergere element cu id ",integer);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("delete from Clienti where id=?")){
            preStmt.setInt(1,integer);
            int result = preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit();
    }

    @Override
    public Client findOne(Integer integer) {
        logger.traceEntry("cautare client cu id ",integer);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("select * from Clienti where Id = ?")){
            preStmt.setInt(1,integer);
            try(ResultSet result = preStmt.executeQuery()){
                if(result.next()){
                    int id = result.getInt("Id");
                    String nume = result.getString("Nume");
                    String adresa = result.getString("Adresa");
                    Client c = new Client(id,nume,adresa);
                    return c;
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.out.println(ex);
        }
        logger.traceExit("Niciun client gasit ",integer);
        return null;
    }

    @Override
    public Iterable<Client> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Client> clients = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Clienti")){
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("Id");
                    String nume = result.getString("Nume");
                    String adresa = result.getString("Adresa");
                    Client c = new Client(id, nume, adresa);
                    clients.add(c);
                }
            }
            }catch(SQLException e){
                logger.error(e);
                System.out.println(e);
            }
            logger.traceExit(clients);
            return clients;

        }

    @Override
    public Client findClientByNameAndAdresa(String nume, String adresa) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Clienti where Nume=? and Adresa=?")){
            preStmt.setString(1,nume);
            preStmt.setString(2,adresa);
            try(ResultSet result = preStmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("Id");
                    String nume2 = result.getString("Nume");
                    String adresa2 = result.getString("Adresa");
                    Client c = new Client(id, nume2, adresa2);
                    return c;
                }
            }
        }catch(SQLException e){
            logger.error(e);
            System.out.println(e);
        }
        return null;
    }
}
