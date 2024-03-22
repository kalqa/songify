package com.songify.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SongifyApplication.class)
@ActiveProfiles("integration")
@AutoConfigureMockMvc
@Testcontainers
class HappyPathIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    public ObjectMapper objectMapper;

    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        postgreSQLContainer.getJdbcUrl();
    }

    @Test
    public void happyPath() throws Exception {
        //1. when I go to /song then I can see no songs
        // given
        // when
        ResultActions songsAction = mockMvc.perform(get("/songs")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult getSongsActionResult = songsAction.andExpect(status().isOk()).andReturn();
//        String songsResponse = getSongsActionResult.getResponse().getContentAsString();
//        GetAllSongsResponseDto response = objectMapper.readValue(songsResponse, GetAllSongsResponseDto.class);
//        assertThat(response.songs()).isEmpty();
        songsAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.songs", empty()));

        //2. when I post to /song with Song "Till i collapse" then Song "Til i collapse" is returned with id 1
        // given
        // when
        ResultActions postSongAction = mockMvc.perform(post("/songs")
                .content("""
                        {
                          "name": "Till i collapse",
                          "releaseDate": "2024-03-15T13:55:21.850Z",
                          "duration": 0,
                          "language": "ENGLISH"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult postSongActionResult = postSongAction.andExpect(status().isOk()).andReturn();
//        String postSongActionAsString = postSongActionResult.getResponse().getContentAsString();
//        CreateSongResponseDto createSongResponse = objectMapper.readValue(postSongActionAsString, CreateSongResponseDto.class);
//        assertAll(
//                () -> assertThat(createSongResponse.song().id()).isEqualTo(1),
//                () -> assertThat(createSongResponse.song().name()).isEqualTo("Till i collapse"),
//                () -> assertThat(createSongResponse.song().genre().id()).isEqualTo(1),
//                () -> assertThat(createSongResponse.song().genre().name()).isEqualTo("default")
//        );
        postSongAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.id", is(1)))
                .andExpect(jsonPath("$.song.name", is("Till i collapse")))
                .andExpect(jsonPath("$.song.genre.id", is(1)))
                .andExpect(jsonPath("$.song.genre.name", is("default")))
        ;

        //3. when I post to /song with Song "Lose Yourself" then Song "Lose Yourself" is returned with id 2
        // given
        // when
        ResultActions postSecondSongAction = mockMvc.perform(post("/songs")
                .content("""
                        {
                          "name": "Lose Yourself",
                          "releaseDate": "2024-03-15T13:55:21.850Z",
                          "duration": 0,
                          "language": "ENGLISH"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult postSongActionResult2 = postSecondSongAction.andExpect(status().isOk()).andReturn();
//        String postSongActionAsString2 = postSongActionResult2.getResponse().getContentAsString();
//        CreateSongResponseDto createSongResponse2 = objectMapper.readValue(postSongActionAsString2, CreateSongResponseDto.class);
//        assertAll(
//                () -> assertThat(createSongResponse2.song().id()).isEqualTo(2),
//                () -> assertThat(createSongResponse2.song().name()).isEqualTo("Lose Yourself"),
//                () -> assertThat(createSongResponse2.song().genre().id()).isEqualTo(1),
//                () -> assertThat(createSongResponse2.song().genre().name()).isEqualTo("default")
//        );
        postSecondSongAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.id", is(2)))
                .andExpect(jsonPath("$.song.name", is("Lose Yourself")))
                .andExpect(jsonPath("$.song.genre.id", is(1)))
                .andExpect(jsonPath("$.song.genre.name", is("default")))
        ;

        //4. when I go to /genre then I can see only default genre with id 1 TODO CHANGED! CHANGE IN REQUERIMTENDS.TXT
        // when
        ResultActions genresAction = mockMvc.perform(get("/genres")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult getGenresActionResult = genresAction.andExpect(status().isOk()).andReturn();
//        String genresResponse = getGenresActionResult.getResponse().getContentAsString();
//        GetAllGenresResponseDto response2 = objectMapper.readValue(genresResponse, GetAllGenresResponseDto.class);
//        assertThat(response2.genres()).contains(new GenreDto(
//                1L,
//                "default"
//        ));
        genresAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genres[0].id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("default")))
        ;

        //5. when I post to /genre with Genre "Rap" then Genre "Rap" is returned with id 2
        // when
        ResultActions postGenreAction = mockMvc.perform(post("/genres")
                .content("""
                        {
                          "name": "Rap"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult postGenreActionResult = postGenreAction.andExpect(status().isOk()).andReturn();
//        String postGenreActionResultAsString = postGenreActionResult.getResponse().getContentAsString();
//        GenreDto createGenreResponse = objectMapper.readValue(postGenreActionResultAsString, GenreDto.class);
//        assertAll(
//                () -> assertThat(createGenreResponse.id()).isEqualTo(2L),
//                () -> assertThat(createGenreResponse.name()).isEqualTo("Rap")
//        );
        postGenreAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Rap")))
        ;

        //6. when I go to /song/1 then I can see default genre with id 1 and name default
        // when
        ResultActions songsByIdAction = mockMvc.perform(get("/songs/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult songsByIdActionResult = songsByIdAction.andExpect(status().isOk()).andReturn();
//        String songsByIdActionResultAsString = songsByIdActionResult.getResponse().getContentAsString();
//        GetSongResponseDto response3 = objectMapper.readValue(songsByIdActionResultAsString, GetSongResponseDto.class);
//        assertAll(
//                () -> assertThat(response3.song().genre().id()).isEqualTo(1),
//                () -> assertThat(response3.song().genre().name()).isEqualTo("default")
//        );
        songsByIdAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.genre.id", is(1)))
                .andExpect(jsonPath("$.song.genre.name", is("default")))
        ;

        //7. when I put to /song/1/genre/1 then Genre with id 2 ("Rap") is added to Song with id 1 ("Til i collapse")
        // when
        ResultActions putGenreAction = mockMvc.perform(put("/songs/1/genres/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult putGenreActionResult = putGenreAction.andExpect(status().isOk()).andReturn();
//        String putGenreActionResultAsString = putGenreActionResult.getResponse().getContentAsString();
//        assertThat(putGenreActionResultAsString).contains("updated");

        putGenreAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("updated")))
        ;

        //8. when I go to /song/1 then I can see "Rap" genre
        // when
        ResultActions songsByIdAction2 = mockMvc.perform(get("/songs/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult songsByIdActionResult2 = songsByIdAction2.andExpect(status().isOk()).andReturn();
//        String songsByIdActionResultAsString2 = songsByIdActionResult2.getResponse().getContentAsString();
//        GetSongResponseDto response4 = objectMapper.readValue(songsByIdActionResultAsString2, GetSongResponseDto.class);
//        assertAll(
//                () -> assertThat(response4.song().genre().id()).isEqualTo(2),
//                () -> assertThat(response4.song().genre().name()).isEqualTo("Rap")
//        );

        songsByIdAction2
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.song.genre.id", is(2)))
                .andExpect(jsonPath("$.song.genre.name", is("Rap")))
        ;

        //9. when I go to /albums then I can see no albums
        // given
        // when
        ResultActions getAlbumsAction = mockMvc.perform(get("/albums")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult getAlbumsActionResult = getAlbumsAction.andExpect(status().isOk()).andReturn();
//        String getSongsActionResultAsString = getAlbumsActionResult.getResponse().getContentAsString();
//        GetAllAlbumsResponseDto allAlbumsResponse = objectMapper.readValue(getSongsActionResultAsString, GetAllAlbumsResponseDto.class);
//        assertThat(allAlbumsResponse.albums()).isEmpty();
        getAlbumsAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.albums", empty()));

        //10. when I post to /albums with Album "EminemAlbum1" and Song with id 1 then Album "EminemAlbum1" is returned with id 1
        // when
        ResultActions postAlbumAction = mockMvc.perform(post("/albums")
                .content("""
                        {
                          "title": "EminemAlbum1",
                          "releaseDate": "2024-03-15T13:55:21.850Z",
                          "songIds": [1]
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult postAlbumActionResult = postAlbumAction.andExpect(status().isOk()).andReturn();
//        String postAlbumActionResultAsString = postAlbumActionResult.getResponse().getContentAsString();
//        AlbumDto createAlbumResponse = objectMapper.readValue(postAlbumActionResultAsString, AlbumDto.class);
//        assertAll(
//                () -> assertThat(createAlbumResponse.id()).isEqualTo(1L),
//                () -> assertThat(createAlbumResponse.name()).isEqualTo("EminemAlbum1"),
//                () -> assertThat(createAlbumResponse.songsIds()).containsExactly(1L)
//        );

        postAlbumAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("EminemAlbum1")))
                .andExpect(jsonPath("$.songsIds", containsInAnyOrder(1)))
        ;

        //11. when I go to /albums/1 then I can not see any albums because there is no artist in system
        // when
        ResultActions getAlbumByIdAction = mockMvc.perform(get("/albums/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult getAlbumByIdActionResult = getAlbumByIdAction.andExpect(status().isNotFound()).andReturn();
//        String getAlbumByIdActionResultAsString = getAlbumByIdActionResult.getResponse().getContentAsString();
//        ErrorAlbumResponseDto errorAlbumResponseDto = objectMapper.readValue(getAlbumByIdActionResultAsString, ErrorAlbumResponseDto.class);
//        assertAll(
//                () -> assertThat(errorAlbumResponseDto.message()).contains("Album with id: 1 not found"),
//                () -> assertThat(errorAlbumResponseDto.status()).isEqualTo(HttpStatus.NOT_FOUND)
//        );

        getAlbumByIdAction
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Album with id: 1 not found")))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")))
        ;

        //12. when I post to /artists with Artist "Eminem" then Artist "Eminem" is returned with id 1
        ResultActions postArtistAction = mockMvc.perform(post("/artists")
                .content("""
                        {
                          "name": "Eminem"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult postArtistActionResult = postArtistAction.andExpect(status().isOk()).andReturn();
//        String postArtistActionResultAsString = postArtistActionResult.getResponse().getContentAsString();
//        ArtistDto createArtistResponseDto = objectMapper.readValue(postArtistActionResultAsString, ArtistDto.class);
//        assertAll(
//                () -> assertThat(createArtistResponseDto.id()).isEqualTo(1L),
//                () -> assertThat(createArtistResponseDto.name()).isEqualTo("Eminem")
//        );

        postArtistAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Eminem")))
        ;

        //13. when I put to /artists/1/albums/2 then Artist with id 1 ("Eminem") is added to Album with id 1 ("EminemAlbum1")
        ResultActions putArtistToAlbumAction = mockMvc.perform(put("/artists/1/albums/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        );
        // then
//        MvcResult putArtistToAlbumActionResult = putArtistToAlbumAction.andExpect(status().isOk()).andReturn();
//        String putArtistToAlbumActionResultAsString = putArtistToAlbumActionResult.getResponse().getContentAsString();
//        assertThat(putArtistToAlbumActionResultAsString).isEqualTo("probably assigned artist to album");

        putArtistToAlbumAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("probably assigned artist to album")))
        ;

        //14. when I go to /albums/1 then I can see album with single song with id 1 and single artist with id 1
        // when && then
        mockMvc.perform(get("/albums/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.songs[*].id", containsInAnyOrder(1)))
                .andExpect(jsonPath("$.artists[*].id", containsInAnyOrder(1)));
        // TODO w kursie pokazac dwie opcje jsonPath i objectMapper (ja wole jsonPath ale jest trudniejszy na start)

        //15. when I put to /albums/1/songs/2 then Song with id 2 ("Lose Yourself") is added to Album with id 1 ("EminemAlbum1")
        // when && then
        mockMvc.perform(put("/albums/1/songs/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("EminemAlbum1")))
                .andExpect(jsonPath("$.songsIds[*]", containsInAnyOrder(1, 2)));
        // then
//        MvcResult putSongToAlbumAssignActionResult = putSongToAlbumAssignAction.andExpect(status().isOk()).andReturn();
//        String putSongToAlbumAssignActionResultAsString = putSongToAlbumAssignActionResult.getResponse().getContentAsString();
//        AlbumDto putSongToAlbumAssignResponse = objectMapper.readValue(putSongToAlbumAssignActionResultAsString, AlbumDto.class);
//        assertAll(
//                () -> assertThat(putSongToAlbumAssignResponse.id()).isEqualTo(1L),
//                () -> assertThat(putSongToAlbumAssignResponse.name()).isEqualTo("EminemAlbum1"),
//                () -> assertThat(putSongToAlbumAssignResponse.songsIds()).containsExactly(1L, 2L)
//        );

        //16. when I go to /albums/1 then I can see album with 2 songs (id1 and id2)
        // when && then
        mockMvc.perform(get("/albums/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.songs[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.artists[*].id", containsInAnyOrder(1)));
    }
}
