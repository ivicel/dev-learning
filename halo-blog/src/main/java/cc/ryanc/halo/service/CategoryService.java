package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.service.base.CrudService;
import java.util.List;

public interface CategoryService extends CrudService<Category, Long> {

    Category findByCateUrl(String cateUrl);

    List<Category> strListToCateList(List<String> categories);
}
