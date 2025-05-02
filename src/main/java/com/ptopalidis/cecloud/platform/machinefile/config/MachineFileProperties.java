package com.ptopalidis.cecloud.platform.machinefile.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "machinefiles")
@Getter
@Setter
public class MachineFileProperties {
    private String s3directory;
}
