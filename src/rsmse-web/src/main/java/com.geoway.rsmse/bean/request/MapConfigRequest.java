package com.geoway.rsmse.bean.request;

import org.geoserver.catalog.rsmse.RsmseMapConfig;
import org.geoserver.catalog.rsmse.impl.RsmseMapConfigImpl;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 17:35
 **/

public class MapConfigRequest
{
    private String id;
    private String name;
    private String lable;
    private String path;
    /**
     * .map 配置
     */
    private String mapData;
    private String createUser;
    private Date createTime;

    public String getMapData()
    {
        return mapData;
    }

    public void setMapData(String mapData)
    {
        this.mapData = mapData;
    }

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

    public String getLable()
    {
        return lable;
    }

    public void setLable(String lable)
    {
        this.lable = lable;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public RsmseMapConfig getRsmseMapConfig()
    {
        RsmseMapConfigImpl rsmseMapConfig = new RsmseMapConfigImpl();
        BeanUtils.copyProperties(this,rsmseMapConfig);
        rsmseMapConfig.setCreateTime(new Date());
        rsmseMapConfig.setPath(String.format("%s.map",rsmseMapConfig.getName()));
        return rsmseMapConfig;
    }
}
