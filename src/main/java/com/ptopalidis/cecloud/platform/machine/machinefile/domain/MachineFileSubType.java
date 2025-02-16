package com.ptopalidis.cecloud.platform.machine.machinefile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MachineFileSubType {

    GENERAL_DESCRIPTION(MachineFileType.PRESET),
    SAFETY_REQUIREMENTS_TABLE(MachineFileType.PRESET),
    RISK_ASSESSMENT_STUDY(MachineFileType.PRESET),
    CALCULATIONS_MANUAL(MachineFileType.PRESET),
    TRIALS_MANUAL(MachineFileType.PRESET),
    REGULATIONS_LIST(MachineFileType.PRESET),
    USAGE_MANUALS(MachineFileType.PRESET),
    QUALITY_CONTROL_PLAN(MachineFileType.PRESET),

    DOC(MachineFileType.VARIABLE),
    PCD(MachineFileType.VARIABLE),
    MATERIALS_LIST(MachineFileType.VARIABLE);

    private final MachineFileType fileType;

}
