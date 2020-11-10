package com.geoway.rsmse.bean.request;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 19:59
 **/

public class SearchRequest
{
    private String id;

    private String name;

    private Integer page = 1 ;

    private Integer pageSize = 10;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
}
