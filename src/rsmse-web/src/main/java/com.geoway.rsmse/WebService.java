package com.geoway.rsmse;

import com.geoway.rsmse.bean.request.*;
import com.geoway.rsmse.bean.response.Result;
import org.geoserver.catalog.rsmse.RsmseMapConfig;

public interface WebService {

    /**
     * 样式相关操作
     * @param style
     * @return
     */
    Result addStyle(Style style);

    Result delStyle(Style style);

    Result changeStyle(Style style);

    Result getStyleList(SearchRequest styleSearchRequest);

    Result getStyle(SearchRequest styleSearchRequest);

    Result getSld(SearchRequest style);

    /**
     * 数据源相关操作
     * @param dirSearchRequest
     * @return
     */
    Result getDirList(DirSearchRequest dirSearchRequest);

    Result addSource(SourceRequest sourceRequest);

    Result getSourceList(SearchRequest sourceRequest);

    Result getSource(SearchRequest sourceRequest);

    Result delSource(SourceRequest sourceRequest);

    Result changeSource(SourceRequest sourceRequest);

    /**
     * 符号相关操作
     */
    Result addSymbol(SymbolRequest symbolRequest);

    Result changeSymbol(SymbolRequest symbolRequest);

    Result getSymbolList(SymbolSearchRequest symbolSearchRequest);

    Result getSymbol(SearchRequest symbolRequest);

    Result delSymbol(SymbolRequest symbolRequest);

    /**
     * 地图相关操作
     * @param mapConfigRequest
     * @return
     */
    Result addMapConfig(MapConfigRequest mapConfigRequest);

    Result delMapConfig(MapConfigRequest mapConfigRequest);

    Result changeMapConfig(MapConfigRequest mapConfigRequest);

    Result getMapConfig(SearchRequest mapConfigRequest);

    Result getMapConfigList(SearchRequest mapConfigSearchRequest);

    /**
     * 切片相关操作
     * @param tileRuleRequest
     * @return
     */
    Result addTileRule(TileRuleRequest tileRuleRequest);

    Result delTileRule(TileRuleRequest tileRuleRequest);

    Result changeTileRule(TileRuleRequest tileRuleRequest);

    Result getTileRuleList(SearchRequest searchRequest);

    Result getTileRule(SearchRequest searchRequest);
}
