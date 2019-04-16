package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.repository.CategoryRepository;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends AbstractCrudService<Category, Long> implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category findByCateUrl(String cateUrl) {
        return categoryRepository.findByCateUrl(cateUrl);
    }

    @Override
    public List<Category> strListToCateList(List<String> categories) {
        if (categories == null) {
            return null;
        }
        List<Long> list = new LinkedList<>();
        for (String cateId : categories) {
            try {
                list.add(Long.parseLong(cateId));
            } catch (NumberFormatException e) {
                log.warn("category id {} not exist", cateId);
            }
        }
        return categoryRepository.findAllById(list);
    }
}
