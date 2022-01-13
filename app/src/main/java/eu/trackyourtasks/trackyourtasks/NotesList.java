package eu.trackyourtasks.trackyourtasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

// @Mohammed Sabar
public class NotesList extends AppCompatActivity {
    NotesListViewAdapter adapter;

    private FirebaseFirestore mDatabase;
    private CollectionReference notesCollectionRef;
    private final String KEY_TITLE = "noteTitle";
    private final String KEY_CONTENT = "noteContent";
    private String KEY_CREATED_AT = "noteCreatedAt";
    private ArrayList<String> notesTitles = new ArrayList<>();
    private ArrayList<String> notesContents = new ArrayList<>();
    private ArrayList<String> notesIds = new ArrayList<>();
    Button addNewNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        mDatabase = FirebaseFirestore.getInstance();
        notesCollectionRef = mDatabase.collection("notes");

        addNewNoteBtn = (Button) findViewById(R.id.newNoteButton);
        addNewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotesList.this, NoteEditorActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!notesTitles.isEmpty()) {
            notesTitles = new ArrayList<>();
        }
        if (!notesContents.isEmpty()) {
            notesContents = new ArrayList<>();
        }
        if (!notesIds.isEmpty()) {
            notesIds = new ArrayList<>();
        }
        getNotesFromDatabase();
    }

    private void getNotesFromDatabase() {
        notesCollectionRef.orderBy(KEY_CREATED_AT, Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    notesIds.add(documentSnapshot.getId().toString());
                    notesTitles.add(documentSnapshot.getData().get(KEY_TITLE).toString());
                    notesContents.add(documentSnapshot.getData().get(KEY_CONTENT).toString());
                }
                setUpRecyclerView();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.notesListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesListViewAdapter(this, notesTitles);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new NotesListViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(NotesList.this, NoteEditorActivity.class);
                i.putExtra("noteTitle", notesTitles.get(position));
                i.putExtra("noteContent", notesContents.get(position));
                i.putExtra("noteId", notesIds.get(position));
                startActivity(i);
            }
        });
    }
}