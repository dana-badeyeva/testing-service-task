package com.playtika.automation.school.test.framework.clients;

import com.playtika.automation.school.test.framework.pojo.nested.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "notes-client",
        url = "https://taschool-notes-service.herokuapp.com",
        path = "/v1/notes"
)

public interface ServiceFeignClient {

    @PostMapping(consumes = "application/json")
    Note createNote(@RequestHeader("Authorization") String authToken,
                                  @RequestBody CreateNoteRequest request);

    @GetMapping(value = "/{noteId}")
    Note getNote(@RequestHeader("Authorization") String authToken,
                            @PathVariable("noteId") Integer noteId);

    @GetMapping()
    List<Note> getAllNotes(@RequestHeader("Authorization") String authToken);

    @PutMapping(value = "/{noteId}", consumes = "application/json")
    Note updateNote(@RequestHeader("Authorization") String authToken,
                                  @PathVariable("noteId") Integer noteId,
                                  @RequestBody UpdateNoteRequest updateNoteRequest);

    @DeleteMapping(value = "/{noteId}")
    void deleteNote(@RequestHeader("Authorization") String authToken,
                    @PathVariable("noteId") Integer noteId);
}