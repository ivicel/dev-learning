package info.ivicel.dao;

import java.util.List;


public interface IDao<T> {
    T insert(T e);

    int delete(int empNo);

    int update(T e);

    List<T> findAll();

    T find(int empNo);

    Object query(String sql, Class clazz);

    List<T> findAll(int start);
}
