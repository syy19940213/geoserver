package org.geoserver.catalog.rsmse.impl;

import org.geoserver.catalog.CatalogVisitor;
import org.geoserver.catalog.rsmse.RsmseDataInfo;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 10:14
 **/

public class RsmseDataInfoImpl implements RsmseDataInfo
{

    private String id;

    /**
     * 1-文件 2-文件夹 3-postgis
     */
    private Integer type;

    /**
     * 具体的文件或文件夹路径
     */
    private String path;

    private String host;

    private String port;

    private String scheme;

    private String userName;

    private String password;

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

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getScheme()
    {
        return scheme;
    }

    public void setScheme(String scheme)
    {
        this.scheme = scheme;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
