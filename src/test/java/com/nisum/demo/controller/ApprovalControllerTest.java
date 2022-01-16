package com.nisum.demo.controller;

import com.nisum.demo.dto.UserInfoDTO;
import com.nisum.demo.helper.DataExtensions;
import com.nisum.demo.helper.TestExtensions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApprovalControllerTest extends TestExtensions {

    private static String urlTemplate = "/approval";

    @Autowired
    private DataExtensions dataExtensions;

    @Test
    @Transactional
    public void userTestErrorEmailDuplicate() throws Exception {
        userTest();
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(dataExtensions.getUserDefault())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
    }

    @Test
    @Transactional
    public void userTest() throws Exception {
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(dataExtensions.getUserDefault())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.modified").isNotEmpty())
                .andExpect(jsonPath("$.last_login").isNotEmpty())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.isactive").isBoolean());
    }

    @Test
    @Transactional
    public void userTestErrorEmailNull() throws Exception {
        UserInfoDTO userInfoDTO = dataExtensions.getUserDefault();
        userInfoDTO.setEmail(null);
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(userInfoDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[email] no puede ser nulo"));
    }

    @Test
    @Transactional
    public void userTestErrorEmailRegularExpression() throws Exception {
        UserInfoDTO userInfoDTO = dataExtensions.getUserDefault();
        userInfoDTO.setEmail("jhon.doe@");
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(userInfoDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[email] mal formado"));
    }

    @Test
    @Transactional
    public void userTestErrorNameNull() throws Exception {
        UserInfoDTO userInfoDTO = dataExtensions.getUserDefault();
        userInfoDTO.setName(null);
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(userInfoDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[name] no puede ser nulo"));
    }

    @Test
    @Transactional
    public void userTestErrorPasswordNull() throws Exception {
        UserInfoDTO userInfoDTO = dataExtensions.getUserDefault();
        userInfoDTO.setPassword(null);
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(userInfoDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[password] no puede ser nulo"));
    }

    @Test
    @Transactional
    public void userTestErrorPasswordRegex() throws Exception {
        UserInfoDTO userInfoDTO = dataExtensions.getUserDefault();
        userInfoDTO.setPassword("hunter2");
        mockMvc.perform(post(urlTemplate + "/user")
                .contentType(APPLICATION_JSON_VALUE)
                .content(json(userInfoDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mensaje").value("[password] mal formado"));
    }
}
