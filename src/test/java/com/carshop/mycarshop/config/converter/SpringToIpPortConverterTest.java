package com.carshop.mycarshop.config.converter;

import com.carshop.mycarshop.common.message.MessageHandler;
import com.carshop.mycarshop.controller.test.ConverterController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ConverterController.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class SpringToIpPortConverterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageHandler messageHandler;  // No qualifying bean of type 'com.carshop.mycarshop.common.message.MessageHandler' available: expected at least 1 bean which qualifies as autowire candidate
    @Test
    void get1() throws Exception {

        mockMvc.perform(get("/converterIP/test/10.10.10:8080"))
                .andExpect(status().isOk())
                .andExpect(content().string("10.10.10"));

    }
    @Test
    void IpPortConverterTest(){
        SpringToIpPortConverter converter = new SpringToIpPortConverter();
        String source = "127.1.2.3:8080";
        IpPort result = converter.convert(source);
        Assertions.assertThat(result).isEqualTo(new IpPort("127.1.2.3", 8080));
    }

}