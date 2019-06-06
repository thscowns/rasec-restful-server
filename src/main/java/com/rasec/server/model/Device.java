package com.rasec.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private String deviceId;
    private Boolean camState;
    private Boolean buzzerState;
    private Boolean detectState;
}
