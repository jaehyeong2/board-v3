package jjfactory.boardtest.business.controller.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.board.req.BoardCreate;
import jjfactory.boardtest.business.dto.board.req.BoardUpdate;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.service.board.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class BoardApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService boardService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BoardRepository boardRepository;

    @Test
    void getBoard() {
    }

    @Test
    void getBoards() {
    }

    @Test
    void getBoardsByCategoryName() {
    }

    @Test
    void getCommentsByBoardId() {
    }

    @Test
    @DisplayName("글 생성 테스트")
    void createBoard() throws Exception {
        User user = User.builder().username("lee").build();
        BoardCreate boardCreate = BoardCreate.builder().content("content").title("title").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Y"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 수정 테스트")
    void updateBoard() throws Exception {

        Board board = Board.builder().title("title1").content("content1").build();
        boardRepository.save(board);

        BoardUpdate boardUpdate = new BoardUpdate("changedContent", "changedTitle");


        mockMvc.perform(MockMvcRequestBuilders.patch("/boards/"+board.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Y"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 삭제 테스트")
    void deleteBoard() throws Exception {
        Board board = Board.builder().title("title1").content("content1").build();
        boardRepository.save(board);


        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/"+board.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Y"))
                .andDo(print());
    }

    @Test
    void likeBoard() {
    }

    @Test
    void dislikeBoard() {
    }
}