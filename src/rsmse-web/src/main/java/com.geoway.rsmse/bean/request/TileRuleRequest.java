package com.geoway.rsmse.bean.request;

import org.geoserver.catalog.rsmse.RsmseTileRuleInfo;
import org.geoserver.catalog.rsmse.impl.RsmseTileRuleInfoImpl;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 20:56
 **/

public class TileRuleRequest
{
    private String id;
    private String name;
    private String summary;
    private String csr;

    private Integer latitude;
    private Integer longtitude;

    private Integer width;
    private Integer height;


    /**
     * 瓦片类型 1-png 2-jpeg
     */
    private Integer tileType;
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

    public Integer getTileType()
    {
        return tileType;
    }

    public void setTileType(Integer tileType)
    {
        this.tileType = tileType;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public RsmseTileRuleInfo getRsmseTileRuleInfo()
    {
        RsmseTileRuleInfoImpl rsmseTileRuleInfo = new RsmseTileRuleInfoImpl();
        BeanUtils.copyProperties(this,rsmseTileRuleInfo);
        rsmseTileRuleInfo.setCreateTime(new Date());
        return rsmseTileRuleInfo;
    }
}
