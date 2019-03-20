package domain;

public class Angajat implements HasID<String> {
    private String user;
    private String password;

    public Angajat(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public String getID() {
        return user;
    }

    @Override
    public void setID(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
