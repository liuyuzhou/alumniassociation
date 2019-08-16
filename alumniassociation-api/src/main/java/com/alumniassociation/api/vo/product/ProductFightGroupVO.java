package com.alumniassociation.api.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zenghaohui
 * @date 11/3/2019 上午 11:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFightGroupVO {

    /**id**/
    private Integer id;
    /**产品id**/
    private Integer productId;
    /**产品名称**/
    private String name;
    /**产品图片**/
    private String advertUrl;
    /**原价**/
    private BigDecimal price;
    /**团购价**/
    private BigDecimal fightPrice;
    /**产品简介**/
    private String summary;
    /**产品名称**/
    private String title;
    /**已参团数**/
    private Integer population;
    /**结束时间**/
    private Date endTime;

}
