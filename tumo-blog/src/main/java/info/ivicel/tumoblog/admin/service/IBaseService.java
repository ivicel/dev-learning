package info.ivicel.tumoblog.admin.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import info.ivicel.tumoblog.admin.entity.Article;
import java.util.List;

public interface IBaseService<T> {
    List<T> findAll();

    List<T> findAllPagable(int offset, int num);

    Long findAllCount();

    Page<T> findAll(T t, Integer pageCode, Integer pageSize);

    Long save(T article);

    T findById(Long id);
}
