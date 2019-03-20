package repository;

import domain.Client;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class ClientRepositoryTest {
    Repository r;
    ArrayList<Client> l;
    private static int nr = 1;
    @Before
    public void setUp() throws Exception {
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("databaseTest.config"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        r = new ClientRepository(prop);

    }

    @Test
    public void save() {
        Iterable<Client> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size()==2);
        Client c = new Client(3,"Bogdan","Berchisesti");
        r.save(c);
        i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size()==3);
        r.delete(3);
        i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert (l.size()==2);

    }

    @Test
    public void findOne() {
        Iterable<Client> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        Client c = (Client) r.findOne(1);
        assert(c.getID() == 1);
        c = (Client) r.findOne(5);
        assert(c == null);
    }

    @Test
    public void findAll() {
        Iterable<Client> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size() == 2);
    }


}