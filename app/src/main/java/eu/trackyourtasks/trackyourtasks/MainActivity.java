package eu.trackyourtasks.trackyourtasks;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    Button btnNotesTodos;
    Button btnProjectList;
    Button btnTimer;
    Button btnAppInfo;

//    @Override
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.add_note_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        super.onOptionsItemSelected(item);
//
//        if (item.getItemId() == R.id.add_note) {
//            // Going from MainActivity to NotesEditorActivity
//            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return false;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtons();

//        ListView listView = findViewById(R.id.listView);
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
//        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);
//
//        if (set == null) {
//            notes.add("Example note");
//        } else {
//            notes = new ArrayList(set);
//        }
//
//        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, notes);
//
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                // Going from MainActivity to NotesEditorActivity
//                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
//                intent.putExtra("noteId", i);
//                startActivity(intent);
//
//            }
//        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                final int itemToDelete = i;
//                // To delete.
//                new AlertDialog.Builder(MainActivity.this)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Are you sure?")
//                        .setMessage("Do you want to delete this note?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                notes.remove(itemToDelete);
//                                arrayAdapter.notifyDataSetChanged();
//                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
//                                HashSet<String> set = new HashSet(MainActivity.notes);
//                                sharedPreferences.edit().putStringSet("notes", set).apply();
//                            }
//                        }).setNegativeButton("No", null).show();
//                return true;
//            }
//        });
    }

    public void setUpButtons() {
        btnNotesTodos = (Button) findViewById(R.id.btnNotes);
        btnNotesTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteEditorActivity.class));
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