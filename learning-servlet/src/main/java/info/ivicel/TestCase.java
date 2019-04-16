package info.ivicel;


import org.junit.Test;

import java.sql.Date;

import info.ivicel.dao.IDao;
import info.ivicel.dao.impl.EmployeeDaoImpl;
import info.ivicel.dao.impl.JdbcTemplate;
import info.ivicel.domain.Employee;
import info.ivicel.serivce.IService;
import info.ivicel.serivce.impl.EmployeeService;

public class TestCase {
    @Test
    public void testDb() {
        IService service = new EmployeeService();
        ((EmployeeService)service).setEmployeeDao(new EmployeeDaoImpl());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            service.findAll();
        }
        System.out.println("Cost time: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testSave() {
        IDao dao = new EmployeeDaoImpl();
        Employee e = new Employee();
        e.setBirthDate(new Date(2011 - 1900, 1, 1));
        e.setEmpNo(2011);
        e.setFirstName("john");
        e.setLastName("smith");
        e.setGender("M");
        e.setHireDate(new Date(2077 - 1900, 1, 1));
        dao.insert(e);
    }

    @Test
    public void testUpdate() {
        IDao dao = new EmployeeDaoImpl();
        Employee e = new Employee();
        e.setBirthDate(new Date(2011 - 1900, 1, 1));
        e.setEmpNo(2011);
        e.setFirstName("john");
        e.setLastName("smith");
        e.setHireDate(new Date(2077 - 1900, 3, 1));
        dao.update(e);
    }

    @Test
    public void testDelete() {
        IDao dao = new EmployeeDaoImpl();
        dao.delete(2011);
    }

    @Test
    public void testQuery() {
        IDao dao = new EmployeeDaoImpl();
        System.out.println(dao.find(2011));
    }

    @Test
    public void testQueryAll() {
        IDao dao = new EmployeeDaoImpl();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            dao.findAll();
        }
        System.out.println("Cost: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testQueryObject() {
        JdbcTemplate.queryObject("emp_no", 2011, Employee.class);
    }

    @Test
    public void testCount() {
        long count = JdbcTemplate.query("SELECT count(*) FROM `employees`");
        System.out.println(count);
    }
}
