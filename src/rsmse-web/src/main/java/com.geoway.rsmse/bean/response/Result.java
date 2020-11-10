package com.geoway.rsmse.bean.response;


import java.io.Serializable;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-07 14:37
 **/

public class Result implements Serializable
{

    private static Integer SUCCESS = 200;
    private static Integer ERROR = 500;


    public Result()
    {}


    public Result(Integer code)
    {
        this.code = code;
    }

    public Result(Integer code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code,String msg,Object obj)
    {
        this.code = code;
        this.msg = msg;
        this.data = obj;
    }

    private String msg;

    private Integer code;

    private Object data;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public static Result success(Integer code,String msg)
    {
        return new Result(code,msg);
    }

    public static Result success(Integer code,String msg,Object data)
    {
        return new Result(code,msg,data);
    }

    public static Result success(String msg)
    {
        return new Result(Result.SUCCESS,msg);
    }

    public static Result success(String msg,Object data)
    {
        return new Result(Result.SUCCESS,msg,data);
    }

    public static Result error(String msg)
    {
        return new Result(Result.ERROR,msg);
    }
}
