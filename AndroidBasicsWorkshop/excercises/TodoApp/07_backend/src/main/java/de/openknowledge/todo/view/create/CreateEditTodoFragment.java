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
package de.openknowledge.todo.view.create;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import de.openknowledge.todo.MainViewModel;
import de.openknowledge.todo.R;
import de.openknowledge.todo.domain.Todo;
import de.openknowledge.todo.domain.TodoType;

/**
 * View controller for create / edit to-do item view
 */
public class CreateEditTodoFragment extends Fragment {


    // view model for this fragment
    private CreateEditTodoViewModel viewModel;

    // shared view model (singleton of shared app data)
    private MainViewModel sharedViewModel;


    private TextInputLayout title;
    private TextInputLayout shortDescription;
    private TextInputLayout description;

    public CreateEditTodoFragment() {
        // Required empty public constructor
    }


    /**
     * stores edited or created item in data source via view model
     */
    public void onSubmit() {
        viewModel.getTodo().setTitle(title.getEditText().getText().toString());
        viewModel.getTodo().setDescription(description.getEditText().getText().toString());
        viewModel.getTodo().setShortDescription(shortDescription.getEditText().getText().toString());

        // delegate call to related view model
        viewModel.onSubmit();

        // update selected item in global view model
        sharedViewModel.setSelectedTodo(viewModel.getTodo());

        // trigger navigation for submit action
        Navigation.findNavController(requireActivity(), R.id.main_host_fragment)
                .navigate(CreateEditTodoFragmentDirections.actionCreateEditTodoFragmentToTodoDetailFragment());
    }

    //----------  Fragment callback methods



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_edit_todo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // access view model of this view
        viewModel = ViewModelProviders.of(this).get(CreateEditTodoViewModel.class);

        // access global view model
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        // edit mode? if yes, get to-do item via navigation argutment
        CreateEditTodoFragmentArgs args = CreateEditTodoFragmentArgs.fromBundle(getArguments());
        if (args.getTodoItem() != null) {
            viewModel.setTodo(args.getTodoItem());
        }

        // ... if no, create an empty / new to-do item
        if (viewModel.getTodo() == null) {
            viewModel.setTodo(new Todo());
        }

        // create to-do type adapter and set related click listener
        TodoTypeAdapter adapter = new TodoTypeAdapter(new TodoTypeAdapter.TodoTypeSelectedListener() {
            @Override
            public void onTodoTypeSelected(TodoType todoType) {
                viewModel.getTodo().setTodoType(todoType);
            }
        }, viewModel.getTodo().getTodoType());

        // set to-do type adapter to corresponding view (list of types to choose one from)
        RecyclerView todoTypesList = view.findViewById(R.id.list_todo_types);
        todoTypesList.setAdapter(adapter);

        // set click listener for save button
        Button button = view.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

        title = view.findViewById(R.id.textinput_title);
        shortDescription = view.findViewById(R.id.textinput_short_description);
        description = view.findViewById(R.id.textinput_description);

        title.getEditText().setText(viewModel.getTodo().getTitle());
        shortDescription.getEditText().setText(viewModel.getTodo().getShortDescription());
        description.getEditText().setText(viewModel.getTodo().getDescription());
    }

}
