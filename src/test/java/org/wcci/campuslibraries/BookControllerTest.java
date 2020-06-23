package org.wcci.campuslibraries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.wcci.campuslibraries.controllers.BookController;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.entities.Book;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.AuthorStorage;
import org.wcci.campuslibraries.storage.BookStorage;
import org.wcci.campuslibraries.storage.CampusStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    private CampusStorage mockCampusStorage;
    private BookStorage mockBookStorage;
    private BookController underTest;
    private Campus testCampus;
    private AuthorStorage mockAuthorStorage;
    private Book testBook;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        mockCampusStorage = mock(CampusStorage.class);
        mockBookStorage = mock(BookStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        underTest = new BookController(mockBookStorage, mockCampusStorage, mockAuthorStorage);

        testCampus = new Campus("Test Town", "Testing the night away.");
        testAuthor = new Author("Testy", "McTesterson");
        testBook = new Book("Test Book", "ISBN", "Description", testCampus, testAuthor);
    }

    @Test
    public void addBookShouldRedirectToCorrectCampus(){
        String redirect = underTest.addBook("Test Book", "ISBN", "Description", testCampus.getName(), 1L);
        assertThat(redirect).isEqualTo("redirect:/campuses/"+ testCampus.getName());
    }
    @Test
    public void addBookShouldAddNewBookToBookStorage(){
        when(mockCampusStorage.findCampusByName(testCampus.getName())).thenReturn(testCampus);
        when(mockAuthorStorage.findAuthorById(1L)).thenReturn(testAuthor);
        underTest.addBook("Test Book", "ISBN", "Description", testCampus.getName(), 1L);

        verify(mockBookStorage).save(testBook);
    }
    @Test
    public void addBookShouldBeMappedCorrectly() throws Exception {
        when(mockCampusStorage.findCampusByName(testCampus.getName())).thenReturn(testCampus);
        when(mockAuthorStorage.findAuthorById(1L)).thenReturn(testAuthor);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/books/add")
                .param("title", testBook.getTitle())
                .param("isbn", testBook.getIsbn())
                .param("description", testBook.getDescription())
                .param("campusName", testCampus.getName())
                .param("authorIds", "1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        verify(mockBookStorage).save(testBook);
    }
}
