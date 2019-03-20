package service;

import domain.Bilet;
import domain.Client;
import domain.Zbor;
import repository.BiletInterface;
import repository.ClientInterface;
import repository.ZborInterface;

import java.util.ArrayList;
import java.util.List;

public class BiletService {
    private BiletInterface repoB;
    private ClientInterface repoC;
    private ZborInterface repoZ;

    public BiletService(BiletInterface repoB, ClientInterface repoC, ZborInterface repoZ) {
        this.repoB = repoB;
        this.repoC = repoC;
        this.repoZ = repoZ;
    }

    public int getCorrectId(){
        Iterable<Bilet> lista = repoB.findAll();
        List<Bilet> l = new ArrayList<>();
        lista.forEach(x->l.add(x));
        int lungime = l.size();
        return lungime+1;
    }

    public void addBilet(String destinatie, String data, String ora, String nume, String adresa, String locuri, String turisti){
        Client c = repoC.findClientByNameAndAdresa(nume,adresa);
        String data2 = data+"T"+ora;
        Zbor z = repoZ.findByDestinatieAndTime(destinatie,data2);
        int locuri2 = Integer.parseInt(locuri);
        int id = getCorrectId();
        Bilet b = new Bilet(id,z.getID(),c.getID(),turisti,locuri2);
        repoB.save(b);
        int locuriRamase = z.getLocuri()-locuri2;
        if(locuriRamase == 0){
            repoZ.delete(z.getID());
        }else {
            repoZ.updateLocuri(z.getID(), locuriRamase);
        }
    }
}
