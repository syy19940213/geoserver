package org.geoserver.catalog.rsmse;

import org.geoserver.catalog.CatalogInfo;
import org.geoserver.catalog.WorkspaceInfo;

public interface RsmseSymbolInfo extends CatalogInfo
{
    String getName();

    WorkspaceInfo getWorkspace();

    Integer getType();
}
