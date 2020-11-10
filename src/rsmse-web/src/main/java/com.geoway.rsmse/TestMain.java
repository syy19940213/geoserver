package com.geoway.rsmse;

import com.geoway.rsmse.bean.response.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-07 16:39
 **/

public class TestMain
{
    public static void main(String[] args) throws IOException
    {
        File file = new File("/Users/sheyuanyi/github/geoserver/src/web/app/src/main/webapp/data/rsmseStyle/11rr.xml");
        String str = FileUtils.readFileToString(file,"UTF-8");
        Result result = new Result();
        result.setData(str);
        Gson gs = new GsonBuilder().disableHtmlEscaping().create();
        String objectStr = gs.toJson(result);//把对象转为JSON格式的字符串
        System.out.println(objectStr);
    }
}
