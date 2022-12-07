package lt.codeacademy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //TODO 01 - Annotation @AutoConfigureMockMvc
class AppTests {

	@Autowired  //TODO 02 - @Autowired Mock
	private MockMvc mockMvc;
	
	@Test //TODO 04 - Write test when unauthorized
	void indexWhenUnAuthenticatedThenRedirect() throws Exception {
		// @formatter:off
		this.mockMvc.perform(get("/"))
				.andExpect(status().isUnauthorized());
		// @formatter:on
	}
	
	@Test
	@WithMockUser //TODO 03 - Write test when authorized
	void indexWhenAuthenticatedThenOk() throws Exception {
		// @formatter:off
		this.mockMvc.perform(get("/users/"))
				.andExpect(status().isOk());
		// @formatter:on
	}

}
