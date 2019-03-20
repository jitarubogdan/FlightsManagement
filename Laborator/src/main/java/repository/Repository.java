package repository;

public interface Repository<ID,E> {
    void save(E entity);
    E findOne(ID id);
    void delete(ID id);
    Iterable<E> findAll();
}
