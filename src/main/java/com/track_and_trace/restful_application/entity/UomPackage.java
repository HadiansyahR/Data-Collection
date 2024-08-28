package com.track_and_trace.restful_application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "master_uom_package")
public class UomPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uom_package")
    private int idUomPackage;

    @Column(name = "uom_package_name")
    private String uomPackageName;

    @OneToMany(mappedBy = "uomPackage")
    private List<ProductDetail> productDetail;

}
