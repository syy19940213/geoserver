package org.geoserver.catalog.rsmse;

import org.geoserver.catalog.CatalogInfo;
import org.geoserver.catalog.WorkspaceInfo;

public interface RsmseMapConfig extends CatalogInfo
{
    WorkspaceInfo getWorkspace();

    String getName();
}
