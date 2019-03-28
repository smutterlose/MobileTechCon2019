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
package de.openknowledge.todo.view.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import de.openknowledge.todo.R;
import de.openknowledge.todo.domain.Todo;
import de.openknowledge.todo.domain.TodoRepository;
import de.openknowledge.todo.view.detail.TodoDetailFragment;

/**
 * View controller for the apps main screen, showing
 * - a list of to-do items on a smartphone
 * - a list of to-do items and the selected to-do item details om a tablet (landscape mode)
 */
public class TodoOverviewFragment extends Fragment implements TodoAdapter.TodoItemClickListener {

    // indicator for screen mode:
    // - true: two pane (tablet)
    // - false: one pane (smartphone)
    private boolean mTwoPane;

    public TodoOverviewFragment() {
        // Required empty public constructor
    }

    //---------- fragment callback methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_todo_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // add floating action button and define its click listener
        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "floating button action not yet implemented",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // retrieve all to-do items
        List<Todo> listValues = TodoRepository.getAllTodos();

        // initiate the custom list adapter with existing to-do items
        TodoAdapter adapter = new TodoAdapter(listValues, this);

        // assign custom list adapter to recycler view of the activities layout
        RecyclerView todoOverview = view.findViewById(R.id.list_todo_overview);
        todoOverview.setAdapter(adapter);

        mTwoPane = view.findViewById(R.id.detail_container) != null;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    //---------- TodoAdapter.TodoItemClickListener callback methods

    // handle item click in respect to screen modus
    @Override
    public void onTodoItemClicked(Todo todo, int position) {
        if (mTwoPane) {
            showDetailFragment(R.id.detail_container, getChildFragmentManager(), todo);
        } else {
            showDetailFragment(R.id.container,requireActivity().getSupportFragmentManager() , todo);
        }
    }

    private void showDetailFragment(@IdRes int containerId, FragmentManager fragmentManager, Todo todo) {
        fragmentManager.beginTransaction()
                .replace(containerId, TodoDetailFragment.create(todo))
                .addToBackStack(TodoDetailFragment.class.getSimpleName())
                .commit();
    }
}
