package com.ptopalidis.cecloud.platform.external.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MachinePublicFile {

    private String fileName;
    private String url;
    private String subType;

}
