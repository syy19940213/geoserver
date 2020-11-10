package com.geoway.rsmse.bean.request;

import com.alibaba.fastjson.JSONObject;
import org.geoserver.catalog.rsmse.RsmseStyleInfo;
import org.geoserver.catalog.rsmse.impl.RsmseStyleInfoImpl;
import org.springframework.beans.BeanUtils;
import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-06 10:33
 **/

public class Style
{

    private String id;

    private String name;

    /**
     *   数据类型 1-json 2-sld
     */
    private Integer dataType;

    private String data;

    private String path;

    private Date createTime;

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getDataType()
    {
        return dataType;
    }

    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public RsmseStyleInfo rsmseStyleInfo()
    {
        RsmseStyleInfoImpl rsmseStyleInfo = new RsmseStyleInfoImpl();
        BeanUtils.copyProperties(this,rsmseStyleInfo);
        rsmseStyleInfo.setPath(String.format("%s.sld", rsmseStyleInfo.getName()));
        rsmseStyleInfo.setCreateTime(new Date());
        return rsmseStyleInfo;
    }

    @Override
    public String toString()
    {
        return JSONObject.toJSONString(this);
    }
}
