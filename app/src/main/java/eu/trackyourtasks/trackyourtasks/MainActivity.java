package eu.trackyourtasks.trackyourtasks;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.ArrayList;

// @Mateusz Ostapko
public class MainActivity extends AppCompatActivity {
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    Button btnNotes;
    Button btnTodos;
    Button btnProjectList;
    Button btnTimer;
    Button btnAppInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtons();
    }

    public void setUpButtons() {
        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotesList.class));
            }
        });

        btnTodos = (Button) findViewById(R.id.btnTodos);
        btnTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, To_Do_list.class));
            }
        });

        btnProjectList = (Button) findViewById(R.id.btnProjects);
        btnProjectList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProjectsList.class));
            }
        });

        btnTimer = (Button) findViewById(R.id.btnTimer);
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TimerActivity.class));
            }
        });

        btnAppInfo = (Button) findViewById(R.id.btnAppInfo);
        btnAppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, info.class));
            }
        });
    }
}