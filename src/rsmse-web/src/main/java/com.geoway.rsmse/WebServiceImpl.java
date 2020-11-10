package com.geoway.rsmse;

import com.geoway.rsmse.bean.request.*;
import com.geoway.rsmse.bean.response.DirResult;
import com.geoway.rsmse.bean.response.FileResult;
import com.geoway.rsmse.bean.response.Result;
import org.apache.commons.lang3.StringUtils;
import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.rsmse.*;
import org.geoserver.catalog.rsmse.impl.*;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @description:
 * @author: syy
 * @create: 2020-11-05 17:42
 */
public class WebServiceImpl implements WebService {

    static Logger logger = org.geotools.util.logging.Logging.getLogger("org.geoserver.ows");

    private Catalog catalog;

    public WebServiceImpl(Catalog catalog)
    {
        this.catalog = catalog;
    }

    /**
     * 新增自定义样式
     * @param style
     * @return
     */
    @Override
    public Result addStyle(Style style) {

        logger.log(Level.INFO,"接收到添加请求数据"+ style.toString());
        if (StringUtils.isEmpty(style.getName()) ||
            StringUtils.isEmpty(style.getData()) ||
            style.getDataType() == null)
        {
            return Result.error("请正确填写相关参数");
        }

        RsmseStyleInfo oldStyle = catalog.getRsmseStyleByName(style.getName());
        if (oldStyle != null)
        {
            return Result.error("已有相同的样式名称");
        }
        RsmseStyleInfo rsmseStyleInfo = style.rsmseStyleInfo();
        rsmseStyleInfo.setId(String.format("RsmseStyleInfoImpl--%s", rsmseStyleInfo.getName()));
        // 添加
        catalog.add(rsmseStyleInfo);

        return Result.success(200,"新增成功");
    }

    @Override
    public Result delStyle(Style style)
    {
        logger.info("接收到删除请求数据"+ style.toString());
        RsmseStyleInfo rsmseStyleInfo = catalog.getRsmseStyleById(style.getId());
        if (rsmseStyleInfo == null)
        {
            return Result.error("没有查找到相关样式");
        }
        catalog.remove(rsmseStyleInfo);
        return Result.success(200,"删除成功");
    }

    @Override
    public Result changeStyle(Style style)
    {
        logger.info("接收到修改请求数据"+ style.toString());

        RsmseStyleInfo rsmseStyleInfo = catalog.getRsmseStyleById(style.getId());
        if (rsmseStyleInfo == null)
        {
            return Result.error("没有查找到相关样式");
        }
        RsmseStyleInfo oldStyle = catalog.getRsmseStyleByName(style.getName());
        if (oldStyle != null && oldStyle.getId().equals(style.getId()))
        {
            return Result.error("已有相同的样式名称");
        }
        RsmseStyleInfo baseStyle = style.rsmseStyleInfo();
        rsmseStyleInfo.setData(baseStyle.getData());
        rsmseStyleInfo.setDataType(baseStyle.getDataType());
        catalog.add(rsmseStyleInfo);

        return Result.success(200,"修改成功");
    }

    @Override
    public Result getStyleList(SearchRequest styleSearchRequest)
    {
        List<RsmseStyleInfo> rsmseStyleInfoList = catalog.getRsmseStyleByName(styleSearchRequest.getName(), styleSearchRequest.getPage(), styleSearchRequest.getPageSize());

        return Result.success(200,"查询成功",rsmseStyleInfoList);
    }

    @Override
    public Result getStyle(SearchRequest styleSearchRequest)
    {
        if (StringUtils.isEmpty(styleSearchRequest.getId()))
        {
            return Result.error("请正确填写请求参数");
        }
        RsmseStyleInfo rsmseStyleInfo = catalog.getRsmseStyleById(styleSearchRequest.getId());
        if (rsmseStyleInfo == null)
        {
            return Result.error("没有查找到相关资源");
        }
        String sldTxt =null;
        try
        {
            sldTxt = rsmseStyleInfo.sldTxt();
        }
        catch (IOException e)
        {
            return Result.error("读取sld文件出错");
        }
        rsmseStyleInfo.setData(sldTxt);

        return Result.success("查询成功",rsmseStyleInfo);
    }

    /**
     * 获取sld文件
     * @param styleSearchRequest
     * @return
     */
    @Override
    public Result getSld(SearchRequest styleSearchRequest)
    {
        if (StringUtils.isEmpty(styleSearchRequest.getId()))
        {
            return Result.error("请正确填写请求参数");
        }
        RsmseStyleInfo rsmseStyleInfo = this.catalog.getRsmseStyleById(styleSearchRequest.getId());
        if (rsmseStyleInfo == null)
        {
            return Result.error("没有查找到相关数据");
        }
        String sldTxt = null;
        try
        {
            sldTxt = rsmseStyleInfo.sldTxt();
        }
        catch (IOException e)
        {
            return Result.error("读取sld文件出错");
        }

        return Result.success("查询成功",sldTxt);
    }


    /**
     * 查询当前dir的路径
     * @param dirSearchRequest
     * @return
     */
    @Override
    public Result getDirList(DirSearchRequest dirSearchRequest)
    {
        if (StringUtils.isEmpty(dirSearchRequest.getNowDir()))
        {
            if (isWindows())
            {
                dirSearchRequest.setNowDir("C://");
            }
            else
            {
                dirSearchRequest.setNowDir("/");
            }
        }
        DirResult dirResult = new DirResult();

        File dir = new File(dirSearchRequest.getNowDir());
        if (!dir.exists())
        {
            return Result.error("文件目录不存在");
        }
        List<FileResult> fileList = new ArrayList<>();
        for (File file : dir.listFiles()){
            FileResult fileResult = new FileResult();
            fileResult.setName(file.getName());
            fileResult.setDir(file.isDirectory());
            fileResult.setModifiTime(new Date(file.lastModified()));
            fileResult.setSize(String.format("%sKb",file.length()/1024));
            fileList.add(fileResult);
        }

        // 排序，文件夹在前
        Collections.sort(fileList);

        dirResult.setFileList(fileList);
        dirResult.setNowDir(dirSearchRequest.getNowDir());


        return Result.success("查找成功",dirResult);
    }


    /**
     * 新增数据源信息
     * @param sourceRequest
     * @return
     */
    @Override
    public Result addSource(SourceRequest sourceRequest)
    {
        if (StringUtils.isEmpty(sourceRequest.getName()) ||
                sourceRequest.getSourceType() == null
            )
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseSourceInfo oldSource = catalog.getRsmseSourceByName(sourceRequest.getName());
        if (oldSource != null)
        {
            return Result.error("数据名称已存在");
        }

        RsmseSourceInfoImpl rsmseSourceInfo = (RsmseSourceInfoImpl) sourceRequest.getRsmseSourceInfo();
        rsmseSourceInfo.setId(String.format("RsmseSourceInfoImpl--%s", rsmseSourceInfo.getName()));
        rsmseSourceInfo.setConnectionStatus(1);
        catalog.add(rsmseSourceInfo);
        return  Result.success("添加成功");
    }

    @Override
    public Result getSourceList(SearchRequest sourceRequest)
    {
        List<RsmseSourceInfo> souceList = catalog.getRsmseSourceByName(sourceRequest.getName(),sourceRequest.getPage(),sourceRequest.getPageSize());
        return Result.success("查询成功",souceList);
    }

    @Override
    public Result getSource(SearchRequest sourceRequest)
    {
        if (StringUtils.isEmpty(sourceRequest.getId()))
        {
            return Result.error("请正确填写查询信息");
        }
        RsmseSourceInfo sourceInfo = catalog.getRsmseSourceById(sourceRequest.getId());
        if (sourceInfo == null)
        {
            return Result.error("没有查找到相关数据");
        }
        return Result.success("查询成功",sourceInfo);
    }


    @Override
    public Result delSource(SourceRequest sourceRequest)
    {
        if (StringUtils.isEmpty(sourceRequest.getId()))
        {
            return Result.error("请填写正确的请求参数");
        }

        RsmseSourceInfo rsmseSourceInfo = catalog.getRsmseSourceById(sourceRequest.getId());
        if (rsmseSourceInfo == null)
        {
            return Result.error("没有查找到相关信息");
        }
        catalog.remove(rsmseSourceInfo);
        return Result.success("删除成功");
    }

    @Override
    public Result changeSource(SourceRequest sourceRequest)
    {
        if (StringUtils.isEmpty(sourceRequest.getId()))
        {
            return Result.error("请填写正确的请求参数");
        }
        RsmseSourceInfoImpl rsmseSourceInfo = (RsmseSourceInfoImpl)catalog.getRsmseSourceById(sourceRequest.getId());
        if (rsmseSourceInfo == null)
        {
            return Result.error("没有查找到相关信息");
        }
        RsmseSourceInfo oldSource = catalog.getRsmseSourceByName(sourceRequest.getName());
        if (oldSource != null && !oldSource.getId().equals(rsmseSourceInfo.getId()))
        {
            return Result.error("数据名称已存在");
        }
        rsmseSourceInfo.setName(sourceRequest.getName());
        rsmseSourceInfo.setSourceType(rsmseSourceInfo.getSourceType());
        rsmseSourceInfo.setConnectionStatus(rsmseSourceInfo.getConnectionStatus());
        RsmseDataInfoImpl rsmseDataInfo = new RsmseDataInfoImpl();
        BeanUtils.copyProperties(sourceRequest.getData(),rsmseDataInfo);
        rsmseSourceInfo.setData(rsmseDataInfo);

        catalog.add(rsmseSourceInfo);

        return Result.success("成功");
    }

    /**
     * 添加符号信息
     * @param symbolRequest
     * @return
     */
    @Override
    public Result addSymbol(SymbolRequest symbolRequest)
    {
        if (StringUtils.isEmpty(symbolRequest.getName()) ||
                StringUtils.isEmpty(symbolRequest.getId()) ||
                StringUtils.isEmpty(symbolRequest.getThumb()) ||
                symbolRequest.getPrimitives() == null )
        {
            return Result.error("请填写正确的请求参数");
        }

        RsmseSymbolInfo symbolInfo = catalog.getRsmseSymbolByName(symbolRequest.getName());
        if (symbolInfo != null)
        {
            return Result.error("该样式名称已存在");
        }

        RsmseSymbolInfoImpl rsmseSymbolInfo = (RsmseSymbolInfoImpl)symbolRequest.getRsmseSymbolInfo();

        catalog.add(rsmseSymbolInfo);

        return Result.success("添加符号成功");
    }

    @Override
    public Result changeSymbol(SymbolRequest symbolRequest)
    {
        if (StringUtils.isEmpty(symbolRequest.getId()))
        {
            return Result.error("请填写正确的请求参数");
        }

        RsmseSymbolInfoImpl rsmseSymbolInfo = (RsmseSymbolInfoImpl) catalog.getRsmseSymbolById(symbolRequest.getId());
        if (rsmseSymbolInfo == null)
        {
            return Result.error("没有查找到相关信息");
        }

        RsmseSymbolInfoImpl oldSyboInfo = (RsmseSymbolInfoImpl) catalog.getRsmseSymbolById(symbolRequest.getName());
        if (oldSyboInfo != null && !oldSyboInfo.getId().equals(rsmseSymbolInfo.getId()))
        {
            return Result.error("该名称的符号信息已存在");
        }

        BeanUtils.copyProperties(symbolRequest,rsmseSymbolInfo);


        catalog.add(rsmseSymbolInfo);


        return Result.success("符号修改成功");
    }


    @Override
    public Result getSymbolList(SymbolSearchRequest symbolSearchRequest)
    {
        List<RsmseSymbolInfo> list = catalog.getRsmseSymbolByName(symbolSearchRequest.getType(),symbolSearchRequest.getName(),symbolSearchRequest.getPage(),symbolSearchRequest.getPageSize());
        return Result.success("查询成功",list);
    }

    @Override
    public Result getSymbol(SearchRequest symbolRequest)
    {
        if (StringUtils.isEmpty(symbolRequest.getId()))
        {
            return Result.error("请填写正确的请求参数");
        }
        RsmseSymbolInfo rsmseSymbolInfo = catalog.getRsmseSymbolById(symbolRequest.getId());
        if (rsmseSymbolInfo == null)
        {
            return Result.error("没有查找到相关信息");
        }
        return Result.success("查询成功",rsmseSymbolInfo);
    }

    @Override
    public Result delSymbol(SymbolRequest symbolRequest)
    {
        if (StringUtils.isEmpty(symbolRequest.getId()))
        {
            return Result.error("请填写正确的请求参数");
        }
        RsmseSymbolInfo rsmseSymbolInfo = catalog.getRsmseSymbolById(symbolRequest.getId());
        if (rsmseSymbolInfo == null)
        {
            return Result.error("没有查找到相关信息");
        }
        catalog.remove(rsmseSymbolInfo);
        return Result.success("删除符号成功");
    }


    /**
     * 添加地图配置
     * @param mapConfigRequest
     * @return
     */
    @Override
    public Result addMapConfig(MapConfigRequest mapConfigRequest)
    {
        if (StringUtils.isEmpty(mapConfigRequest.getName()) ||
                StringUtils.isEmpty(mapConfigRequest.getMapData()))
        {
            return Result.error("请填写正确的请求参数");
        }

        RsmseMapConfig oldMapConfig = catalog.getRsmseMapConfigByName(mapConfigRequest.getName());

        if (oldMapConfig != null)
        {
            return Result.error("已存在同名的地图配置");
        }

        RsmseMapConfigImpl rsmseMapConfig = (RsmseMapConfigImpl) mapConfigRequest.getRsmseMapConfig();
        rsmseMapConfig.setId(String.format("RsmseSourceInfoImpl--%s",mapConfigRequest.getName()));


        catalog.add(rsmseMapConfig);


        return Result.success("添加地图配置成功");
    }

    @Override
    public Result delMapConfig(MapConfigRequest mapConfigRequest)
    {
        if (StringUtils.isEmpty(mapConfigRequest.getId()))
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseMapConfig rsmseMapConfig = catalog.getRsmseMapConfigById(mapConfigRequest.getId());
        if (rsmseMapConfig == null) {
            return Result.error("没有查找到相关数据");
        }

        catalog.remove(rsmseMapConfig);


        return Result.success("删除地图配置成功");
    }

    @Override
    public Result changeMapConfig(MapConfigRequest mapConfigRequest)
    {
        if (StringUtils.isEmpty(mapConfigRequest.getId()) ||
                StringUtils.isEmpty(mapConfigRequest.getName())
        )
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseMapConfig oldMapConfig = catalog.getRsmseMapConfigById(mapConfigRequest.getName());
        if (oldMapConfig != null && !oldMapConfig.getId().equals(mapConfigRequest.getId()))
        {
            return Result.error("该名称的地图配置已经存在");
        }

        RsmseMapConfigImpl rsmseMapConfig = (RsmseMapConfigImpl) catalog.getRsmseMapConfigById(mapConfigRequest.getId());
        if (rsmseMapConfig == null) {
            return Result.error("没有查找到相关数据");
        }

        rsmseMapConfig.setLable(mapConfigRequest.getLable());
        rsmseMapConfig.setMapData(mapConfigRequest.getMapData());

        catalog.add(rsmseMapConfig);

        return Result.success("修改地图配置成功");
    }

    @Override
    public Result getMapConfig(SearchRequest mapConfigRequest)
    {
        if (StringUtils.isEmpty(mapConfigRequest.getId()))
        {
            return Result.error("请正确填写请求参数");
        }
        RsmseMapConfig rsmseMapConfig = catalog.getRsmseMapConfigById(mapConfigRequest.getId());
        if(rsmseMapConfig == null)
        {
            return Result.error("没有查找到相关数据");
        }
        return Result.success("查询成功",rsmseMapConfig);
    }


    @Override
    public Result getMapConfigList(SearchRequest mapConfigSearchRequest)
    {
        List<RsmseMapConfig> list = catalog.getRsmseMapConfigByName(mapConfigSearchRequest.getName(),mapConfigSearchRequest.getPage(),mapConfigSearchRequest.getPageSize());
        return Result.success("查询成功",list);
    }

    /**
     * 添加切片
     * @param tileRuleRequest
     * @return
     */
    @Override
    public Result addTileRule(TileRuleRequest tileRuleRequest)
    {
        if (StringUtils.isEmpty(tileRuleRequest.getName()) ||
                StringUtils.isEmpty(tileRuleRequest.getCsr()) ||
                tileRuleRequest.getHeight() == null ||
                tileRuleRequest.getWidth() == null ||
                tileRuleRequest.getLongtitude() == null ||
                tileRuleRequest.getLatitude() == null)
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseTileRuleInfo oldTileRule = catalog.getRsmseTileRuleByName(tileRuleRequest.getName());
        if (oldTileRule != null)
        {
            Result.error("存在名称相同的资源信息");
        }

        RsmseTileRuleInfoImpl rsmseTileRuleInfo = (RsmseTileRuleInfoImpl) tileRuleRequest.getRsmseTileRuleInfo();
        rsmseTileRuleInfo.setId(String.format("RsmseTileRuleInfoImpl--%s",rsmseTileRuleInfo.getName()));
        catalog.add(rsmseTileRuleInfo);

        return Result.success("添加切片规则成功");
    }

    @Override
    public  Result delTileRule(TileRuleRequest tileRuleRequest)
    {
        if (StringUtils.isEmpty(tileRuleRequest.getId()))
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseTileRuleInfo rsmseTileRuleInfo = catalog.getRsmseTileRuleById(tileRuleRequest.getId());
        if (rsmseTileRuleInfo == null)
        {
            return Result.error("没有查找到相关请求");
        }
        catalog.remove(rsmseTileRuleInfo);

        return Result.success("删除切片规则成功");
    }

    @Override
    public Result changeTileRule(TileRuleRequest tileRuleRequest)
    {
        if (StringUtils.isEmpty(tileRuleRequest.getName()) ||
                StringUtils.isEmpty(tileRuleRequest.getCsr()) ||
                tileRuleRequest.getHeight() == null ||
                tileRuleRequest.getWidth() == null ||
                tileRuleRequest.getLongtitude() == null ||
                tileRuleRequest.getLatitude() == null ||
                StringUtils.isEmpty(tileRuleRequest.getId()) )
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseTileRuleInfo oldTileRule = catalog.getRsmseTileRuleByName(tileRuleRequest.getName());
        if (oldTileRule != null && !oldTileRule.getId().equals(tileRuleRequest.getId()))
        {
            return Result.error("存在相同名称的切片规则");
        }

        RsmseTileRuleInfoImpl rsmseTileRuleInfo = (RsmseTileRuleInfoImpl) catalog.getRsmseTileRuleById(tileRuleRequest.getId());
        BeanUtils.copyProperties(tileRuleRequest,rsmseTileRuleInfo);
        rsmseTileRuleInfo.setCreateTime(new Date());
        catalog.add(rsmseTileRuleInfo);

        return Result.success("修改切片规则成功");
    }

    @Override
    public Result getTileRuleList(SearchRequest searchRequest)
    {
        List<RsmseTileRuleInfo> list = catalog.getRsmseTileRuleByName(searchRequest.getName(),searchRequest.getPage(),searchRequest.getPageSize());
        return Result.success("查询成功",list);
    }

    @Override
    public Result getTileRule(SearchRequest searchRequest)
    {
        if (StringUtils.isEmpty(searchRequest.getId()))
        {
            return Result.error("请正确填写请求参数");
        }

        RsmseTileRuleInfo rsmseTileRuleInfo = catalog.getRsmseTileRuleById(searchRequest.getId());
        if (rsmseTileRuleInfo == null)
        {
            return Result.error("没有查找到相关数据");
        }
        return Result.success("查找成功",rsmseTileRuleInfo);
    }


    private boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    }

}
