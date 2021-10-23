package br.com.nextstep.Fenestra;

import br.com.nextstep.Fenestra.controller.api.ApiUserController;
import br.com.nextstep.Fenestra.model.Componente;
import br.com.nextstep.Fenestra.model.Log;
import br.com.nextstep.Fenestra.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

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

	@Test
	void createUser() throws Exception{
		//Sempre mudar o email a cada teste (Pois ele é Unique Key)
		//Criar usuário
		User user = new User("nome", "email5@hotmail.com", "Senha123");

		//POST
		MvcResult result = mockMvc.perform(post("/api/user")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)))
		.andExpect(status().isCreated()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(user.getName(), "nome");
		//Sempre mudar o email a cada teste
		Assertions.assertEquals(user.getEmail(), "email5@hotmail.com");
		Assertions.assertEquals(user.getPassword(), "Senha123");

		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}

	@Test
	void getAllUsers() throws Exception{
		//GET All
		MvcResult result = mockMvc.perform(get("/api/user")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");
	}

	@Test
	void deleteUser() throws Exception{

		//Este Objeto deve estar criado no BD
		User user = new User(44L);

		//DELETE
		MvcResult result = mockMvc.perform(delete("/api/user/44")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		System.out.println("\n\nUsuário deletado");

	}

	@Test
	void getAllComponentes() throws Exception{
		//GET All
		MvcResult result = mockMvc.perform(get("/api/componente")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}

	@Test
	void deleteComponente() throws Exception{
		//Este Objeto deve estar criado no BD
		User user = new User(18L);

		//DELETE
		MvcResult result = mockMvc.perform(delete("/api/componente/18")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		System.out.println("\n\nComponente deletado");
	}

	@Test
	void getAllLogs() throws Exception{
		//GET All
		MvcResult result = mockMvc.perform(get("/api/log")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}

	@Test
	void deleteLog() throws Exception{
		//Este Objeto deve estar criado no BD
		Log log = new Log(8L);

		//DELETE
		MvcResult result = mockMvc.perform(delete("/api/log/8")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(log)))
				.andExpect(status().isOk()).andReturn();

		System.out.println("\n\nLog deletado");
	}
}
