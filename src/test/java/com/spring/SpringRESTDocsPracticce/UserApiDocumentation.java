package com.spring.SpringRESTDocsPracticce;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApiDocumentation {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void testRead() throws Exception {
        this.mockMvc.perform(get("/api/user/{id}", 1))
                .andDo(print())
                .andDo(document("user",
                        pathParameters(
                                parameterWithName("id").description("사용자 id")
                        ),
                        responseFields(
                                fieldWithPath("resultCode").description("응답코드"),
                                fieldWithPath("data.id").description("id"),
                                fieldWithPath("data.account").description("계정"),
                                fieldWithPath("data.email").description("이메일"),
                                fieldWithPath("data.phoneNumber").description("전화번호"),
                                fieldWithPath("data.createdAt").description("생성시간"),
                                fieldWithPath("data.updatedAt").description("수정시간")
                        )
                ));

        this.mockMvc.perform(get("/api/user/{id}", 1))
                .andDo(document("user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                .description("사용자의 정보를 생성/조회/수정/삭제 합니다.")
                                .summary("사용자 정보")
                                .pathParameters(
                                        parameterWithName("id").description("사용자 id")
                                )
                                .responseFields(
                                        fieldWithPath("resultCode").description("응답코드"),
                                        fieldWithPath("data.id").description("id"),
                                        fieldWithPath("data.account").description("계정"),
                                        fieldWithPath("data.email").description("이메일"),
                                        fieldWithPath("data.phoneNumber").description("전화번호"),
                                        fieldWithPath("data.createdAt").description("생성시간"),
                                        fieldWithPath("data.updatedAt").description("수정시간")
                                )
                                .build()
                        )
                ));
    }

}
