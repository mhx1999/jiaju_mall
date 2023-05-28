package com.hsp.furns.entity;

import java.util.List;

/**
 * @author 马海鑫
 * @version 1.0
 * Page是一个Javabean  是一个分页的数据模型（包含了分页的各种信息）
 */

//T泛型，因为将来分页模型对象的数据模型对应的数据类型是不确定的
public class Page<T> {

    public static final Integer PAGE_SIZE = 3;

    //当前页，显示的是第几页,前端页面得来
    private Integer pageNo;

    //每页显示几条记录
    private Integer pageSize = PAGE_SIZE ;

    //共有多少条记录，从数据库来
    private Integer totalRow;

    //共有多少页，是计算得到的,通过totalRow和pageSize计算得到
    private Integer pageTotalCount;

    //封装当前页显示的数据，从数据库来
    private List<T> items;

    //分页导航的字符串
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
