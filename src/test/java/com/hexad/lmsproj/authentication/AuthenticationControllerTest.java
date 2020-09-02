package com.hexad.lmsproj.authentication;

import com.google.gson.Gson;
import com.hexad.lmsproj.DAO.UserCredentialsDAO;
import com.hexad.lmsproj.boot.LmsApplication;
import com.hexad.lmsproj.datamodels.dto.UserDetailsDto;
import com.hexad.lmsproj.datamodels.entity.UserCredentials;
import com.hexad.lmsproj.datamodels.vo.UserLoginVO;
import com.hexad.lmsproj.services.AuthenticationServices;
import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LmsApplication.class)
public class AuthenticationControllerTest extends TestCase {

    public MockMvc mockMvc;
    @Autowired
    public WebApplicationContext context;

    @Mock
    AuthenticationServices authenticationServices;


    @Rule
    public ExpectedException thrown = ExpectedException.none();



    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testUserLogin() throws Exception {
        Gson gson = new Gson();
        UserLoginVO userLoginVO = mock(UserLoginVO.class);
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserName("Librarian");
        userDetailsDto.setPassword("Password123");
        String json = gson.toJson(userDetailsDto);
        Mockito.when(authenticationServices.userLogin(userDetailsDto.getUserName(),userDetailsDto.getPassword())).thenReturn(userLoginVO);
        Mockito.when(userLoginVO.getStatus()).thenReturn("Success");
        mockMvc.perform(post("/request/login").contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUserLoginFailed() throws Exception {
        Gson gson = new Gson();
        UserLoginVO userLoginVO = mock(UserLoginVO.class);
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserName("Librarian");
        userDetailsDto.setPassword("Password");
        String json = gson.toJson(userDetailsDto);
        Mockito.when(authenticationServices.userLogin(userDetailsDto.getUserName(),userDetailsDto.getPassword())).thenReturn(userLoginVO);
        Mockito.when(userLoginVO.getStatus()).thenReturn("Failed");
        mockMvc.perform(post("/request/login").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUserLogout() throws Exception {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId",1);
        Mockito.when(authenticationServices.userLogout(1L)).thenReturn(anyString());
        mockMvc.perform(post("/request/logout").contentType(MediaType.APPLICATION_JSON).content(jsonObj.toString()))
                .andExpect(status().is2xxSuccessful());
    }
}