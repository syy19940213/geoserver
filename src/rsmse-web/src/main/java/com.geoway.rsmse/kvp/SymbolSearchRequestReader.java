package com.geoway.rsmse.kvp;

import com.geoway.rsmse.bean.request.SymbolSearchRequest;
import org.geoserver.ows.KvpRequestReader;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-10 09:43
 **/

public class SymbolSearchRequestReader extends KvpRequestReader
{

    public SymbolSearchRequestReader()
    {
        super(SymbolSearchRequest.class);
    }
}
