package com.ptopalidis.cecloud.platform.machine.domain;

import com.ptopalidis.cecloud.platform.common.domain.ApiPage;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

@Schema(name = "MachinesPage", description = "Paginated response for machines")
public class MachinesPage  extends ApiPage<Machine> {
    public MachinesPage(Page<Machine> page) {
        super(page);
    }
}