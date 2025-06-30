package eu.cokeman.cycleareastats.entity;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import eu.cokeman.cycleareastats.valueObject.AreaName;

import static org.junit.jupiter.api.Assertions.*;

class AdministrativeAreaTest {
    @Test
    void builderShouldCreateObjectWithGivenValues() {
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new AreaName("Test Area"))
                .createTime(Instant.now())
                .updateTime(Instant.now())
                .build();
        assertNotNull(area);
        assertEquals("Test Area", area.getName().name());
    }
}
