package com.example.trackyourtasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ProjectsList extends AppCompatActivity {
    ProjectsListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);


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
}