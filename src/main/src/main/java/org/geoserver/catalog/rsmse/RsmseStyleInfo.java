package org.geoserver.catalog.rsmse;

import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.CatalogInfo;
import org.geoserver.catalog.WorkspaceInfo;
import org.geotools.styling.StyledLayerDescriptor;

import java.io.IOException;

public interface RsmseStyleInfo extends CatalogInfo
{
    WorkspaceInfo getWorkspace();

    String getName();

    String getData();

    void setPath(String path);

    void setData(String data);




    /**
     * 获取样式信息
     * @return
     * @throws IOException
     */
    StyledLayerDescriptor getSLD() throws IOException;

    void setCatalog(Catalog catalog);

    Catalog getCatalog();

    void setId(String format);


    Integer getDataType();

    void setDataType(Integer dataType);

    String sldTxt() throws IOException;

    String getPath();
}
