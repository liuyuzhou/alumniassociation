package com.alumniassociation.api.vo.product;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zenghaohui
 * @date 12/3/2019 下午 6:13
 */
@Data
public class ProductFightGroupInfoVO {

    /**商品ID**/
    private Integer productId;
    /**团购商品ID**/
    private Integer groupProductId;
    /**宣传图**/
    private String advertUrl;
    /**展示图片**/
    private String showImages;
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
    /**商品状态 0，待上架 1 在线 2 已下架**/
    private String status;
    /**销量**/
    private Integer salesVolume;
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
    /**参团信息**/
    private List<Map<String,Object>> groupInfo;
    /**参团人数**/
    private Integer population;
    /**产品属性**/
    private List<Map<String,Object>> shopCategory;
    /**团购价**/
    private String fightPrice;
    /**是否已经开过团 1已经过开团 0为曾开团**/
    private String isInitiate;

}
