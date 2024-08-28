package com.track_and_trace.restful_application.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {

    private int idProductDetail;

    private int idProduct;

    private String productName;

    private int idUom;

    private int idUomPackage;

    private String gtin;

    private String gtinoutbox;

    private String gtinbasket;

    private String gtinmasterbox;

    private String gtinwrapping;

    private String gtinbundling;

    private Integer wrappingQty;

    private Integer bundlingQty;

    private Integer outboxQty;

    private Boolean isVial;

    private BigDecimal minTemperature;

    private BigDecimal maxTemperature;

    private String dosis;

    private String penyuntikan;

    private String jedaPenyuntikan;

    private Boolean isSas;

    private Integer basketQty;

    private Integer masterboxQty;

    private String lblsas;

    private String kodesas;

    private String nie;

    private String lotno;

    private BigDecimal weigthProduct;

    private Boolean isAutoPrint;

    private String productflag;

    private String productType;

    private String distributionFlowType;

    private String gtinType;

    private String manufacturcountry;
}
