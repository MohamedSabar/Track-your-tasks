package eu.trackyourtasks.trackyourtasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectsList extends AppCompatActivity {
    ProjectsListViewAdapter adapter;
    private FirebaseFirestore mDatabase;
    private String KEY_TITLE = "projectTitle";
    private String KEY_TIME = "projectTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        mDatabase = FirebaseFirestore.getInstance();

        readData();

        // data to populate the RecyclerView with
        ArrayList<String> projects = new ArrayList<>();
        projects.add("Horse");
        projects.add("Cow");
        projects.add("Camel");
        projects.add("Sheep");
        projects.add("Goat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.projectsListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectsListViewAdapter(this, projects);
        recyclerView.setAdapter(adapter);
    }

    public void readData() {
        String title = "Tytul";
        Integer time = 200;

        Map<String, Object> project = new HashMap<>();
        project.put(KEY_TITLE, title);
        project.put(KEY_TIME, time);


        mDatabase.collection("projects").document().set(project)
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
}