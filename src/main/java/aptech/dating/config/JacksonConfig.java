//package aptech.dating.config;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.text.SimpleDateFormat;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
//        converter.setObjectMapper(objectMapper);
//        return converter;
//    }
//}
