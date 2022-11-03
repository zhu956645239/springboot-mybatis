package cn.gy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.gy.mapper")
public class Application {


    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
