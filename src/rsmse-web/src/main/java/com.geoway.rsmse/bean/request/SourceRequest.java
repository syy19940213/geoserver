package com.geoway.rsmse.bean.request;

import com.geoway.rsmse.bean.request.DataRequest;
import org.geoserver.catalog.rsmse.RsmseSourceInfo;
import org.geoserver.catalog.rsmse.impl.RsmseDataInfoImpl;
import org.geoserver.catalog.rsmse.impl.RsmseSourceInfoImpl;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 09:49
 **/

public class SourceRequest
{
    private String id;

    private String name;

    /**
     *     1-文件夹 2-数据库
     */
    private Integer sourceType;

    /**
     *  链接状态 1-已连接 2-未链接
     */
    private Integer connectionStatus;

    private DataRequest data;

    private Date createTime;

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
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

    public Integer getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(Integer sourceType)
    {
        this.sourceType = sourceType;
    }

    public Integer getConnectionStatus()
    {
        return connectionStatus;
    }

    public void setConnectionStatus(Integer connectionStatus)
    {
        this.connectionStatus = connectionStatus;
    }

    public DataRequest getData()
    {
        return data;
    }

    public void setData(DataRequest data)
    {
        this.data = data;
    }

    public RsmseSourceInfo getRsmseSourceInfo()
    {
        RsmseSourceInfoImpl rsmseSourceInfo = new RsmseSourceInfoImpl();

        RsmseDataInfoImpl rsmseDataInfo = new RsmseDataInfoImpl();
        BeanUtils.copyProperties(this.getData(),rsmseDataInfo);
        BeanUtils.copyProperties(this,rsmseSourceInfo);
        rsmseSourceInfo.setCreateTime(new Date());
        rsmseSourceInfo.setData(rsmseDataInfo);
        return rsmseSourceInfo;
    }
}

