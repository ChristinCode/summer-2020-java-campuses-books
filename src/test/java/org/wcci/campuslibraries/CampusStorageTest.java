package org.wcci.campuslibraries;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CampusStorageTest {

    @Test
    public void shouldFindColumbus(){
        CampusStorage underTest = new CampusStorage();
        Campus result = underTest.findCampusByName("Columbus");
        assertThat(result.getName()).isEqualTo("Columbus");
    }
}
