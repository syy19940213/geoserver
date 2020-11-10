package org.geoserver.catalog.rsmse.impl;

import com.alibaba.fastjson.JSONArray;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogVisitor;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.rsmse.RsmseSymbolInfo;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 16:01
 **/

public class RsmseSymbolInfoImpl implements RsmseSymbolInfo
{
    private WorkspaceInfo workspaceInfo;

    private transient Catalog catalog;

    private String id;
    private String name;
    /**
     * 1 二维点   2 二维线  3 二维面  4 三维点  5 三维线  6 三维面
     */
    private Integer type;
    private String thumb;
    private JSONArray primitives;
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

    @Override
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
}
