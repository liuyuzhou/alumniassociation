package com.alumniassociation.api.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述:
 * 秒杀商品列表VO
 * @author zenghaohui
 * @date 2019/3/7 17:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpikeVO {

    /**商品id**/
    private Integer id;

    /**商品图片**/
    private String icon;

    /**商品名**/
    private String name;

    /**秒杀价**/
    private String spikePrice;

    /**原价**/
    private String originalPrice;

    /**已抢人数**/
    private Integer population;




}
