package com.playtika.automation.school.test.framework;

import com.playtika.automation.school.test.framework.actions.AuthActions;
import com.playtika.automation.school.test.framework.actions.ServiceActions;
import com.playtika.automation.school.test.framework.configs.AuthConfiguration;
import com.playtika.automation.school.test.framework.configs.ServiceConfiguration;
import com.playtika.automation.school.test.framework.pojo.nested.Note;
import com.playtika.automation.school.test.framework.utils.UserUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = {
        AuthConfiguration.class,
        ServiceConfiguration.class
})
class ServiceTest {

    private static final String GRANT_TYPE = "password";
    private static final String SCOPE = "read+write";

    @Autowired
    private AuthActions authActions;
    @Autowired
    private ServiceActions serviceActions;

    @Test
    void testNotesService() {
        // 1. Generate random email and password
        String email = UserUtils.getRandomEmail();
        String password = UserUtils.getRandomPassword();

        // 2. Register user
        authActions.registration(email, password);

        // 3. Authenticate and get token
        LoginResponse login = authActions.login(email, password, GRANT_TYPE, SCOPE);
        String authToken = "Bearer " + login.getAccessToken();

        // 4. Create a note with any content
        String firstContent = "My first note";
        Note firstNote = serviceActions.createNote(authToken, firstContent);

        // 5. Get list of notes and assert it has size equals to one
        assertThat(serviceActions.getAllNotes(authToken).size()).isOne();

        // 6. Create second note
        String secondContent = "My second note";
        Note secondNote = serviceActions.createNote(authToken, secondContent);

        // 7. Get list of notes and assert it has size has grown
        List<Note> response = serviceActions.getAllNotes(authToken);
        assertThat(serviceActions.getAllNotes(authToken).size()).isEqualTo(2);

        // 8. Get first note by id and assert it's the same as you've create
        assertThat(serviceActions.getNote(authToken, firstNote.getId())).isEqualToComparingFieldByField(firstNote);

        // 9. Update first note with any new content
        String updatedContent = "Any new content";
        serviceActions.updateNote(authToken, firstNote.getId(), updatedContent, firstNote.getVersion());

        // 10. Get list of notes. Use stream to filter list by id of note and get updated one
        List<Note> notes = serviceActions.getAllNotes(authToken);
        Note updatedNote = notes.stream().filter(note -> note.getId().equals(firstNote.getId())).findFirst().orElseThrow(() -> new RuntimeException("Note is not found"));

        // 11. Check that update note has the same id as first note. Check that version was incremented.
        // Check that content was update according to text from update step. Check that creation date is equal to first note creation date.
        // Check that modification date is not the same, as in first note.
        assertThat(updatedNote.getId()).isEqualTo(firstNote.getId());
        assertThat(updatedNote.getVersion()).isGreaterThan(firstNote.getVersion());
        assertThat(updatedNote.getContent()).isEqualTo(updatedContent);
        assertThat(updatedNote.getCreatedAt()).isEqualTo(firstNote.getCreatedAt());
        assertThat(updatedNote.getModifiedAt()).isNotEqualTo(firstNote.getModifiedAt());

        // 12. Delete first note
        serviceActions.deleteNote(authToken, firstNote.getId());

        // 13. Get list of notes and assert it has size equal to one and it doesn't contain updated note
        assertThat(serviceActions.getAllNotes(authToken).size()).isOne();
        assertThat(serviceActions.getAllNotes(authToken)).doesNotContain(updatedNote);

        // 14. Try to get deleted note
        // and assert that method throws error which contains message: "Note with id [{your note id}] wasn't found"
        assertThatThrownBy(() -> serviceActions.getNote(authToken, firstNote.getId())).hasMessageContaining("Note with id [" + firstNote.getId() + "] wasn't found");

        // 15. Delete second note
        serviceActions.deleteNote(authToken, secondNote.getId());
    }
}