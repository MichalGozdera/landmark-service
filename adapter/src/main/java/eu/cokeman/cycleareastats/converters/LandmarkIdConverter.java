package eu.cokeman.cycleareastats.converters;

import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LandmarkIdConverter implements Converter<String, LandmarkId> {
    @Override
    public LandmarkId convert(String source) {
        return new LandmarkId(UUID.fromString(source));
    }
}
