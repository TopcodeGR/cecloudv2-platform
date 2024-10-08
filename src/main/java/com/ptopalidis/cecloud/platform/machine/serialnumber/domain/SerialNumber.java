package com.ptopalidis.cecloud.platform.machine.serialnumber.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ptopalidis.cecloud.platform.common.security.domain.ResourceAuthorizedEntity;
import com.ptopalidis.cecloud.platform.machine.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.pcd.domain.Pcd;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SERIAL_NUMBER", uniqueConstraints = @UniqueConstraint(columnNames = {"sn", "machine_id"}))
public class SerialNumber  implements ResourceAuthorizedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties({"serialNumbers","files"})
    @ManyToOne()
    @JoinColumn(name="machine", referencedColumnName = "id")
    @NotNull
    private Machine machine;

    @Column(nullable = false)
    @NotBlank
    private String sn;

    @JsonIgnore
    @OneToOne(mappedBy = "serialNumber", cascade = CascadeType.ALL)
    private Doc doc;

    @JsonIgnore
    @OneToOne(mappedBy = "serialNumber", cascade = CascadeType.ALL)
    private Pcd pcd;

    @JsonIgnore
    @OneToOne(mappedBy = "serialNumber", cascade = CascadeType.ALL)
    private MaterialsList materialsList;

    @JsonIgnore
    @Override
    public Long getUserId() {
        return machine.getUserId();
    }
}
