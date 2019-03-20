package group.bridge.web.model;

import java.util.List;

/**
 * @author wuran
 * @Created on 2019/3/20
 */
public class PageViewModel<T> {
    private List<T> list;
    private int count;
    private int pageCount;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
