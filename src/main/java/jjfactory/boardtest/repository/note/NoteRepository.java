package jjfactory.boardtest.repository.note;

import jjfactory.boardtest.domain.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
