package eu.cokeman.cycleareastats.converters;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdministrativeAreaIdConverter implements Converter<Integer, AdministrativeAreaId> {
  @Override
  public AdministrativeAreaId convert(Integer source) {
    return new AdministrativeAreaId(source);
  }
}
