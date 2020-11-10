package com.geoway.rsmse.bean.request;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-10 09:42
 **/

public class SymbolSearchRequest extends SearchRequest
{

    private Integer type;

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }
}
