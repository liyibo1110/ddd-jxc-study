package com.github.liyibo1110.jxc.gateway.dto;

import lombok.Data;

/**
 * 门店信息（Gateway返回的领域层DTO，不暴露外部系统数据结构）。
 * @author liyibo
 * @date 2026-08-10 12:14
 */
@Data
public class ShopInfo {

    private Integer shopId;
    private String shopName;
    private String shopCode;
    private String address;
    private Boolean operable;
    private Boolean autoDistributionEnabled;
}
