package org.geoserver.catalog.rsmse.impl;

import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogVisitor;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.rsmse.RsmseTileRuleInfo;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 21:18
 **/

public class RsmseTileRuleInfoImpl implements RsmseTileRuleInfo
{
    private transient Catalog catalog;

    private WorkspaceInfo workspaceInfo;

    private String id;
    private String name;
    private String summary;
    private String csr;

    private Integer latitude;
    private Integer longtitude;

    private Integer width;
    private Integer height;

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

    public Catalog getCatalog()
    {
        return catalog;
    }

    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public WorkspaceInfo getWorkspace()
    {
        return workspaceInfo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getCsr()
    {
        return csr;
    }

    public void setCsr(String csr)
    {
        this.csr = csr;
    }

    public Integer getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Integer latitude)
    {
        this.latitude = latitude;
    }

    public Integer getLongtitude()
    {
        return longtitude;
    }

    public void setLongtitude(Integer longtitude)
    {
        this.longtitude = longtitude;
    }

    public Integer getWidth()
    {
        return width;
    }

    public void setWidth(Integer width)
    {
        this.width = width;
    }

    public Integer getHeight()
    {
        return height;
    }

    public void setHeight(Integer height)
    {
        this.height = height;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
