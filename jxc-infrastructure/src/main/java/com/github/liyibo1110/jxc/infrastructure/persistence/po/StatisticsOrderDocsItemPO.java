package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订货明细统计表PO。
 * @author liyibo
 * @date 2026-08-14 10:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("statistics_order_docs_item")
public class StatisticsOrderDocsItemPO extends BasePO {

    private Long orderItemId;
    private Long orderId;
    private String materialCode;
    private String materialName;
    private String orderCount;
    private String unit;
    private Long shopId;
    private String shopName;
    private LocalDate orderDate;
    private LocalDateTime orderTime;
    private Long materialCategoryId;
}
