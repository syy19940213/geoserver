package org.geoserver.catalog.rsmse.impl;

import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogVisitor;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.rsmse.RsmseMapConfig;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 17:42
 **/

public class RsmseMapConfigImpl implements RsmseMapConfig
{


    private transient Catalog catalog;

    private WorkspaceInfo workspaceInfo;


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


    @Override
    public void accept(CatalogVisitor visitor)
    {

    }

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }

    @Override
    public String getId()
    {
        return id;
    }

    public String getMapData()
    {
        return mapData;
    }

    public void setMapData(String mapData)
    {
        this.mapData = mapData;
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


    @Override
    public WorkspaceInfo getWorkspace()
    {
        return workspaceInfo;
    }
}
