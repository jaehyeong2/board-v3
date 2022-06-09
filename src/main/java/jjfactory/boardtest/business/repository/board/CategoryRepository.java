package jjfactory.boardtest.business.repository.board;

import jjfactory.boardtest.business.domain.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
