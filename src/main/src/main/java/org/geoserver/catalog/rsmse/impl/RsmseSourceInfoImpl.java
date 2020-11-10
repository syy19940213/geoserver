package org.geoserver.catalog.rsmse.impl;

import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogVisitor;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.rsmse.RsmseDataInfo;
import org.geoserver.catalog.rsmse.RsmseSourceInfo;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 10:13
 **/

public class RsmseSourceInfoImpl implements RsmseSourceInfo
{

    private WorkspaceInfo workspaceInfo;

    private transient Catalog catalog;


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

    private RsmseDataInfo data;

    private Date createTime;


    @Override
    public void accept(CatalogVisitor visitor)
    {

    }

    @Override
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public WorkspaceInfo getWorkspace()
    {
        return workspaceInfo;
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


    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public RsmseDataInfo getData()
    {
        return data;
    }

    public void setData(RsmseDataInfo data)
    {
        this.data = data;
    }

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }
}
