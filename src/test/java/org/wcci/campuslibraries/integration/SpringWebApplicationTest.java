package org.wcci.campuslibraries.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.wcci.campuslibraries.AuthorRepository;
import org.wcci.campuslibraries.BookStorage;
import org.wcci.campuslibraries.Campus;
import org.wcci.campuslibraries.CampusStorage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringWebApplicationTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReceiveOKFromHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")));
    }

    @Test
    public void shouldReceiveOKFromColumbusCampusEndpoint() throws Exception {
        mockMvc.perform(get("/campuses/Columbus"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")));
    }

    @Test
    public void shouldReceiveOKFromHeadFirstJavaBookEndpoint() throws Exception {
        mockMvc.perform(get("/books/Head First Java"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")));

    }

    @Test
    public void shouldReceiveOKFromKathySierraAuthorEndpoint() throws Exception {
        mockMvc.perform(get("/authors/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")));

    }

    @Test
    public void shouldReceive3xxFromAddAuthorEndpoint() throws Exception {
        mockMvc.perform(post("/authors/add")
                .param("firstName", "Testy")
                .param("lastName", "Tester"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldReceive3xxFromAddCampusEndpoint() throws Exception {
        mockMvc.perform(post("/campuses/add")
                .param("name", "Test Town")
                .param("description", "Town for testing."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}