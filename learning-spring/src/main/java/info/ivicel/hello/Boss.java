package info.ivicel.hello;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Boss {
    @Autowired
    private List<Person> persons;


    public List<Person> getPersons() {
        return persons;
    }
}
