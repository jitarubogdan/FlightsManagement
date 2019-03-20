package repository;

import domain.Zbor;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

public class ZborRepositoryTest {
    Repository r;
    ArrayList<Zbor> l;

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
        r = new ZborRepository(prop);
    }

    @Test
    public void save() {
        Iterable<Zbor> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size()==1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse("2016-11-09 10:30",formatter);

        Zbor c = new Zbor(2,"Suceava",formatDateTime,"Otopeni",30);
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
        Iterable<Zbor> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        Zbor c = (Zbor) r.findOne(1);
        assert(c.getID() == 1);
        c = (Zbor) r.findOne(5);
        assert(c == null);
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
        Iterable<Zbor> i =r.findAll();
        l = new ArrayList<>();
        i.forEach(x->l.add(x));
        assert(l.size() == 1);
    }


}