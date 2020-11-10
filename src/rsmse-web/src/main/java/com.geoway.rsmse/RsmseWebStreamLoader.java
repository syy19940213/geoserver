package com.geoway.rsmse;

import org.geoserver.config.GeoServer;
import org.geoserver.config.util.LegacyServicesReader;
import org.geoserver.config.util.XStreamServiceLoader;
import org.geoserver.platform.GeoServerResourceLoader;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-05 22:29
 **/

public class RsmseWebStreamLoader extends XStreamServiceLoader<RsmseWebInfo>
{
    public RsmseWebStreamLoader(GeoServerResourceLoader resourceLoader)
    {
        super(resourceLoader, "rsmes-web");
    }

    public RsmseWebInfo load(LegacyServicesReader reader, GeoServer geoServer) throws Exception
    {
        RsmseWebInfoImpl rsmseWebInfo = new RsmseWebInfoImpl();
        rsmseWebInfo.setId("rsmes-web");
        return rsmseWebInfo;
    }

        @Override
    protected RsmseWebInfo createServiceFromScratch(GeoServer gs)
    {
        RsmseWebInfoImpl wms = new RsmseWebInfoImpl();
        wms.setName("RSMES-WEB");
        return wms;
    }

    @Override
    public Class<RsmseWebInfo> getServiceClass()
    {
        return RsmseWebInfo.class;
    }
}
