package org.wcci.campuslibraries;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CampusControllerTest {

    @Test
    public void campusControllerShouldReturnCampusTemplateWhenAskedForSingleCampusView(){
        CampusRepository campusRepo = mock(CampusRepository.class);
        CampusController underTest = new CampusController(new CampusStorage(campusRepo));
        Model model = Mockito.mock(Model.class);
        String templateName = underTest.showSingleCampus("Columbus",model);
        assertThat(templateName).isEqualTo("campus-template");
    }

    @Test
    public void showSingleCampusAsksCampusStorageForColumbusCampus(){
        CampusStorage mockStorage = mock(CampusStorage.class);
        CampusController underTest = new CampusController(mockStorage);
        Model model = Mockito.mock(Model.class);
        underTest.showSingleCampus("Columbus", model);
        verify(mockStorage).findCampusByName("Columbus");
    }
    @Test
    public void showSingleCampusAddsRetrievedCampusToModel(){
        //Arrange
        CampusStorage mockStorage = mock(CampusStorage.class);
        CampusController underTest = new CampusController(mockStorage);
        Model model = Mockito.mock(Model.class);
        Campus testCampus = new Campus("Test Town", "TEST");
        when(mockStorage.findCampusByName("Columbus")).thenReturn(testCampus);
        //Action
        underTest.showSingleCampus("Columbus", model);
        //Assertion
        verify(model).addAttribute("campusToView", testCampus);
    }
}
