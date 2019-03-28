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
package de.openknowledge.todo.view.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import de.openknowledge.todo.MainViewModel;
import de.openknowledge.todo.R;
import de.openknowledge.todo.domain.Todo;

/**
 * View controller for item detail information
 */
public class TodoDetailFragment extends Fragment {

    // access shared view model (singleton of shared app data)
    private MainViewModel sharedViewModel;

    public TodoDetailFragment() {
        // Required empty public constructor
    }

    //----------  Fragment callback methods

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_todo_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set observer for selected item changes
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        sharedViewModel.getSelectedTodo().observe(getViewLifecycleOwner(), new Observer<Todo>() {
            @Override
            public void onChanged(Todo todo) {
                // update view if selected item changes
                updateView(todo);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // inflate special detail menu (allow edit)
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // check for "edit" pressed
        if (item.getItemId() == R.id.edit) {
            NavDirections directions = TodoDetailFragmentDirections.actionTodoDetailFragmentToCreateEditTodoFragment(sharedViewModel.getSelectedTodo().getValue());
            Navigation.findNavController(requireActivity(), R.id.main_host_fragment).navigate(directions);
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    //----------  internal methods

    /**
     * Iniztializes / updates detail view with given item data.
     * @param todoItem item to display
     */

    private void updateView(Todo todoItem) {
        TextView title = getView().findViewById(R.id.txt_title);
        TextView shortDescription = getView().findViewById(R.id.txt_short_description);
        TextView fullDescription = getView().findViewById(R.id.txt_description);
        ImageView todoType = getView().findViewById(R.id.img_type);

        title.setText(todoItem.getTitle());
        shortDescription.setText(todoItem.getShortDescription());
        fullDescription.setText(todoItem.getDescription());
        todoType.setImageDrawable(ContextCompat.getDrawable(requireContext(), todoItem.getTodoType().getImageId()));

    }
}
