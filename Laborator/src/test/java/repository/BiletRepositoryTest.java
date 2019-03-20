package repository;

import domain.Bilet;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

public class BiletRepositoryTest {
    Repository r;
    ArrayList<Bilet> l;
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
        r = new BiletRepository(prop);

    }

    @Test
    public void save() {
        Iterable<Bilet> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size()==1);
        Bilet c = new Bilet(2,1,2,"sdf",10);
        r.save(c);
        i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size()==2);
        r.delete(2);
        i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert (l.size()==1);
    }

    @Test
    public void findOne() {
        Iterable<Bilet> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        Bilet c = (Bilet) r.findOne(1);
        assert(c.getID() == 1);
        c = (Bilet) r.findOne(5);
        assert(c == null);
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
        Iterable<Bilet> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size() == 1);
    }
}