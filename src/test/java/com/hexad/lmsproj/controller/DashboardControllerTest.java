package com.hexad.lmsproj.controller;

import com.google.gson.Gson;
import com.hexad.lmsproj.boot.LmsApplication;
import com.hexad.lmsproj.datamodels.dto.BookDto;
import com.hexad.lmsproj.datamodels.entity.Books;
import com.hexad.lmsproj.datamodels.vo.UserLoginVO;
import com.hexad.lmsproj.services.AuthenticationServices;
import com.hexad.lmsproj.services.DashboardServices;
import junit.framework.TestCase;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import java.awt.print.*;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LmsApplication.class)
public class DashboardControllerTest extends TestCase {

    public MockMvc mockMvc;
    @Autowired
    public WebApplicationContext context;

    @Mock
    DashboardServices dashboardServices;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddNewBook() throws Exception {
        BookDto bookDto = new BookDto();
        Books book = new Books();
        Gson gson = new Gson();
        bookDto.setBookId(13L);
        bookDto.setBookName("Moby Dick");
        String json = gson.toJson(bookDto);
        Mockito.when(dashboardServices.getBookByBookId(anyLong())).thenReturn(book);
        Mockito.when(dashboardServices.addNewBook(book)).thenReturn(anyString());
        mockMvc.perform(post("/dashboard/addNewBook").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetBooksList() {
    }

    @Test
    public void testGetAvailableList() {
    }

    @Test
    public void testGetBorrowedList() {
    }

    @Test
    public void testCheckoutBooks() {
    }

    @Test
    public void testRemoveBooks() {
    }
}