package yyyq.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * BaseSearchModel
 *
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseSearchModel<T> implements Serializable {
    private T viewModel;

    private Integer pageIndex;

    private Integer pageSize;

    private Integer totalSize;

    private String sortField;

    private String sortOrder;

    public Integer getPageIndex() {
        if (pageIndex == null) {
            return 1;
        }
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 20;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        if (totalSize == null) {
            return 0;
        }
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public T getViewModel() {
        return viewModel;
    }

    public void setViewModel(T viewModel) {
        this.viewModel = viewModel;
    }
}
