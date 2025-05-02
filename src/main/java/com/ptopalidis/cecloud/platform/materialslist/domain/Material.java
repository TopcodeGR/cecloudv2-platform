package com.ptopalidis.cecloud.platform.materialslist.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MATERIAL")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="materials_list", referencedColumnName = "id")
    private MaterialsList materialsList;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name="description")
    private String description;

    @Column(nullable = false, name="sn")
    private String sn;

    @Column(nullable = false, name="supplier")
    private String supplier;

}
