package com.ptopalidis.cecloud.platform.machinefile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.topcode.web.domain.ResourceAuthorizedEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "MACHINE_FILE")
@RequiredArgsConstructor
@AllArgsConstructor
public class MachineFile implements ResourceAuthorizedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnoreProperties("files")
    @ManyToOne()
    @JoinColumn(name="machine", referencedColumnName = "id")
    private Machine machine;

    @Column(nullable = false,name = "s3_key")
    @NotBlank
    private String s3key;

    @Column(nullable = false,name = "original_file_name")
    @NotBlank
    private String originalFileName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private MachineFileSubType subType;

    @Column(nullable = false)
    @NotBlank
    private String url;

    @Column(nullable = false,name = "content_type")
    private String contentType;

    @Column(nullable = false)
    private Long size;


    @JsonIgnore
    @Override
    public Long getAccountId() {
        return machine.getAccountId();
    }
}
