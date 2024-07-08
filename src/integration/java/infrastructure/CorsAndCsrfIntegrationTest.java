package infrastructure;

import com.songify.SongifyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = SongifyApplication.class)
@ActiveProfiles("integration")
@Testcontainers
class CorsAndCsrfIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    }

//    @Test
//    @WithMockUser
//    // tylko ze my mamy wylaczone csrf! http.csrf(c -> c.disable());
//    // gdyby bylo on np. dla formlogin albo basic autha to nie powinniśmy przepuszczac rq bez csrf dla mutable op.
//    // oraz sprawdzac czy dla mutable operations jest wysylany csrf
//    public void testCsrf() throws Exception {
//        mockMvc.perform(post("/genres")
////                        .with(csrf())
//                        .content("""
//                                {
//                                  "name": "Rap"
//                                }
//                                """.trim())
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                )
//                .andExpect(status().isForbidden());
//    }

    @Test
    public void testCors() throws Exception {
        mockMvc.perform(options("/songs")
                        .header("Access-Control-Request-Method", List.of("GET", "POST", "PUT", "DELETE", "PATCH"))
                        .header("Origin", "https://localhost:3000")
                )
                .andExpect(header().exists("Access-Control-Allow-Origin"))
                .andExpect(header().string("Access-Control-Allow-Origin", "https://localhost:3000"))
                .andExpect(header().exists("Access-Control-Allow-Methods"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH"))
                .andExpect(status().isOk());
    }
}
