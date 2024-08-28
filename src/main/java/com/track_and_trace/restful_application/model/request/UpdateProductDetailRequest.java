package com.track_and_trace.restful_application.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDetailRequest {

    private Integer idProductDetail;

    @JsonIgnore
    @Positive
    @NotNull
    private int idProduct;

    private Integer idUom;

    private Integer idUomPackage;

    @Size(max = 50)
    private String gtin;

    @Size(max = 50)
    private String gtinoutbox;

    @Size(max = 50)
    private String gtinbasket;

    @Size(max = 50)
    private String gtinmasterbox;

    @Size(max = 50)
    private String gtinwrapping;

    @Size(max = 50)
    private String gtinbundling;

    private Integer wrappingQty;

    private Integer bundlingQty;

    private Integer outboxQty;


    private Boolean isVial;

    private BigDecimal minTemperature;

    private BigDecimal maxTemperature;

    @Size(max = 100)
    private String dosis;

    @Size(max = 100)
    private String penyuntikan;

    @Size(max = 100)
    private String jedaPenyuntikan;

    private Boolean isSas;

    private Integer basketQty;

    private Integer masterboxQty;

    @Size(max = 100)
    private String lblsas;

    @Size(max = 100)
    private String kodesas;

    @Size(max = 100)
    private String nie;

    @Size(max = 100)
    private String lotno;

    private BigDecimal weigthProduct;

    private Boolean isAutoPrint;

    @Size(max = 50)
    private String productflag;

    @Size(max = 50)
    private String productType;

    @Size(max = 50)
    private String distributionFlowType;

    @Size(max = 50)
    private String gtinType;

    @Size(max = 100)
    private String manufacturcountry;
}
