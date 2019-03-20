package repository;

import domain.Client;

public interface ClientInterface extends Repository<Integer, Client> {
    Client findClientByNameAndAdresa(String nume, String adresa);
}
