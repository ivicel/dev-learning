package info.ivicel.service.impl;

import info.ivicel.domain.Authority;
import info.ivicel.repository.AuthorityRepository;
import info.ivicel.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authorityService")
public class AuthorityServiceImpl implements IAuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }

}
