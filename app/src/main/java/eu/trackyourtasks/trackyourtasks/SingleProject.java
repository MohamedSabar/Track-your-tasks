package eu.trackyourtasks.trackyourtasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class SingleProject extends AppCompatActivity {
    private String projectTitle;
    private String projectTime;
    private String projectId;

    EditText projectTitleTxt;
    EditText projectTimeTxt;

    Button removeProjectBtn;
    Button cancelEditProjectBtn;
    Button saveChangesProjectBtn;

    private FirebaseFirestore mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project);
        mDatabase = FirebaseFirestore.getInstance();

        projectTitleTxt = (EditText) findViewById(R.id.changeProjectTitleInput);
        projectTimeTxt = (EditText) findViewById(R.id.changeProjectTimeInput);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            projectTitle = extras.getString("projectTitle");
            projectTime = extras.getString("projectTime");
            projectId = extras.getString("projectId");

            projectTitleTxt.setText(projectTitle);
            projectTimeTxt.setText(projectTime);
        }

        removeProjectBtn = (Button) findViewById(R.id.btnRemoveProject);
        removeProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProject();
            }
        });

        cancelEditProjectBtn = (Button) findViewById(R.id.btnCancelEdit);
        cancelEditProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveChangesProjectBtn = (Button) findViewById(R.id.btnSaveProjectChanges);
        saveChangesProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProjectChanges();
            }
        });
    }

    private void removeProject() {
        mDatabase.collection("projects").document(projectId).delete();
        finish();
    }

    private void saveProjectChanges() {
        mDatabase.collection("projects").document(projectId).update("projectTitle", projectTitleTxt.getText().toString());
        mDatabase.collection("projects").document(projectId).update("projectTime", projectTimeTxt.getText().toString());
        mDatabase.collection("projects").document(projectId).update("projectCreatedAt", System.currentTimeMillis());
        finish();
    }
}