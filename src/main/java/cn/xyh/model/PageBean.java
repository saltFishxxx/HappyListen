package cn.xyh.model;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
    private int currentPage;
    private int pageSize;
    private List<T> Object = new ArrayList();
    private int allCounts;
    private int allPages;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getObject() {
        return Object;
    }

    public void setObject(List<T> object) {
        Object = object;
    }

    public int getAllCounts() {
        return allCounts;
    }

    public void setAllCounts(int allCounts) {
        this.allCounts = allCounts;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }
}
