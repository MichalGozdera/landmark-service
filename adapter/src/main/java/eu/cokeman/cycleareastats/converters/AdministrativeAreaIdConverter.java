package eu.cokeman.cycleareastats.converters;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdministrativeAreaIdConverter implements Converter<Integer, AdministrativeAreaId> {
    @Override
    public AdministrativeAreaId convert(Integer source) {
        return new AdministrativeAreaId(source);
    }
}
