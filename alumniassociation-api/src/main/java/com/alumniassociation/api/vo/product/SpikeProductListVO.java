package com.alumniassociation.api.vo.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zenghaohui
 * @date 9/3/2019 下午 5:18
 */
@Data
@Builder
public class SpikeProductListVO {

    /**秒杀列表id**/
    private Integer id;

    /**商品id**/
    private Integer productId;

    /**商品名称**/
    private String name;

    /**商品图片地址**/
    private String advertUrl;

    /**原价**/
    private BigDecimal price;

    /**秒杀价**/
    private BigDecimal spikePrice;

    /**有效期**/
    private Date validityPeriod;

    /***商品属性*/
    private List<Map<String,Object>> productAttr;


}
