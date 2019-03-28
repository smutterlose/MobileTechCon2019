/*
 * Copyright 2019 open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.openknowledge.todo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import de.openknowledge.todo.domain.Todo;
import de.openknowledge.todo.domain.TodoRepository;

/**
 * View controller for the apps main screen,
 * showing a list of to-do items.
 */
public class MainActivity extends AppCompatActivity implements TodoAdapter.TodoItemClickListener {

    //--------- callback methods of Activity class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // set toolbar cause we are using <code>AppCompatActivity</code>
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add floating action button and define its click listener
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "floating button action not yet implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // retrieve all to-do items
        List<Todo> listValues = TodoRepository.getAllTodos();

        TodoAdapter adapter = new TodoAdapter(listValues, this);

        // assign custom list adapter to recycler view of the activities layout
        RecyclerView todoOverview = findViewById(R.id.list_todo_overview);

        todoOverview.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String toastText;

        switch (item.getItemId()) {
            case R.id.refresh:
                toastText = "menu action 'refresh' clicked";
                break;
            case R.id.settings:
                toastText = "menu action 'settings' clicked";
                break;
            case R.id.search:
                toastText = "menu action 'search' clicked";
                break;
            case R.id.help:
                toastText = "menu action 'help' clicked";
                break;
            default:
                toastText = "unknown menu item clicked";
        }

        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //--------- callback methods of TodoAdapter.TodoItemClickListener class

    @Override
    public void onTodoItemClicked(Todo todo, int position) {
        Toast.makeText(this, "list item clicked at position " + position, Toast.LENGTH_SHORT).show();
    }

}
