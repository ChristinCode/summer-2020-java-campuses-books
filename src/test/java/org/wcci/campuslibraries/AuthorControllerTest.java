package org.wcci.campuslibraries;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.wcci.campuslibraries.controllers.AuthorController;
import org.wcci.campuslibraries.entities.Author;
import org.wcci.campuslibraries.storage.AuthorStorage;

import static org.mockito.Mockito.*;

public class AuthorControllerTest {

    @Test
    public void shouldAskAuthorStorageForCorrectAuthorWhenGivenIdAndPassesItToTheModel(){
        //Arrangement
        AuthorStorage mockAuthorStorage = mock(AuthorStorage.class);
        Author testAuthor = new Author("Tester", "Testy");
        when(mockAuthorStorage.findAuthorById(1L)).thenReturn(testAuthor);
        AuthorController underTest = new AuthorController(mockAuthorStorage);
        Model mockModel = mock(Model.class);
        //Action
        underTest.showSingleAuthor(1L, mockModel);
        //Assertion
        verify(mockAuthorStorage).findAuthorById(1L);
        verify(mockModel).addAttribute("author", testAuthor);
    }

}
