package com.vkomissarov.order.rest;

import com.vkomissarov.order.data.Health;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vkomissarov.order.data.HealthStatus;

@RestController()
@RequestMapping("/api/v1")
@Slf4j
public class HealthController {

    @GetMapping(
            value = "/health",
            produces = "application/json")
    public ResponseEntity<Health> getHealth() {
        log.debug("REST request to get the Health Status");
        final var health = new Health();
        health.setStatus(HealthStatus.UP);
        return ResponseEntity.ok().body(health);
    }
}