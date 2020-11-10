package org.geoserver.catalog.rsmse;

import org.geoserver.catalog.CatalogInfo;
import org.geoserver.catalog.WorkspaceInfo;

/**
 * @description:
 * @author: syy
 * @create: 2020-11-09 21:18
 **/

public interface RsmseTileRuleInfo extends CatalogInfo
{
    WorkspaceInfo getWorkspace();

    String getName();
}
