package com.ptopalidis.cecloud.platform.pdfgenerator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "file_template_asset")
public class FileTemplateAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String url;

    @Column
    @NotNull
    private String licence;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private FileTemplateType type;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name="file_template", referencedColumnName = "id")
    private FileTemplate fileTemplate;

}
