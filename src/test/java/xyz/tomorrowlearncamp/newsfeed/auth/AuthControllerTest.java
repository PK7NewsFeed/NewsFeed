package xyz.tomorrowlearncamp.newsfeed.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserRequestDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserRequestDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    // 테스트 시작하기 전에 1번 수행
    // 회원가입
    @BeforeAll
    public void setup() throws Exception {
        // SignUpUserRequestDto 생성
        SignUpUserRequestDto requestDto = new SignUpUserRequestDto();
        ReflectionTestUtils.setField(requestDto, "email", "test@test.com");
        ReflectionTestUtils.setField(requestDto, "password", "TestPassword123@");
        ReflectionTestUtils.setField(requestDto, "username", "tester");

        // object -> json
        String userJson = objectMapper.writeValueAsString(requestDto);

        // API 테스트
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        // login dto 생성
        LoginUserRequestDto loginDto = new LoginUserRequestDto();
        ReflectionTestUtils.setField(loginDto, "email", "test@test.com");
        ReflectionTestUtils.setField(loginDto, "password", "TestPassword123@");

        // object -> json
        String userJson = objectMapper.writeValueAsString(loginDto);

        // API 테스트
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                ).andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    public void testLoginFailure1() throws Exception {
        // login dto 생성
        LoginUserRequestDto loginDto = new LoginUserRequestDto();
        ReflectionTestUtils.setField(loginDto, "email", "test@test.com");
        ReflectionTestUtils.setField(loginDto, "password", "wrongPassword");

        // object -> json
        String userJson = objectMapper.writeValueAsString(loginDto);

        // API 테스트
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().is4xxClientError())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    public void testLogout() throws Exception {

        // 로그인
        LoginUserRequestDto loginDto = new LoginUserRequestDto();
        ReflectionTestUtils.setField(loginDto, "email", "test@test.com");
        ReflectionTestUtils.setField(loginDto, "password", "TestPassword123@");

        // object -> json
        String userJson = objectMapper.writeValueAsString(loginDto);

        // 로그인 API 먼저
        MockHttpSession session = (MockHttpSession) mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andReturn().getRequest().getSession();

        mockMvc.perform(post("/auth/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

}
