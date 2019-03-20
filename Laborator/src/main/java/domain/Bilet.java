package domain;

public class Bilet implements HasID<Integer> {

    private Integer id;
    private Integer idZbor;
    private Integer idClient;
    private String turisti;
    private Integer nrLocuri;

    public Bilet(Integer id, Integer idZbor, Integer idClient, String turisti, Integer nrLocuri) {
        this.id = id;
        this.idZbor = idZbor;
        this.idClient = idClient;
        this.turisti = turisti;
        this.nrLocuri = nrLocuri;
    }


    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer integer) {
        this.id = integer;
    }


    public Integer getIdZbor() {
        return idZbor;
    }

    public void setIdZbor(Integer idZbor) {
        this.idZbor = idZbor;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getTuristi() {
        return turisti;
    }

    public void setTuristi(String turisti) {
        this.turisti = turisti;
    }

    public Integer getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(Integer nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + id +
                ", idZbor=" + idZbor +
                ", idClient=" + idClient +
                ", turisti='" + turisti + '\'' +
                ", nrLocuri=" + nrLocuri +
                '}';
    }
}
