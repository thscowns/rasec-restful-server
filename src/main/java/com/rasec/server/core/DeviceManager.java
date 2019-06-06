package com.rasec.server.core;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeviceManager extends CoapResource {
    private ApiController apiController;
    @Autowired
    public DeviceManager() {
        super("devices");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        log.info("POST REQUEST!!");
    }
}
