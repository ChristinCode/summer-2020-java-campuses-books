package org.wcci.campuslibraries.integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.entities.Book;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.AuthorStorage;
import org.wcci.campuslibraries.storage.BookStorage;
import org.wcci.campuslibraries.storage.CampusStorage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class WebMvcLayerTest {

    @MockBean
    private AuthorStorage authorStorage;

    @MockBean
    private CampusStorage campusStorage;

    @MockBean
    private BookStorage bookStorage;

    @Autowired
    private MockMvc mockMvc;

    private Campus testCampus;

    @BeforeEach
    void setUp() {
        testCampus = new Campus("Columbus", "A fun place to get books.");
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void shouldReceiveOKFromHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("authors", "campuses"))
                .andExpect(view().name("home-template"));
        verify(campusStorage).findAllCampuses();
        verify(authorStorage).findAllAuthors();
    }

    @Test
    public void shouldReceiveOKFromColumbusCampusEndpoint() throws Exception {
        when(campusStorage.findCampusByName("Columbus")).thenReturn(testCampus);
        mockMvc.perform(get("/campuses/Columbus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("campus", "authors"))
                .andExpect(view().name("campus-template"));
        verify(campusStorage).findCampusByName("Columbus");
        verify(authorStorage).findAllAuthors();
    }

    @Test
    public void shouldReceiveOKFromHeadFirstJavaBookEndpoint() throws Exception {
        when(bookStorage.findBookByTitle("Head First Java")).thenReturn(new Book("Head First Java", "ISBN","A book on Java",testCampus));
        mockMvc.perform(get("/books/Head First Java"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bookToDisplay"))
                .andExpect(view().name("book-template"));
        verify(bookStorage).findBookByTitle("Head First Java");
    }

    @Test
    public void shouldReceiveOKFromKathySierraAuthorEndpoint() throws Exception {
        when(authorStorage.findAuthorById(3L)).thenReturn(new Author("Kathy", "Sierra"));
        mockMvc.perform(get("/authors/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("author-template"));
        verify(authorStorage).findAuthorById(3L);

    }

    @Test
    public void shouldReceive3xxFromAddAuthorEndpoint() throws Exception {
        mockMvc.perform(post("/authors/add")
                .param("firstName", "Testy")
                .param("lastName", "Tester"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(authorStorage).save(new Author("Testy", "Tester"));
    }

    @Test
    public void shouldReceive3xxFromAddCampusEndpoint() throws Exception {
        mockMvc.perform(post("/campuses/add")
                .param("name", "Test Town")
                .param("description", "Town for testing."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(campusStorage).addCampus(new Campus("Test Town", "Town for testing."));
    }
}
