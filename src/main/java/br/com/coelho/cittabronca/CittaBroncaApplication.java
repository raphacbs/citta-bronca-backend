package br.com.coelho.cittabronca;

import br.com.coelho.cittabronca.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CittaBroncaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CittaBroncaApplication.class, args);
    }

}
