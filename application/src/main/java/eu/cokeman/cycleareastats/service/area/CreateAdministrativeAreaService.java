package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.ConvertAdministrativeAreaGeometryUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.CreateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;


public class CreateAdministrativeAreaService implements CreateAdministrativeAreaUseCase {

    private final AdministrativeAreaRepository areaRepository;
    private final AdministrativeAreaPublisher publisher;
    private final ConvertAdministrativeAreaGeometryUseCase converter;
    private final AreaLevelBinder levelBinder;

    public CreateAdministrativeAreaService(AdministrativeAreaRepository areaRepository, AdministrativeLevelRepository levelRepository, AdministrativeAreaPublisher publisher, ConvertAdministrativeAreaGeometryUseCase converter) {
        this.areaRepository = areaRepository;
        this.publisher = publisher;
        this.converter = converter;
        this.levelBinder=new AreaLevelBinder(levelRepository);
    }

    @Override
    public AdministrativeAreaId createAdministrativeArea(AdministrativeArea area) {
        if(area.getLevel()!=null){
            area = levelBinder.bindLevelData(area);
        }
        return areaRepository.createArea(area);
    }

    @Override
    public void processSubunit(AdministrativeAreaId administrativeAreaId) {
//        AdministrativeArea administrativeArea = areaRepository.findByAdministrativeAreaId(administrativeAreaId);
//        AdministrativeAreaId parentID = areaRepository.findParent(administrativeAreaId);
//        administrativeArea = administrativeArea.toBuilder().parent(parentID).build();
//        areaRepository.updateAdministrativeArea(administrativeArea);
//
//        List<AdministrativeArea> children = areaRepository.findSubUnits(administrativeAreaId);
//        if (children != null && !children.isEmpty()) {
//            for (AdministrativeArea child : children) {
//                child = administrativeArea.toBuilder().parent(administrativeAreaId).build();
//                areaRepository.updateAdministrativeArea(child);
//            }
//        }
    }





    private AdministrativeArea bindDataFromGeometry(AdministrativeAreaGeometry geometryData, AdministrativeArea administrativeArea) {
        var geometriesSimplified = converter.getGeometriesSimplified(geometryData);
        if (geometriesSimplified.size() > 1) {
            System.out.println(geometryData.name());
        }
        administrativeArea = administrativeArea.toBuilder()
                .name(new AreaName(geometryData.name()))
                .geometry(geometryData.geometryData())
                .build();
        return administrativeArea;
    }


}
