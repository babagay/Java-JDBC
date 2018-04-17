package Repository;

import SQL.Parameter;

import java.util.Collection;
import java.util.function.BiFunction;

public interface StorageRepository<T> {

    public void add(T element);
    public void remove(T element);
    public T get(int id);
    public Collection<T> getAll();

    public Collection<T> find(String sql, Collection<Parameter> parameters);
}
