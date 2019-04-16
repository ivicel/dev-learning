package info.ivicel.controller;

public class PageResult<T> {
    private long currentPage;
    private long totalPage;
    private T t;

    public boolean hasNext() {
        return currentPage * 15 < totalPage;
    }

    public boolean hasPrev() {
        return (currentPage != 1);
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
