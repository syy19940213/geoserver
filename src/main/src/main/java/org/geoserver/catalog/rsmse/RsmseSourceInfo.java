package org.geoserver.catalog.rsmse;

import org.geoserver.catalog.CatalogInfo;
import org.geoserver.catalog.WorkspaceInfo;

public interface RsmseSourceInfo extends CatalogInfo
{

    String getName();

    WorkspaceInfo getWorkspace();
}
