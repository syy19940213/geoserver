package com.geoway.rsmse.kvp;

import com.geoway.rsmse.bean.request.SearchRequest;
import org.geoserver.ows.KvpRequestReader;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-07 10:54
 **/

public class SearchRequestReader extends KvpRequestReader
{


    public SearchRequestReader()
    {
        super(SearchRequest.class);
    }

}
