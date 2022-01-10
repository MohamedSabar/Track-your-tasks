package eu.trackyourtasks.trackyourtasks;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.ArrayList;
// @Mohamed Sabar

public class To_Do_list extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView_todo;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        listView_todo = findViewById(R.id.listView_todo);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });


        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(To_Do_list.this, android.R.layout.simple_list_item_1, items);
        listView_todo.setAdapter(itemsAdapter);
        setUpListViewListener();

    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.editTextView_todo);
        String itemText = input.getText().toString();

        if (!(itemText.equals(""))) {
            itemsAdapter.add(itemText);
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
                Toast.makeText(context, "item has been removed", Toast.LENGTH_LONG).show();
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}