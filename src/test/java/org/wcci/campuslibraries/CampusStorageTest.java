package org.wcci.campuslibraries;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CampusStorageTest {

    @Test
    public void shouldFindColumbus(){
        CampusRepository campusRepo = mock(CampusRepository.class);
        CampusStorage underTest = new CampusStorage(campusRepo);
        Campus result = underTest.findCampusByName("Columbus");
        assertThat(result.getName()).isEqualTo("Columbus");
    }
}
