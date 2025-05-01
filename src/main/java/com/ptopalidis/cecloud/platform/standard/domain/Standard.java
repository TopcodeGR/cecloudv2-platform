package com.ptopalidis.cecloud.platform.standard.domain;

import com.topcode.pdfgenerator.domain.FileTemplate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STANDARD")
public class Standard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @OneToOne()
    @JoinColumn(name = "doc_file_template", referencedColumnName = "id", unique = true)
    private FileTemplate docFileTemplate;
}
