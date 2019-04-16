package thymeleafexamples.stsm.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import thymeleafexamples.stsm.business.entities.SeedStarter;
import thymeleafexamples.stsm.business.entities.repositories.SeedStarterRepository;

@Service
public class SeedStarterService {
    @Autowired
    private SeedStarterRepository seedStarterRepository;

    public List<SeedStarter> findAll() {
        return seedStarterRepository.findAll();
    }

    public void add(final SeedStarter seedStarter) {
        seedStarterRepository.add(seedStarter);
    }
}
