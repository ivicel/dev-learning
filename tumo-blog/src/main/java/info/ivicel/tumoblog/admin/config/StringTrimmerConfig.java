package info.ivicel.tumoblog.admin.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


@Configuration
public class StringTrimmerConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.deserializerByType(String.class,
                new StdScalarDeserializer<String>(String.class) {
                    private static final long serialVersionUID = -4214089346370633158L;

                    @Override
                    public String deserialize(JsonParser p, DeserializationContext ctxt)
                            throws IOException, JsonProcessingException {
                        String str = p.getValueAsString();
                        return str == null ? null : str.trim();
                    }
                });
    }

    @ControllerAdvice
    public static class RequestParamTrimming {
        @InitBinder
        public void requestParamStringTrim(WebDataBinder binder) {
            binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        }
    }

}
