package com.solar.core.mybatis;

import com.solar.core.orm.db.OrderType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hushaoge on 2016/11/17.
 */
public class Pager<E> implements Serializable {
    private static final long serialVersionUID = -3178526725690528863L;
    public static final Integer MAX_PAGE_SIZE = Integer.valueOf(2147483647);
    private Integer currentPage = Integer.valueOf(1);
    private Integer pageSize = Integer.valueOf(20);
    private Integer totalCount = Integer.valueOf(0);
    private Integer pageCount = Integer.valueOf(0);
    private List<E> list;
    protected OrderType orderType;
    protected String orderColumns;

    public Pager() {
        this.orderType = OrderType.DESC;
    }

    public OrderType getOrderType() {
        return this.orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if(currentPage.intValue() < 1) {
            currentPage = Integer.valueOf(1);
        }

        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize.intValue() < 1) {
            pageSize = Integer.valueOf(1);
        } else if(pageSize.intValue() > MAX_PAGE_SIZE.intValue()) {
            pageSize = MAX_PAGE_SIZE;
        }

        this.pageSize = pageSize;
        if(this.totalCount.intValue() != 0) {
            this.setTotalCount(this.totalCount);
        }

    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        if(this.pageSize.intValue() == 0) {
            this.pageCount = Integer.valueOf(0);
        } else {
            this.pageCount = Integer.valueOf((totalCount.intValue() + this.pageSize.intValue() - 1) / this.pageSize.intValue());
        }

    }

    public Integer getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<E> getList() {
        return this.list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public boolean hasNext() {
        return this.pageCount.intValue() > this.currentPage.intValue();
    }

    public boolean hasForward() {
        return this.currentPage.intValue() > 1;
    }

    public String getOrderColumns() {
        return this.orderColumns;
    }

    public void setOrderColumns(String orderColumns) {
        this.orderColumns = orderColumns;
    }

    public Integer getP() {
        return this.currentPage;
    }

    public void setP(Integer p) {
        this.currentPage = p;
    }
}
