package com.ptopalidis.cecloud.platform.machine.materialslist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptopalidis.cecloud.platform.common.security.domain.ResourceAuthorizedEntity;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.pdfgenerator.domain.ReportingEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "MATERIALS_LIST")
public class MaterialsList extends ReportingEntity implements ResourceAuthorizedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name="issuance_date")
    private LocalDate issuanceDate;

    @NotNull
    @Column(nullable = false, name="production_date")
    private LocalDate productionDate;

    @NotNull
    @Column(nullable = false, name="production_manager")
    private String productionManager;

    @OneToOne()
    @JoinColumn(name = "serial_number", referencedColumnName = "id", unique = true)
    @JsonIgnore
    private SerialNumber serialNumber;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "materialsList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materials = new ArrayList<>();

    @JsonIgnore
    @Override
    public Long getAccountId() {
        return serialNumber.getMachine().getAccountId();
    }

    public void setMaterials(@Valid List<Material> materials) {
        this.materials.clear();
        this.materials.addAll(materials);
    }
}
