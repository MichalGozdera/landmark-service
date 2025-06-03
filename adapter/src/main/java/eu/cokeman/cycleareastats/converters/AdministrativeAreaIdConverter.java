package eu.cokeman.cycleareastats.converters;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdministrativeAreaIdConverter implements Converter<String, AdministrativeAreaId> {
    @Override
    public AdministrativeAreaId convert(String source) {
        return new AdministrativeAreaId(UUID.fromString(source));
    }
}
