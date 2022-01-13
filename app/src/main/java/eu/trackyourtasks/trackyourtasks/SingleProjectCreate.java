package eu.trackyourtasks.trackyourtasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
// @Mateusz Ostapko

public class SingleProjectCreate extends AppCompatActivity {
    private Button addSingleProjectBtn;
    private EditText projectTitleInput;
    private EditText projectTimeInput;

    private FirebaseFirestore mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project_create);
        mDatabase = FirebaseFirestore.getInstance();
//
        addSingleProjectBtn = (Button) findViewById(R.id.btnAddSingleProject);
        projectTitleInput = (EditText) findViewById(R.id.inputProjectTitle);
        projectTimeInput = (EditText) findViewById(R.id.inputProjectTime);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        addSingleProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeProjectDataToDatabase(projectTitleInput.getText().toString(), projectTimeInput.getText().toString());
                finish();
            }
        });
    }

    public void writeProjectDataToDatabase(String title, String time) {
        String KEY_TITLE = "projectTitle";
        String KEY_TIME = "projectTime";
        String KEY_CREATED_AT = "projectCreatedAt";

        Map<String, Object> project = new HashMap<>();
        project.put(KEY_TITLE, title);
        project.put(KEY_TIME, time);
        project.put(KEY_CREATED_AT, System.currentTimeMillis());

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