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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import de.openknowledge.todo.MainViewModel;
import de.openknowledge.todo.R;
import de.openknowledge.todo.domain.Todo;


/**
 * View controller for the apps main screen, showing a list of to-do items
 */
public class TodoOverviewFragment extends Fragment implements TodoAdapter.TodoItemClickListener {

    // access to shared view model (singleton of shared app data)
    private MainViewModel sharedViewModel;

    // access to global navigation controller
    private NavController navController;

    public TodoOverviewFragment() {
        // Required empty public constructor
    }

    //---------- fragment callback methods


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_todo_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // add floating action button and define its click listener for navigation purpose
        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(TodoOverviewFragmentDirections.actionTodoOverviewFragmentToCreateEditTodoFragment(null));
            }
        });

        // set navigation controller
        navController = Navigation.findNavController(requireActivity(), R.id.main_host_fragment);

        // access shared view model (singleton of shared app data)
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        /// initiate the custom list adapter
        final TodoAdapter adapter = new TodoAdapter(this);

        // assign custom list adapter to recycler view of the activities layout
        RecyclerView todoOverview = view.findViewById(R.id.list_todo_overview);
        todoOverview.setAdapter(adapter);

        // access view model for overview view
        TodoOverviewViewModel viewModel = ViewModelProviders.of(this).get(TodoOverviewViewModel.class);

        // observe to-do list changes via view model and update adapter in case of change
        viewModel.getTodoList().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter.setItemList(todos);
            }
        });
    }


    @Override
    public void onTodoItemClicked(Todo todo) {
        sharedViewModel.setSelectedTodo(todo);

        navController.navigate(TodoOverviewFragmentDirections.actionTodoOverviewFragmentToTodoDetailFragment());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

}
