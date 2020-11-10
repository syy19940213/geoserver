package com.geoway.rsmse.bean.response;

import java.util.List;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 16:11
 **/

public class DirResult
{
    private String nowDir;

    List<FileResult> fileList;

    public String getNowDir()
    {
        return nowDir;
    }

    public void setNowDir(String nowDir)
    {
        this.nowDir = nowDir;
    }

    public List<FileResult> getFileList()
    {
        return fileList;
    }

    public void setFileList(List<FileResult> fileList)
    {
        this.fileList = fileList;
    }
}
