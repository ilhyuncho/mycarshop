package com.carshop.mycarshop.config.converter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class IpPort {

    private String ip;
    private int port;
}
