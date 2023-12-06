package aptech.dating;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HapindApplication {

    public static void main(String[] args) {
	SpringApplication.run(HapindApplication.class, args);
    }
//Test thu cai nha
    @Bean
    public ModelMapper getModelMapper() {
	return new ModelMapper();
    }

}
