package repository;

import domain.Angajat;
import domain.Client;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

public class AngajatRepositoryTest {
    AngajatRepository r;
    ArrayList<Angajat> l;
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
        r = new AngajatRepository(prop);

    }

    @Test
    public void isAngajat() {
        assert(r.isAngajat("ion","maria").getID().equals("ion"));
        assert(r.isAngajat("dfg","sdg")==null);
    }
}