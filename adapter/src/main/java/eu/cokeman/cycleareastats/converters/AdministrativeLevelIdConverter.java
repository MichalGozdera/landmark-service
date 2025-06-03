package eu.cokeman.cycleareastats.converters;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdministrativeLevelIdConverter implements Converter<Integer, AdministrativeLevelId> {
    @Override
    public AdministrativeLevelId convert(Integer source) {
        return new AdministrativeLevelId(source);
    }
}
