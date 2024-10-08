package com.ptopalidis.cecloud.platform.pdfgenerator.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "file_template")
public class FileTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String url;

    @Column
    @NotNull
    private String name;


    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fileTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileTemplateAsset> assets = new ArrayList<>();
}
