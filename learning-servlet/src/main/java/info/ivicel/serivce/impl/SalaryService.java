package info.ivicel.serivce.impl;

import java.util.List;

import info.ivicel.domain.Salary;
import info.ivicel.serivce.IService;

public class SalaryService implements IService<Salary> {
    @Override
    public Salary insert(Salary e) {
        return null;
    }

    @Override
    public int delete(int empNo) {
        return 0;
    }

    @Override
    public int update(Salary salary) {
        return 0;
    }

    @Override
    public List<Salary> findAll() {
        return null;
    }

    @Override
    public Salary find(int num) {
        return null;
    }

    @Override
    public List<Salary> findAll(int start) {
        return null;
    }
}
