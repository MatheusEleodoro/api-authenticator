package br.com.eleodoro.dev.utils.converter;


import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ByteBoolean implements Converter<Byte, Boolean> {


    @Override
    public Boolean convert(@NonNull Byte source) {
        return source != 0;
    }
}
