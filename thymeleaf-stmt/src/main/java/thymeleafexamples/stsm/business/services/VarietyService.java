package thymeleafexamples.stsm.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import thymeleafexamples.stsm.business.entities.Variety;
import thymeleafexamples.stsm.business.entities.repositories.VarietyRepository;

@Service
public class VarietyService {
    @Autowired
    private VarietyRepository varietyRepository;

    public List<Variety> findAll() {
        return varietyRepository.findAll();
    }

    public Variety findById(final Integer id) {
        return varietyRepository.findById(id);
    }
}
