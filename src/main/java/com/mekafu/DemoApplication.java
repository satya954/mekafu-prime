package com.mekafu;

//public class DemoApplication {
//    public static void main(String[] args) {
//        System.out.println("Hello, Mekafu Prime!");
//    }
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class IPController {
    @GetMapping("/details")
    public Map<String, String> getClientDetails(HttpServletRequest request,
                                                @RequestHeader(value = "X-Forwarded-For", required = false) String forwardedFor) {
        Map<String, String> details = new HashMap<>();
        details.put("Client-IP", (forwardedFor != null) ? forwardedFor : request.getRemoteAddr());
        details.put("Source", request.getRemoteHost());
        return details;
    }
}

