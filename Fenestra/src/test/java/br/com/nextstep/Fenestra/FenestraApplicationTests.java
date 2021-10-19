package br.com.nextstep.Fenestra;

import br.com.nextstep.Fenestra.controller.api.ApiUserController;
import br.com.nextstep.Fenestra.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FenestraApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ApiUserController controller;


	/*
	*
	* TESTES DA CLASSE USER
	* ENDPOINT (/api/user)
	* */
	@Test
	void createUser() throws Exception{
		//Sempre mudar o email a cada teste (Pois ele é Unique Key)
		//Criar usuário
		User user = new User("nome", "email3@hotmail.com", "Senha123");

		//POST
		mockMvc.perform(post("/api/user")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)))
		.andExpect(status().isCreated());

		//Comparar resultados
		Assertions.assertEquals(user.getName(), "nome");
		//Sempre mudar o email a cada teste
		Assertions.assertEquals(user.getEmail(), "email3@hotmail.com");
		Assertions.assertEquals(user.getPassword(), "Senha123");
	}

	@Test
	void getAllUsers() throws Exception{
		//GET All
		mockMvc.perform(get("/api/user", "/api/user"));
	}

	@Test
	void getUser() throws Exception{
		User user = new User(2L, "Eduardo Vinícius", "eduardo@gmail.com", "Fiap@123");

		//GET
		mockMvc.perform(get("/api/user")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());

		//Comparar resultados
		Assertions.assertEquals(user.getId(), 2L);
		Assertions.assertEquals(user.getName(), "Eduardo Vinícius");
		Assertions.assertEquals(user.getEmail(), "eduardo@gmail.com");
		Assertions.assertEquals(user.getPassword(), "Fiap@123");
	}

	@Test
	void updateUser() throws Exception{
		//Mudando final da senha de 123 para 321
		User user = new User(42L, "nome", "email3@hotmail.com", "Senha321");

		//PUT
		mockMvc.perform(put("/api/user/42")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());

		//Comparar Resultados
		Assertions.assertEquals(user.getId(), 42L);
		Assertions.assertEquals(user.getName(), "nome");
		Assertions.assertEquals(user.getEmail(), "email3@hotmail.com");
		Assertions.assertEquals(user.getPassword(), "Senha321");

	}

	@Test
	void deleteUser() throws Exception{

		//Este Objeto deve estar criado no BD
		User user = new User(42L);

		//DELETE
		mockMvc.perform(delete("/api/user/42")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());

	}
}
