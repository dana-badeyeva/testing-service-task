package com.playtika.automation.school.test.framework.actions;

import com.playtika.automation.school.test.framework.clients.ServiceFeignClient;
import com.playtika.automation.school.test.framework.pojo.nested.Note;
import com.playtika.automation.school.test.framework.pojo.requests.CreateNoteRequest;
import com.playtika.automation.school.test.framework.pojo.requests.UpdateNoteRequest;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ServiceActions {
    
    private final ServiceFeignClient serviceFeignClient;

    public Note createNote(String authToken, String content) {
        CreateNoteRequest request = CreateNoteRequest.builder()
                .content(content)
                .build();
        return serviceFeignClient.createNote(authToken, request);
    }

    public Note getNote(String authToken, Integer noteId) {
        return serviceFeignClient.getNote(authToken, noteId);
    }

    public Note updateNote(String authToken, Integer noteId, String content, Integer version) {
        UpdateNoteRequest request = UpdateNoteRequest.builder()
                .content(content)
                .version(version)
                .build();
        return serviceFeignClient.updateNote(authToken, noteId, request);
    }

    public List<Note> getAllNotes(String authToken) {
        return serviceFeignClient.getAllNotes(authToken);
    }

    public void deleteNote(String authToken, Integer noteId) {
        serviceFeignClient.deleteNote(authToken, noteId);
    }
}