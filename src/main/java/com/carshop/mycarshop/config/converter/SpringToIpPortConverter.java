package com.carshop.mycarshop.config.converter;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;


@Log4j2
public class SpringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        log.info("StringToIpPortConverter source = {}", source);
        String[] split = source.split(":");
        String ip = split[0];
        int port = Integer.parseInt(split[1]);
        return new IpPort(ip, port);
    }
}
