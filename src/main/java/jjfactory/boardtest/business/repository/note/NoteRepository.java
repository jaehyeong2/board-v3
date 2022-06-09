package jjfactory.boardtest.business.repository.note;

import jjfactory.boardtest.business.domain.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
