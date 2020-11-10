package com.geoway.rsmse.bean.request;

import com.alibaba.fastjson.JSONArray;
import org.geoserver.catalog.rsmse.RsmseSymbolInfo;
import org.geoserver.catalog.rsmse.impl.RsmseSymbolInfoImpl;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 15:33
 **/

public class SymbolRequest
{
    private String id;
    private String name;
    /**
     * 1 二维点   2 二维线  3 二维面  4 三维点  5 三维线  6 三维面
     */
    private Integer type;
    private String thumb;
    private JSONArray primitives;
    private Date createTime;


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

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getThumb()
    {
        return thumb;
    }

    public void setThumb(String thumb)
    {
        this.thumb = thumb;
    }

    public JSONArray getPrimitives()
    {
        return primitives;
    }

    public void setPrimitives(JSONArray primitives)
    {
        this.primitives = primitives;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public RsmseSymbolInfo getRsmseSymbolInfo()
    {
        RsmseSymbolInfoImpl rsmseSymbolInfo = new RsmseSymbolInfoImpl();

        BeanUtils.copyProperties(this,rsmseSymbolInfo);
        rsmseSymbolInfo.setCreateTime(new Date());

//        rsmseSymbolInfo.setPrimitives(this.getPrimitives());
        return rsmseSymbolInfo;
    }
}
