package jjfactory.boardtest.business.controller.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.comment.Comment;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.comment.CommentDto;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.repository.comment.CommentRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class CommentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    void clean(){
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("댓글 1개 조회")
    void getComment() throws Exception {
        Comment comment = Comment.builder().content("댓글1").build();
        commentRepository.save(comment);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/{id}",comment.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").value("댓글1"))
                .andDo(print());

    }

    @Test
    @DisplayName("댓글 작성 테스트")
    void createComment() throws Exception {
        //when
        User user = User.builder().name("이재형").username("wogud222").build();
        userRepository.save(user);

        Board board = Board.builder().title("제목1").content("게시글1").build();
        boardRepository.save(board);

        CommentDto dto = new CommentDto("내용입니다", board.getId());

        Comment comment = Comment.create(dto, user, board);
        String stringComment = objectMapper.writeValueAsString(comment);


        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringComment))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").value("댓글1"))
                .andDo(print());
    }

    @Test
    void deleteComment() {
    }

    @Test
    void changeContent() {
    }

    @Test
    void likeBoard() {
    }

    @Test
    void dislikeBoard() {
    }
}