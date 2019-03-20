package repository;

import domain.Angajat;

public interface AngajatInterface<E,T>{
    Angajat isAngajat(E user, T password);
}
