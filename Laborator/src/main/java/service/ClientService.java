package service;

import domain.Client;
import repository.ClientInterface;
import repository.Repository;

public class ClientService {
    private ClientInterface repo;

    public ClientService(ClientInterface repo) {
        this.repo = repo;
    }

    public void save(Client c){
        repo.save(c);
    }
}
