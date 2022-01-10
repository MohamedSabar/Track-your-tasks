package eu.trackyourtasks.trackyourtasks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


// @Mohamed Sabar
public class NoteEditorActivity extends AppCompatActivity {
    private String noteContent;
    private String noteTitle;
    private String noteId;

    private FirebaseFirestore mDatabase;

    EditText noteContentTxt;
    EditText noteTitleTxt;

    Button saveNoteBtn;
    Button deleteNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        mDatabase = FirebaseFirestore.getInstance();

        noteContentTxt = (EditText) findViewById(R.id.noteContentInput);
        noteTitleTxt = (EditText) findViewById(R.id.noteTitleTxt);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteContent = extras.getString("noteContent");
            noteTitle = extras.getString("noteTitle");
            noteId = extras.getString("noteId");

            noteTitleTxt.setText(noteTitle);
            noteContentTxt.setText(noteContent);
        }

        saveNoteBtn = (Button) findViewById(R.id.btnSaveNote);
        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveNoteChanges();
                } catch (Exception e) {
                    createNewNote(noteTitleTxt.getText().toString(), noteContentTxt.getText().toString());
                }
                finally {
                    finish();
                }
            }
        });

        deleteNoteBtn = (Button) findViewById(R.id.btnDeleteNote);
        deleteNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              removeNote();
            }
        });
    }


    private void saveNoteChanges() {
            mDatabase.collection("notes").document(noteId).update("noteTitle", noteTitleTxt.getText().toString());
            mDatabase.collection("notes").document(noteId).update("noteContent", noteContentTxt.getText().toString());
            mDatabase.collection("notes").document(noteId).update("noteCreatedAt", System.currentTimeMillis());
        }

    private void createNewNote(String title, String content) {
        Map<String, Object> project = new HashMap<>();
        project.put("noteTitle", title);
        project.put("noteContent", content);
        project.put("noteCreatedAt", System.currentTimeMillis());

        mDatabase.collection("notes").document().set(project)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }


    private void removeNote() {
        mDatabase.collection("notes").document(noteId).delete();
        finish();
    }
}
