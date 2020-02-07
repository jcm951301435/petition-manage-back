package com.ssy.petition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jcm
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.ssy.petition.dao")
public class PetitionManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetitionManageApplication.class, args);
    }

}
