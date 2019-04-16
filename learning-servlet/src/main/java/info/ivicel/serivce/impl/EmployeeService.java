package info.ivicel.serivce.impl;

import java.util.List;

import info.ivicel.dao.IDao;
import info.ivicel.dao.impl.EmployeeDaoImpl;
import info.ivicel.serivce.IService;
import info.ivicel.domain.Employee;
import lombok.Setter;

@Setter
public class EmployeeService implements IService<Employee> {
    private IDao<Employee> employeeDao;

    public EmployeeService() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public List<Employee> findAll(int start) {
        return employeeDao.findAll(start);
    }

    @Override
    public Employee insert(Employee e) {
        return employeeDao.insert(e);
    }

    @Override
    public int delete(int empNo) {
        return employeeDao.delete(empNo);
    }

    @Override
    public int update(Employee e) {
        return employeeDao.update(e);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee find(int empNo) {
        return employeeDao.find(empNo);
    }
}
