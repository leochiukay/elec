package com.telek.elec;

import com.telek.elec.netty.NettyStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class ElecApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElecApplication.class, args);
    }
}
