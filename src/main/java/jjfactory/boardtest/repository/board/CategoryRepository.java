package jjfactory.boardtest.repository.board;

import jjfactory.boardtest.domain.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
