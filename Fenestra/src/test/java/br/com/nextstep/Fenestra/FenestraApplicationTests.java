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

	/*
	*
	* TESTES DA CLASSE USER
	* ENDPOINT (/api/user)
	* */
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
	void getUser() throws Exception{
		User user = new User(2L, "Eduardo Vinícius", "eduardo@gmail.com", "Fiap@123");

		//GET
		MvcResult result = mockMvc.perform(get("/api/user/2")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(user.getId(), 2L);
		Assertions.assertEquals(user.getName(), "Eduardo Vinícius");
		Assertions.assertEquals(user.getEmail(), "eduardo@gmail.com");
		Assertions.assertEquals(user.getPassword(), "Fiap@123");

		//Print do body do request
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");
	}

	@Test
	void updateUser() throws Exception{
		//Mudando final da senha de 321 para 123 de um usuário já existente
		User user = new User(41L, "nome", "email2@hotmail.com", "Senha123");

		//PUT
		MvcResult result = mockMvc.perform(put("/api/user/41")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		//Comparar Resultados
		Assertions.assertEquals(user.getId(), 41L);
		Assertions.assertEquals(user.getName(), "nome");
		Assertions.assertEquals(user.getEmail(), "email2@hotmail.com");
		Assertions.assertEquals(user.getPassword(), "Senha123");

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

	/*
	 *
	 * TESTES DA CLASSE COMPONENTE
	 * ENDPOINT (/api/componente)
	 * */

	/*
	@Test
	void createComponente() throws Exception{
		//Criar componente
		Componente componente = new Componente("Janela da Sala"); //Falta colocar o ID do Usuário que tem esse componente

		//POST
		MvcResult result = mockMvc.perform(post("/api/componente")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(componente)))
				.andExpect(status().isCreated()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(componente.getName(), "Janela da sala");

		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}
*/
	@Test
	void getAllComponentes() throws Exception{
		//GET All
		MvcResult result = mockMvc.perform(get("/api/componente")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}
/*
	@Test
	void getComponente() throws Exception{
		Componente componente = new Componente(1L, "Janela Quarto", 1L); //Falta colocar o ID do Usuário que tem esse componente

		//GET
		MvcResult result = mockMvc.perform(get("/api/componente/1")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(componente)))
				.andExpect(status().isOk()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(componente.getId(), 1L);
		Assertions.assertEquals(componente.getName(), "Janela Quarto");
		//Assertions.assertEquals(componente.getUser(), );

		//Print do body do request
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");
	}

	@Test
	void updateComponente() throws Exception{
		//Mudando o nome do componente
		Componente componente = new Componente(99999L, "Janela Quarto principal", 99999L); //Falta colocar o ID do Usuário que tem esse componente

		//PUT
		MvcResult result = mockMvc.perform(put("/api/user/999999")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(componente)))
				.andExpect(status().isOk()).andReturn();

		//Comparar Resultados
		Assertions.assertEquals(componente.getId(), 99999L);
		Assertions.assertEquals(componente.getName(), "Janela Quarto principal");
		Assertions.assertEquals(componente.getUser(), );

		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}
*/

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


	/*
	 *
	 * TESTES DA CLASSE LGO
	 * ENDPOINT (/api/log)
	 * */

	/*
@Test
void createLog() throws Exception{
    //Criar log
    Log log = new Log(); //Falta colocar o ID do Usuário que tem esse componente

    //POST
    MvcResult result = mockMvc.perform(post("/api/log")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(log)))
            .andExpect(status().isCreated()).andReturn();

    //Comparar resultados
    Assertions.assertEquals(log.getDescription(), "Fechamento Programado");

    System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

}
*/
	@Test
	void getAllLogs() throws Exception{
		//GET All
		MvcResult result = mockMvc.perform(get("/api/log")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}
/*
	@Test
	void getLog() throws Exception{
		Log log = new Log(); //Falta colocar o ID do Componente que tem esse log (Log -> ManyToOne -> Componente)

		//GET
		MvcResult result = mockMvc.perform(get("/api/log/1")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(log)))
				.andExpect(status().isOk()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(log.getId(), 1L);
		Assertions.assertEquals(log.getDateRegistro(), dd/MM/yyyy);
		Assertions.assertEquals(log.getDescription(), "Fechamento Programado");
		//Assertions.assertEquals(log.getComponente(), );

		//Print do body do request
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");
	}

	@Test
	void updateLog() throws Exception{
		//Mudando o nome do componente
		Log log = new Log(); //Falta colocar o ID do Componente que tem esse log (Log -> ManyToOne -> Componente)

		//PUT
		MvcResult result = mockMvc.perform(put("/api/log/999999")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(log)))
				.andExpect(status().isOk()).andReturn();

		//Comparar Resultados
		Assertions.assertEquals(log.getId(), 1L);
		Assertions.assertEquals(log.getDateRegistro(), dd/MM/yyyy);
		Assertions.assertEquals(log.getDescription(), "Fechamento Programado");
		//Assertions.assertEquals(log.getComponente(), );

		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}
*/

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
