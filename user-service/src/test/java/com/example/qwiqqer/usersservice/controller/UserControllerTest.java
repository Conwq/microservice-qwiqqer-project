package com.example.qwiqqer.usersservice.controller;

import com.example.qwiqqer.usersservice.model.dto.UserRequest;
import com.example.qwiqqer.usersservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	private final ObjectMapper mapper = new ObjectMapper();
	private final ObjectWriter writer = mapper.writer();

	@Test
	public void shouldSaveUser() throws Exception {
		UserRequest userRequest = UserRequest.builder()
				.email("tom@mail.com")
				.username("tom1213")
				.password("d11231")
				.build();

		MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(writer.writeValueAsString(userRequest));

		mockMvc.perform(content)
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status")
						.value(HttpStatus.CREATED.value()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message")
						.value("User successfully registered."));

		Mockito.verify(userService, Mockito.times(1)).saveUser(userRequest);
	}

	@Test
	public void shouldReturnBadResponse() throws Exception {
		UserRequest userRequest = UserRequest.builder()
				.email(" ")
				.username(" ")
				.password(" ")
				.build();

		MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(writer.writeValueAsString(userRequest));

		mockMvc.perform(content)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message",
						Matchers.is("Incorrect data.")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status",
						Matchers.is(HttpStatus.BAD_REQUEST.value())));

		Mockito.verify(userService, Mockito.never()).saveUser(userRequest);
	}
}