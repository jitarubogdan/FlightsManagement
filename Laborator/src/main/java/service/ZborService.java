package service;

import domain.Zbor;
import domain.ZborDTO;
import repository.ZborInterface;
import repository.ZborRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ZborService {
    private ZborInterface repo;

    public ZborService(ZborInterface repo) {
        this.repo = repo;
    }

    public Iterable<Zbor> findAll(){
        return repo.findAll();
    }

    public Iterable<ZborDTO> findByDestinatieData(String destinatie, String data){
        Iterable<Zbor> it = repo.findByDestinatieData(destinatie,data);
        List<Zbor> zboruri = new ArrayList();
        it.forEach(x->zboruri.add(x));
        List<ZborDTO> zboruriDTO = new ArrayList<>();
        zboruri.forEach(
                x->{
                    int hour = x.getPlecare().getHour();
                    int minute = x.getPlecare().getMinute();
                    zboruriDTO.add(new ZborDTO(LocalTime.of(hour,minute),x.getLocuri()));
                });
        zboruriDTO.forEach(x->System.out.println(x));
        return zboruriDTO;
    }
}
