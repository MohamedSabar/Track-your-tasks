package eu.trackyourtasks.trackyourtasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// @Mohamed Sabar

public class To_Do_list extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView_todo;
    private Button btn_add;
    private FirebaseFirestore mDatabase;
    private CollectionReference todosCollectionRef;
    private String KEY_TITLE = "todoTitle";
    private String KEY_CREATED_AT = "todoCreatedAt";

    private ArrayList<String> todosTitles = new ArrayList<>();
    private ArrayList<String> todosIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        mDatabase = FirebaseFirestore.getInstance();
        todosCollectionRef = mDatabase.collection("todos");

        listView_todo = findViewById(R.id.listView_todo);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });
        getTodosFromDatabase();
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.editTextView_todo);
        String itemText = input.getText().toString();

        if (!(itemText.equals(""))) {
            itemsAdapter.add(itemText);
            writeTodoDataToDatabase(itemText);
            input.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "please enter text", Toast.LENGTH_LONG).show();
        }
    }

    private void setUpListViewListener() {
        listView_todo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                removeTodo(todosIds.get(i));
                todosTitles.remove(i);
                itemsAdapter.notifyDataSetChanged();
                Toast.makeText(context, "item has been removed", Toast.LENGTH_LONG).show();
                todosIds.remove(i);
                return true;
            }
        });
    }

    public void writeTodoDataToDatabase(String title) {
        String KEY_TITLE = "todoTitle";
        String KEY_CREATED_AT = "todoCreatedAt";

        Map<String, Object> todo = new HashMap<>();
        todo.put(KEY_TITLE, title);
        todo.put(KEY_CREATED_AT, System.currentTimeMillis());

        mDatabase.collection("todos").document().set(todo)
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

    private void getTodosFromDatabase() {
        todosCollectionRef.orderBy(KEY_CREATED_AT, Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    todosIds.add(documentSnapshot.getId().toString());
                    todosTitles.add(documentSnapshot.getData().get(KEY_TITLE).toString());
                }
                setUpView();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void setUpView() {
        itemsAdapter = new ArrayAdapter<>(To_Do_list.this, android.R.layout.simple_list_item_1, todosTitles);
        listView_todo.setAdapter(itemsAdapter);
        setUpListViewListener();
    }

    private void removeTodo(String id) {
        mDatabase.collection("todos").document(String.valueOf(id)).delete();
    }
}