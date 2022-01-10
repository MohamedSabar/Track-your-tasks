package eu.trackyourtasks.trackyourtasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button addNewProjectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        mDatabase = FirebaseFirestore.getInstance();
        addNewProjectBtn = (Button) findViewById(R.id.newProjectButton);

        setOnClickListeners();

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

    private void setOnClickListeners() {
        addNewProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProjectsList.this, SingleProjectCreate.class));
            }
        });
    }
}