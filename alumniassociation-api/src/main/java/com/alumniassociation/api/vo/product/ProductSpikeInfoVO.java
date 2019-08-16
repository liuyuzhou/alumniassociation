package com.alumniassociation.api.vo.product;

import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * 功能描述:
 * 秒杀商品详情
 * @author zenghaohui
 * @date 8/3/2019 下午 12:02
 */
@Data
public class ProductSpikeInfoVO {

    /**商品ID**/
    private Integer productId;
    /**秒杀商品ID**/
    private Integer spikeId;
    /**展示图片**/
    private String showImages;
    /**宣传图**/
    private String advertUrl;
    /**产品名称**/
    private String name;
    /**产品名称**/
    private String title;
    /**产品样式**/
    private String style;
    /**产品简介**/
    private String summary;
    /**产品规格参数**/
    private String specification;
    /**产品分类子标签**/
    private String tags;
    /**建议零售价格**/
    private String suggestPrice;
    /**品牌**/
    private String brand;
    /**产品分类**/
    private String category;
    /**产品序列**/
    private String seq;
    /**产品描述**/
    private String description;
    /**产品序列**/
    private String attr;
    /**计价方式**/
    private String pricingMethod;
    /**计价单位**/
    private String unit;
    /**是否单独生产**/
    private String aloneProduce;
    /**是否单独安装**/
    private String aloneInstall;
    /**型材厚度**/
    private String profileThicks;
    /**玻璃**/
    private String glass;
    /**起步计费单位**/
    private String minBillUnit;
    /**安装种类**/
    private String installType;
    /** 生产工厂**/
    private String factoryIds;
    /**产品属性**/
    private List<Map<String,Object>> shopCategory;
    /**秒杀价**/
    private String spikePrice;

}
