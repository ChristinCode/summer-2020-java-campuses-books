package org.wcci.campuslibraries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.repositories.CampusRepository;
import org.wcci.campuslibraries.storage.CampusStorage;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CampusStorageTest {

    private CampusRepository campusRepo;
    private CampusStorage underTest;

    @BeforeEach
    void setUp() {
        campusRepo = mock(CampusRepository.class);
        underTest = new CampusStorage(campusRepo);
    }

    @Test
    public void shouldFindColumbus(){
        when(campusRepo.findByName("Columbus")).thenReturn(Optional.of(new Campus("Columbus","")));
        Campus result = underTest.findCampusByName("Columbus");
        assertThat(result.getName()).isEqualTo("Columbus");
    }
    @Test
    public void shouldAddCampus(){
        Campus testCampus = new Campus("Test Campus", "Campus for testing.");
        underTest.addCampus(testCampus);
        verify(campusRepo).save(testCampus);
    }
}
