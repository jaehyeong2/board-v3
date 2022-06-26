package jjfactory.boardtest.board;

import jjfactory.boardtest.business.domain.board.Board;
import jjfactory.boardtest.business.domain.board.Category;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.board.BoardDto;
import jjfactory.boardtest.business.repository.board.BoardRepository;
import jjfactory.boardtest.business.repository.board.CategoryRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.business.service.board.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardService boardService;

    User user1;
    User user2;
    Category category;

//    @BeforeAll
//    void init(){
//        user1 = User.builder().name("lee").username("test11").build();
//        user2 = User.builder().name("kim").username("test22").build();
//
//        category = Category.builder().name("공포").build();
//
//        userRepository.save(user1);
//        categoryRepository.save(category);
//    }

    @Test
    @DisplayName("게시글 작성 포인트 업 테스트")
    void point_up(){
        user1 = User.builder().name("lee").username("test11").build();
        category = Category.builder().name("공포").build();

        userRepository.save(user1);
        categoryRepository.save(category);

        User findUser = userRepository.findByUsername("test11");

        BoardDto boardDto = new BoardDto("게시글1","내용내용",category.getId());

        boardService.createBoard(boardDto,null,findUser.getId());
        Assertions.assertThat(findUser.getActivePoint()).isEqualTo(3);

    }
}
