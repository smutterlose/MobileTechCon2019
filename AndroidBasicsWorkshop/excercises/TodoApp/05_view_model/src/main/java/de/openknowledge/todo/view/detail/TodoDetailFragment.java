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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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


    //----------  internal methods

    /**
     * Iniztializes / updates detail view with given item data.
     * @param to-do item to display
     */
    private void updateView(Todo todo) {
        // access view fields
        TextView title = getView().findViewById(R.id.txt_title);
        TextView shortDescription = getView().findViewById(R.id.txt_short_description);
        TextView fullDescription = getView().findViewById(R.id.txt_description);
        ImageView todoType = getView().findViewById(R.id.img_type);

        // set view fields data
        title.setText(todo.getTitle());
        shortDescription.setText(todo.getShortDescription());
        fullDescription.setText(todo.getDescription());
        todoType.setImageDrawable(ContextCompat.getDrawable(requireContext(), todo.getTodoType().getImageId()));
    }

}
