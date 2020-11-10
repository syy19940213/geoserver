package org.geoserver.catalog.rsmse.impl;

import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogVisitor;
import org.geoserver.catalog.WorkspaceInfo;
import org.geoserver.catalog.rsmse.RsmseStyleInfo;
import org.geotools.styling.StyledLayerDescriptor;

import java.io.IOException;
import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-06 11:15
 **/

public class RsmseStyleInfoImpl implements RsmseStyleInfo
{

    private transient Catalog catalog;

    private String id;
    private String name;
    /**
     *   数据类型 1-json 2-sld
     */
    private Integer dataType;

    private String data;

    private String path;

//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    @Override
    public void accept(CatalogVisitor visitor)
    {

    }

    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public WorkspaceInfo getWorkspace()
    {
        return null;
    }

    @Override
    public String getName()
    {
        return this.name;
    }


    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    @Override
    public String getData()
    {
        return data;
    }

    @Override
    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public StyledLayerDescriptor getSLD() throws IOException
    {
//        return catalog.getResourcePool().getSld(this);
        return null;
    }

    public StyledLayerDescriptor parserData()
    {
        return null;
//        return catalog.getResourcePool().parserData(this.data);
    }

    @Override
    public String getPath()
    {
        return path;
    }

    @Override
    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public Integer getDataType()
    {
        return dataType;
    }

    @Override
    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

    @Override
    public String sldTxt() throws IOException
    {
        return catalog.getResourcePool().getRsmseSldTxt(this);
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
    public Catalog getCatalog()
    {
        return catalog;
    }

    @Override
    public void setCatalog(Catalog catalog)
    {
        this.catalog = catalog;
    }
}
