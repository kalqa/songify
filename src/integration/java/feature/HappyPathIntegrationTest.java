package feature;

import com.songify.SongifyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = SongifyApplication.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class HappyPathIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    public MockMvc mockMvc;

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    }

    @Test
    public void f() throws Exception {
//  1. when I go to /songs then I can see no songs
        mockMvc.perform(get("/songs")
                .contentType(MediaType.APPLICATION_JSON)
        );
//  2. when I post to /song with Song "Till i collapse" then Song "Til i collapse" is returned with id 1
//  3. when I post to /song with Song "Lose Yourself" then Song "Lose Yourself" is returned with id 2
//  4. when I go to /genre then I can see only default genre with id 1
//  5. when I post to /genre with Genre "Rap" then Genre "Rap" is returned with id 2
//  6. when I go to /song/1 then I can see default genre with id 1 and name default
//  7. when I put to /song/1/genre/1 then Genre with id 2 ("Rap") is added to Song with id 1 ("Til i collapse")
//  8. when I go to /song/1 then I can see "Rap" genre
//  9. when I go to /albums then I can see no albums
//  10. when I post to /albums with Album "EminemAlbum1" and Song with id 1 then Album "EminemAlbum1" is returned with id 1
//  11. when I go to /albums/1 then I can not see any albums because there is no artist in system
//  12. when I post to /artists with Artist "Eminem" then Artist "Eminem" is returned with id 1
//  13. when I put to /artists/1/albums/2 then Artist with id 1 ("Eminem") is added to Album with id 1 ("EminemAlbum1")
//  14. when I go to /albums/1 then I can see album with single song with id 1 and single artist with id 1
//  15. when I put to /albums/1/songs/2 then Song with id 2 ("Lose Yourself") is added to Album with id 1 ("EminemAlbum1")
//  16. when I go to /albums/1 then I can see album with 2 songs (id1 and id2)

    }

}
