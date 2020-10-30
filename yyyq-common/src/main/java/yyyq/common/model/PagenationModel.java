package yyyq.common.model;

import java.io.Serializable;

/**
 *PagenationModel(分页信息）
 */
public class PagenationModel implements Serializable{
    public int PageIndex ;
    public int PageSize ;
    public long TotalSize ;

    public PagenationModel(int pageIndex, int pageSize, long totalSize) {
        PageIndex = pageIndex;
        PageSize = pageSize;
        TotalSize = totalSize;
    }

    public PagenationModel() {

    }
    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public long getTotalSize() {
        return TotalSize;
    }

    public void setTotalSize(long totalSize) {
        TotalSize = totalSize;
    }
}
