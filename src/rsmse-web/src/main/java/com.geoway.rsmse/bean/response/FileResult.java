package com.geoway.rsmse.bean.response;

import java.util.Date;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 09:37
 **/

public class FileResult implements Comparable<FileResult>
{
    private String name;

    private Boolean isDir;

    private Date modifiTime;

    private String size;

    public Date getModifiTime()
    {
        return modifiTime;
    }

    public void setModifiTime(Date modifiTime)
    {
        this.modifiTime = modifiTime;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getDir()
    {
        return isDir;
    }

    public void setDir(Boolean dir)
    {
        isDir = dir;
    }

    @Override
    public int compareTo(FileResult o)
    {
        if (this.getDir())
        {
            return 1;
        }
        return 0;
    }
}
