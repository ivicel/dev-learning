package info.ivicel.tumoblog.admin.service;


import info.ivicel.tumoblog.admin.entity.Category;

public interface ICategoryService extends IBaseService<Category> {

    Category findByCategoryName(String name);

    Category insert(Category category);
}
