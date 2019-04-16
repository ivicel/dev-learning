package info.ivicel.serivce;

import java.util.List;

import info.ivicel.domain.Employee;

public interface IService<T> {
    T insert(T e);

    int delete(int empNo);

    int update(T t);

    List<T> findAll();

    List<T> findAll(int start);

    T find(int num);
}
