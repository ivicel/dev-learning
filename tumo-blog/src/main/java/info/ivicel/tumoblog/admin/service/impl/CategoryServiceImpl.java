package info.ivicel.tumoblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.entity.Category;
import info.ivicel.tumoblog.admin.mapper.CategoryMapper;
import info.ivicel.tumoblog.admin.service.ICategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> findAllPagable(int offset, int num) {
        return null;
    }

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public Page<Category> findAll(Category category, Integer pageCode, Integer pageSize) {
        return null;
    }

    @Override
    public Long save(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Category findByCategoryName(String name) {
        return categoryMapper.findByName(name);
    }

    @Override
    @Transactional
    public Category insert(Category category) {
        return categoryMapper.insertSelective(category) > 0 ? category : null;
    }
}
