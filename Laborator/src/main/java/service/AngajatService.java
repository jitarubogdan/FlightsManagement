package service;

import domain.Angajat;
import repository.AngajatInterface;

public class AngajatService {
    private AngajatInterface repo;

    public AngajatService(AngajatInterface repo) {
        this.repo = repo;
    }

    public Angajat isAngajatService(String user, String password){
        return repo.isAngajat(user,password);
    }


}
