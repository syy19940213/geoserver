package com.geoway.rsmse.response;

import com.geoway.rsmse.bean.response.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.geoserver.ows.Response;
import org.geoserver.platform.Operation;
import org.geoserver.platform.Service;
import org.geoserver.platform.ServiceException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-05 23:36
 **/

public class JSONResponse extends Response
{


    public static final String MIME_TYPE = "application/json";

    private Service service;



    public JSONResponse(Service service)
    {
        super(Result.class);
        this.service = service;
    }

    /** Makes sure this triggers only */
    @Override
    public boolean canHandle(Operation operation) {
        // is this a wcs 1.1.1 or 1.1.0 one?
        for (String serviceOperation : service.getOperations())
        {
           if (serviceOperation.equalsIgnoreCase(operation.getId()))
           {
               return true;
           }
        }
        return false;
    }

    @Override
    public String getMimeType(Object value, Operation operation) throws ServiceException {
        return MIME_TYPE;
    }

    @Override
    public void write(Object value, OutputStream output, Operation operation) throws IOException, ServiceException
    {

        Result result = (Result) value;
        PrintWriter writer = new PrintWriter(output);
        try {

            Gson gs = new GsonBuilder()
                    .disableHtmlEscaping()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            String objectStr = gs.toJson(result);

            writer.write(objectStr);
        } finally {
            writer.close();
        }
    }

    public String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
}
