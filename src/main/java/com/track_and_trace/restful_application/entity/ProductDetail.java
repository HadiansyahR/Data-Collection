package com.track_and_trace.restful_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master_product_detail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_detail")
    private int idProductDetail;

    @OneToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_uom", referencedColumnName = "id_uom")
    private Uom uom;

    @ManyToOne
    @JoinColumn(name = "id_uom_package", referencedColumnName = "id_uom_package")
    private UomPackage uomPackage;

    private String gtin;

    private String gtinoutbox;

    private String gtinbasket;

    private String gtinmasterbox;

    private String gtinwrapping;

    private String gtinbundling;

    @Column(name = "wrapping_qty")
    private int wrappingQty;

    @Column(name = "bundling_qty")
    private int bundlingQty;

    @Column(name = "outbox_qty")
    private int outboxQty;

    @Column(name = "isvial")
    private boolean isVial;

    @Column(name = "min_temperature", precision = 5, scale = 2)
    private BigDecimal minTemperature;

    @Column(name = "max_temperature", precision = 5, scale = 2)
    private BigDecimal maxTemperature;

    private String dosis;

    private String penyuntikan;

    @Column(name = "jeda_penyuntikan")
    private String jedaPenyuntikan;

    @Column(name = "issas")
    private boolean isSas;

    @Column(name = "basket_qty")
    private int basketQty;

    @Column(name = "masterbox_qty")
    private int masterboxQty;

    private String lblsas;

    private String kodesas;

    private String nie;

    private String lotno;

    @Column(name = "weigthproduct", precision = 10, scale = 2)
    private BigDecimal weigthProduct;

    @Column(name = "isautoprint")
    private boolean isAutoPrint;

    private String productflag;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "distribution_flow_type")
    private String distributionFlowType;

    @Column(name = "gtin_type")
    private String gtinType;

    private String manufacturcountry;
}
