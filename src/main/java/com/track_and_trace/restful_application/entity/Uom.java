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
@Table(name = "master_uom")
public class Uom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uom")
    private int idUom;

    @Column(name = "uom_name")
    private String uomName;

    @OneToMany(mappedBy = "uom")
    private List<ProductDetail> productDetail;

}
