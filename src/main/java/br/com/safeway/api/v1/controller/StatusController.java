package br.com.safeway.api.v1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Status")
public class StatusController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to SafeWay Challenge!";
    }

    @GetMapping("/status")
    public Map<String, Object> getAppStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("ok", true);
        status.put("uptime", ManagementFactory.getRuntimeMXBean().getUptime());
        return status;
    }
}
