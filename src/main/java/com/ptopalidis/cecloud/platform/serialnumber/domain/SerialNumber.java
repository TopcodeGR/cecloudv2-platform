package com.ptopalidis.cecloud.platform.serialnumber.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ptopalidis.cecloud.platform.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.pcd.domain.Pcd;
import com.topcode.web.domain.ResourceAuthorizedEntity;
import jakarta.persistence.*;
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
    private String sn;

    @OneToOne(mappedBy = "serialNumber", cascade = CascadeType.ALL)
    private Doc doc;

    @OneToOne(mappedBy = "serialNumber", cascade = CascadeType.ALL)
    private Pcd pcd;

    @OneToOne(mappedBy = "serialNumber", cascade = CascadeType.ALL)
    private MaterialsList materialsList;

    @JsonIgnore
    @Override
    public Long getAccountId() {
        return machine.getAccountId();
    }
}
