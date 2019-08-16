package com.alumniassociation.api.vo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zenghaohui
 * @date 26/3/2019 下午 3:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaitMeasureProductVO {

    private String proId;

    private String proImg;

    private String proContent;

    private String unit;

    private String proArea;

    private BigDecimal proUnitPrice;

    private Integer productNum;

    private String attr;

    private String attrCode;

    private String height;

    private String pricingMethod;

    private String space;

    private String wallThickness;

    private String width;

    private String needRemove;

    private String proQuDes;

    /**商品来源 **/
    private String source;

    private List<ProductInfo> infoList;

    @Data
    public static class ProductInfo{

        /**宽**/
        private Float width;

        /**高度**/
        private Float height;

        /**墙厚**/
        private Float wallThickness;

        private String spaceName;

        /**面积（平方米）**/
        private Float area;

        private String needRemove;

        /**购买数量**/
        private String buyNum;

    }



}
