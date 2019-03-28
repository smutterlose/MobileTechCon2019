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
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import de.openknowledge.todo.domain.TodoRepository;

/**
 * Single activity of the app.
 * All view related stuff is managed via Fragments.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout with main container for fragment management
        setContentView(R.layout.activity_main);

        // set toolbar cause we are using <code>AppCompatActivity</code>
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup navigation, no fragment manager handling needed
        NavController navController = Navigation.findNavController(this, R.id.main_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String toastText;

        int clickedItemId = item.getItemId();

        if (clickedItemId == android.R.id.home) {
            onBackPressed();
        } else if (clickedItemId == R.id.refresh) {
            new TodoRepository().refresh();
        } else {
            switch (item.getItemId()) {
                case R.id.edit:
                    toastText = "menu action 'edit' clicked";
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
        }
        return super.onOptionsItemSelected(item);
    }
}
