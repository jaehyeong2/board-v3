package jjfactory.boardtest.service.note;

import jjfactory.boardtest.repository.note.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class NoteService {
    private final NoteRepository noteRepository;
}
