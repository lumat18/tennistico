package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.WebAttributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    MockMvc mockMvc;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    WebApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    void shouldSetUserNotActiveMessageIfSecurityThrowsDisabledException() throws Exception {
        DisabledException disabledException = new DisabledException("");

        mockMvc.perform(MockMvcRequestBuilders.get("/login-error")
                .sessionAttr(WebAttributes.AUTHENTICATION_EXCEPTION, disabledException))
                .andExpect(model().attribute("login-error-message", LoginController.USER_NOT_ACTIVE_MESSAGE))
                .andExpect(view().name("login"));
    }

}