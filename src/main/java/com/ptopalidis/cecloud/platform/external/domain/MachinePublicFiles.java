package com.ptopalidis.cecloud.platform.external.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MachinePublicFiles {
    private List<MachinePublicFile> generalDescriptions;
}
