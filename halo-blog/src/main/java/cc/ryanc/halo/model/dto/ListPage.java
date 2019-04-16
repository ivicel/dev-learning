package cc.ryanc.halo.model.dto;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListPage<T> implements Iterable<T> {
    private List<T> data;
    private int prePage;
    private int nextPage;
    private boolean hasNext;
    private boolean hasPrevious;
    private int nowPage;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private int size;

    public ListPage(List<T> data, int nowPage, int pageSize) {
        this.data = data;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
        this.totalCount = data.size();
        this.totalPage = (this.totalCount + pageSize - 1) / pageSize;
        this.prePage = nowPage - 1 > 1 ? nowPage - 1 : 1;
        this.nextPage = nowPage + 1 > totalPage ? totalPage : nowPage + 1;
        this.hasPrevious = prePage != nowPage;
        this.hasNext = nextPage != nowPage;
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        data.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return data.spliterator();
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public List<T> getPageList() {
        int fromIndex = (nowPage - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return Collections.emptyList();
        }
        if (fromIndex < 0) {
            return Collections.emptyList();
        }
        int toIndex = nowPage * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }
}
