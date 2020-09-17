package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_register_user() throws Exception {
        User user = new User("Eva", "female", 26, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_register_user_when_name_is_empty() throws Exception {
        User user = new User("", "female", 26, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_name_exceed_8_characters() throws Exception {
        User user = new User("jjjjjjjjj", "female", 26, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_gender_is_empty() throws Exception {
        User user = new User("Eva", "", 26, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_age_is_empty() throws Exception {
        User user = new User("Eva", "female", null, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_age_is_less_than_18() throws Exception {
        User user = new User("Eva", "female", 17, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_age_is_larger_than_100() throws Exception {
        User user = new User("Eva", "female", 101, "Eva@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_email_is_invalid() throws Exception {
        User user = new User("Eva", "female", 101, "@twuc.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_phone_is_empty() throws Exception {
        User user = new User("Eva", "female", 101, "@twuc.com", "");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_phone_is_not_11_digits() throws Exception {
        User user = new User("Eva", "female", 101, "@twuc.com", "123456789");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_register_user_when_phone_is_not_start_with_1() throws Exception {
        User user = new User("Eva", "female", 101, "@twuc.com", "28888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String userStr = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user/register")
                .content(userStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}