package jjfactory.boardtest.controller.note;

import jjfactory.boardtest.service.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notes")
@RequiredArgsConstructor
@RestController
public class NoteApi {
    private final NoteService noteService;
}
