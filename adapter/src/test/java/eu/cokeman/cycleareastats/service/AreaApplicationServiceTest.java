package eu.cokeman.cycleareastats.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelBasicDto;
import eu.cokeman.cycleareastats.openapi.model.CreateAdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.CreateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AreaApplicationServiceTest {
  private CreateAdministrativeAreaUseCase createAdministrativeAreaUseCase;
  private AreaApplicationService service;
  private AdministrativeAreaExternalMapper areaMapper;

  // Correct mocks for use cases
  private final eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase
      mockFetchAdministrativeAreaUseCase =
          mock(
              eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase
                  .class);
  private final eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase
      mockDeleteAdministrativeAreaUseCase =
          mock(
              eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase
                  .class);
  private final eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase
      mockUpdateAdministrativeAreaUseCase =
          mock(
              eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase
                  .class);
  private final eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase
      mockFilterAdministrativeAreaUseCase =
          mock(
              eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase
                  .class);

  @BeforeEach
  void setUp() {
    createAdministrativeAreaUseCase = mock(CreateAdministrativeAreaUseCase.class);
    service = new AreaApplicationService(null, null, null, null, createAdministrativeAreaUseCase);
    areaMapper = AdministrativeAreaExternalMapper.INSTANCE;
  }

  @Test
  void shouldCreateAdministrativeAreaAndReturnId() {
    // Arrange: create DTO and expected entity
    AdministrativeAreaRequestDto requestDto = new AdministrativeAreaRequestDto();
    requestDto.setName("Test Area");
    AdministrativeLevelBasicDto level = new AdministrativeLevelBasicDto();
    level.setName("CITY");
    level.setCountry("Poland");
    requestDto.setLevel(level);
    CreateAdministrativeAreaRequestDto createDto = new CreateAdministrativeAreaRequestDto();
    createDto.setRequest(requestDto);
    AdministrativeArea area = areaMapper.mapToInternal(requestDto).build();
    AdministrativeAreaId expectedId = new AdministrativeAreaId(123);
    when(createAdministrativeAreaUseCase.createAdministrativeArea(area)).thenReturn(expectedId);

    // Act
    AdministrativeAreaId result = service.createAdministrativeArea(createDto);

    // Assert: returned id is as expected and use case is called
    assertEquals(expectedId, result);
    verify(createAdministrativeAreaUseCase, times(1)).createAdministrativeArea(area);
  }

  @Test
  void shouldThrowExceptionWhenCreateAdministrativeAreaFails() {
    // Arrange: simulate exception from use case
    AdministrativeAreaRequestDto requestDto = new AdministrativeAreaRequestDto();
    requestDto.setName("Test Area");
    AdministrativeLevelBasicDto level = new AdministrativeLevelBasicDto();
    level.setName("CITY");
    level.setCountry("Poland");
    requestDto.setLevel(level);
    CreateAdministrativeAreaRequestDto createDto = new CreateAdministrativeAreaRequestDto();
    createDto.setRequest(requestDto);
    AdministrativeArea area = areaMapper.mapToInternal(requestDto).build();
    when(createAdministrativeAreaUseCase.createAdministrativeArea(area))
        .thenThrow(new RuntimeException("creation failed"));

    // Act & Assert: exception is propagated
    assertThrows(RuntimeException.class, () -> service.createAdministrativeArea(createDto));
    verify(createAdministrativeAreaUseCase, times(1)).createAdministrativeArea(area);
  }

  // Example test for another use case: fetchAdministrativeArea
  @Test
  void shouldFetchAdministrativeAreaById() {
    // Arrange: mock dependencies and expected result
    AdministrativeAreaId areaId = new AdministrativeAreaId(42);
    AdministrativeArea expectedArea =
        AdministrativeArea.builder().id(areaId).name(new AreaName("Cieszyn")).build();
    AreaApplicationService serviceWithFetch =
        new AreaApplicationService(mockFetchAdministrativeAreaUseCase, null, null, null, null);
    when(mockFetchAdministrativeAreaUseCase.findArea(areaId)).thenReturn(expectedArea);

    // Act
    AdministrativeAreaResponseDto result = serviceWithFetch.loadAdministrativeArea(areaId.value());

    // Assert: returned area fields are as expected and use case is called
    assertEquals(expectedArea.getId().value(), result.getId());
    assertEquals(expectedArea.getName().name(), result.getName());
    verify(mockFetchAdministrativeAreaUseCase, times(1)).findArea(areaId);
  }

  // Example test for another use case: deleteAdministrativeArea
  @Test
  void shouldDeleteAdministrativeAreaById() {
    // Arrange: mock dependencies
    AdministrativeAreaId areaId = new AdministrativeAreaId(42);
    AreaApplicationService serviceWithDelete =
        new AreaApplicationService(null, null, mockDeleteAdministrativeAreaUseCase, null, null);

    // Act
    serviceWithDelete.deleteAdministrativeArea(areaId.value());

    // Assert: use case is called
    verify(mockDeleteAdministrativeAreaUseCase, times(1)).deleteAdministrativeArea(areaId);
  }

  // Example test for another use case: updateAdministrativeArea
  @Test
  void shouldUpdateAdministrativeArea() {
    // Arrange: mock dependencies and expected result
    AdministrativeAreaId areaId = new AdministrativeAreaId(42);
    AdministrativeAreaRequestDto requestDto = new AdministrativeAreaRequestDto();
    requestDto.setName("Test Area");
    AdministrativeLevelBasicDto level = new AdministrativeLevelBasicDto();
    level.setName("CITY");
    level.setCountry("Poland");
    requestDto.setLevel(level);
    AdministrativeArea area = areaMapper.mapToInternal(requestDto).id(areaId).build();
    AdministrativeArea updatedArea = area.toBuilder().build();
    AreaApplicationService serviceWithUpdate =
        new AreaApplicationService(null, mockUpdateAdministrativeAreaUseCase, null, null, null);
    when(mockUpdateAdministrativeAreaUseCase.updateAdministrativeArea(
            eq(areaId), any(AdministrativeArea.class)))
        .thenReturn(updatedArea);

    // Act
    AdministrativeAreaResponseDto result =
        serviceWithUpdate.updateAdministrativeArea(areaId.value(), requestDto);

    // Assert: returned area is as expected and use case is called
    assertEquals(updatedArea.getId().value(), result.getId());
    verify(mockUpdateAdministrativeAreaUseCase, times(1))
        .updateAdministrativeArea(eq(areaId), any(AdministrativeArea.class));
  }

  @Test
  void shouldFilterAdministrativeAreasByLevelAndCountry() {
    // Arrange: mock dependencies and expected result
    String level = "CITY";
    String country = "Poland";
    List<AdministrativeArea> expectedAreas =
        List.of(
            AdministrativeArea.builder()
                .id(new AdministrativeAreaId(1))
                .name(new AreaName("Area"))
                .build());
    AreaApplicationService serviceWithFilter =
        new AreaApplicationService(null, null, null, mockFilterAdministrativeAreaUseCase, null);
    when(mockFilterAdministrativeAreaUseCase.findByLevelAndCountry(level, country))
        .thenReturn(expectedAreas);

    // Act
    List<AdministrativeAreaResponseDto> result =
        serviceWithFilter.getAdministrativeAreasByLevelAndCountry(level, country);

    // Assert: returned list is as expected and use case is called
    assertEquals(expectedAreas.size(), result.size());
    verify(mockFilterAdministrativeAreaUseCase, times(1)).findByLevelAndCountry(level, country);
  }

  @Test
  void shouldFilterAdministrativeAreasByMetadata() {
    // Arrange: mock dependencies and expected result
    String metadata = "desc";
    List<AdministrativeArea> expectedAreas =
        List.of(
            AdministrativeArea.builder()
                .id(new AdministrativeAreaId(1))
                .name(new AreaName("Test Area"))
                .build());
    AreaApplicationService serviceWithFilter =
        new AreaApplicationService(null, null, null, mockFilterAdministrativeAreaUseCase, null);
    when(mockFilterAdministrativeAreaUseCase.findByMetadataContains(metadata))
        .thenReturn(expectedAreas);

    // Act
    List<AdministrativeAreaResponseDto> result =
        serviceWithFilter.getAdministrativeAreasByMetadata(metadata);

    // Assert: returned list is as expected and use case is called
    assertEquals(expectedAreas.size(), result.size());
    assertEquals(expectedAreas.get(0).getName().name(), result.get(0).getName());
    verify(mockFilterAdministrativeAreaUseCase, times(1)).findByMetadataContains(metadata);
  }
}
