package thymeleafexamples.stsm.business.entities.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import thymeleafexamples.stsm.business.entities.SeedStarter;


@Repository
public class SeedStarterRepository {
    private final List<SeedStarter> seedStarters = new ArrayList<>();
    public List<SeedStarter> findAll() {
        return new ArrayList<>(this.seedStarters);
    }

    public void add(final SeedStarter seedStarter) {
        seedStarters.add(seedStarter);
    }
}
