package domain;

import java.time.LocalDateTime;

public class Zbor implements HasID<Integer> {
    private Integer id;
    private String destinatie;
    private LocalDateTime plecare;
    private String aeroport;
    private Integer locuri;

    public Zbor(Integer id, String destinatie, LocalDateTime plecare, String aeroport, Integer locuri) {
        this.id = id;
        this.destinatie = destinatie;
        this.plecare = plecare;
        this.aeroport = aeroport;
        this.locuri = locuri;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer integer) {
        this.id = integer;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public LocalDateTime getPlecare() {
        return plecare;
    }

    public void setPlecare(LocalDateTime plecare) {
        this.plecare = plecare;
    }

    public String getAeroport() {
        return aeroport;
    }

    public void setAeroport(String aeroport) {
        this.aeroport = aeroport;
    }

    public Integer getLocuri() {
        return locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "id=" + id +
                ", destinatie='" + destinatie + '\'' +
                ", plecare=" + plecare +
                ", aeroport='" + aeroport + '\'' +
                ", locuri=" + locuri +
                '}';
    }
}
