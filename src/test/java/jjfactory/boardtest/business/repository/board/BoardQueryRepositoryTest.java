package jjfactory.boardtest.business.repository.board;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.board.Category;
import jjfactory.boardtest.business.dto.board.res.BoardResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardQueryRepositoryTest {

    @Autowired
    BoardQueryRepository boardQueryRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("페이징 글 리스트 조회")
    void findAllBoards() {
        ArrayList<Board> boardList = new ArrayList<>();

        for (int i=1; i<31; i++){
            Board build = Board.builder().title("보드" + i).build();
            boardList.add(build);
        }

        boardRepository.saveAll(boardList);

        Pageable request = PageRequest.of(1, 10);
        Page<BoardResponse> responses = boardQueryRepository.findAllBoards(request);

        assertThat(responses.getSize()).isEqualTo(10);
    }

    @Test
    @DisplayName("카테고리별 게시글 찾기")
    void findBoardsByCategory() {

        //given
        Category info = Category.builder().name("정보").build();
        Category question = Category.builder().name("질문").build();
        categoryRepository.saveAll(List.of(info,question));

        ArrayList<Board> boardList = new ArrayList<>();
        for (int i=1; i<31; i++){
            if( i%2 == 0){
                Board build = Board.builder().title("보드" + i).category(info).build();
                boardList.add(build);
            }else {
                Board build = Board.builder().title("보드" + i).category(question).build();
                boardList.add(build);
            }
        }
        boardRepository.saveAll(boardList);

        Pageable page = PageRequest.of(1, 20);

        Page<BoardResponse> boards = boardQueryRepository.findBoardsByCategoryName(page,"정보");
        assertThat(boards.getSize()).isEqualTo(15);
    }
}