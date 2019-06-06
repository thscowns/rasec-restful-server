package com.rasec.server.config;

import com.rasec.server.model.Device;

public class DeviceConfig {
    public static Device device = Device.builder()
            .deviceId("thscowns")
            .buzzerState(true)
            .camState(false)
            .build();
}
