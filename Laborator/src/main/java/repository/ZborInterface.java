package repository;

import domain.Zbor;

import java.util.List;

public interface ZborInterface extends Repository<Integer, Zbor> {
    Iterable<Zbor> findByDestinatieData(String destinatie, String data);

    Zbor findByDestinatieAndTime(String destinatie, String data);

    void updateLocuri(Integer id, int locuriRamase);
}
