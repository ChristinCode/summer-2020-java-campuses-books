package org.wcci.campuslibraries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.campuslibraries.controllers.CampusController;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.CampusStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CampusControllerTest {

    private CampusStorage mockStorage;
    private CampusController underTest;
    private Model model;

    @BeforeEach
    void setUp() {
        //Arrange
        mockStorage = mock(CampusStorage.class);
        underTest = new CampusController(mockStorage);

        model = Mockito.mock(Model.class);
    }

    @Test
    public void campusControllerShouldReturnCampusTemplateWhenAskedForSingleCampusView() {
        //Act
        String templateName = underTest.showSingleCampus("Columbus", model);
        //Assert
        assertThat(templateName).isEqualTo("campus-template");
    }

    @Test
    public void showSingleCampusAsksCampusStorageForColumbusCampus() {
        //Act
        underTest.showSingleCampus("Columbus", model);
        //Assert
        verify(mockStorage).findCampusByName("Columbus");
    }

    @Test
    public void showSingleCampusAddsRetrievedCampusToModel() {
        //Arrange
        Campus testCampus = new Campus("Test Town", "TEST");
        when(mockStorage.findCampusByName("Columbus")).thenReturn(testCampus);
        //Act
        underTest.showSingleCampus("Columbus", model);
        //Assert
        verify(model).addAttribute("campus", testCampus);
    }

    @Test
    public void addingCampusShouldRedirectToHomePage() {
        //Act
        String redirectionInstructions = underTest.addNewCampus("Test Name", "Test Description");
        //Assertion
        assertThat(redirectionInstructions).isEqualTo("redirect:/");
    }

    @Test
    public void addingCampusShouldAddNewCampusToCampusStorage() {
        underTest.addNewCampus("Test Name", "Test Description");
        verify(mockStorage).addCampus(new Campus("Test Name", "Test Description"));
    }

    @Test
    public void addingCampusShouldBeMappedCorrectly() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(post("/campuses/add")
                    .param("name", "Test Name")
                    .param("description", "Test Description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).addCampus(new Campus("Test Name", "Test Description"));
    }

}
