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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// @Mateusz Ostapko

public class ProjectsList extends AppCompatActivity {
    ProjectsListViewAdapter adapter;

    private FirebaseFirestore mDatabase;
    private CollectionReference projectsCollectionRef;
    private String KEY_TITLE = "projectTitle";
    private String KEY_TIME = "projectTime";
    private String KEY_CREATED_AT = "projectCreatedAt";
    private ArrayList<String> projectsTitles = new ArrayList<>();
    private ArrayList<String> projectsTimes = new ArrayList<>();
    private ArrayList<String> projectsIds = new ArrayList<>();
    Button addNewProjectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);
        mDatabase = FirebaseFirestore.getInstance();
        projectsCollectionRef = mDatabase.collection("projects");

        addNewProjectBtn = (Button) findViewById(R.id.newProjectButton);
        addNewProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProjectsList.this, SingleProjectCreate.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!projectsTitles.isEmpty()) {
            projectsTitles = new ArrayList<>();
        }
        if (!projectsTimes.isEmpty()) {
            projectsTimes = new ArrayList<>();
        }
        if (!projectsIds.isEmpty()) {
            projectsIds = new ArrayList<>();
        }
        getProjectsFromDatabase();
    }

    private void getProjectsFromDatabase() {
        projectsCollectionRef.orderBy(KEY_CREATED_AT, Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    projectsIds.add(documentSnapshot.getId().toString());
                    projectsTitles.add(documentSnapshot.getData().get(KEY_TITLE).toString());
                    projectsTimes.add(timeOrHyphen(documentSnapshot.getData().get(KEY_TIME).toString()));
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
        RecyclerView recyclerView = findViewById(R.id.projectsListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectsListViewAdapter(this, projectsTitles, projectsTimes);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new ProjectsListViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(ProjectsList.this, SingleProject.class);
                i.putExtra("projectTitle", projectsTitles.get(position));
                i.putExtra("projectTime", projectsTimes.get(position));
                i.putExtra("projectId", projectsIds.get(position));
                startActivity(i);
            }
        });
    }

    private String timeOrHyphen(String time) {
        if (time.equals("")) {
            return "-";
        } else {
            return time;
        }
    }
}