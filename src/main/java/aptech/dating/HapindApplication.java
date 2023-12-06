package aptech.dating;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class HapindApplication {

    public static void main(String[] args) {
	SpringApplication.run(HapindApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
	return new ModelMapper();
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // Other ObjectMapper configurations can be added here
        return objectMapper;
    }

}
