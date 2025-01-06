package com.carshop.mycarshop.controller.test;


import com.carshop.mycarshop.config.converter.IpPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/converterIP")
public class ConverterController {

    @GetMapping("/test/{ip}")
    public String converter(@PathVariable("ip") IpPort ipPort) {

        log.error(ipPort.toString());
        return ipPort.getIp();
    }
}
