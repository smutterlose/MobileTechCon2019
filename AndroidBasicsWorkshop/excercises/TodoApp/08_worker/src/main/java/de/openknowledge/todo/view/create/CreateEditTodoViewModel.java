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


import java.util.concurrent.Executors;

import androidx.lifecycle.ViewModel;
import de.openknowledge.todo.AppDatabase;
import de.openknowledge.todo.domain.Todo;

/**
 * View model to hold / share current to-do item
 * - for edit purpose
 * - for creation purpose
 */
public class CreateEditTodoViewModel extends ViewModel {

    // current to-do item
    private Todo todo;

    // store new or edited to-do item in database
    public void onSubmit() {
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                if (todo.getId() == null) {
                    AppDatabase.INSTANCE.todoDao().insertAll(todo);
                } else {
                    AppDatabase.INSTANCE.todoDao().update(todo);
                }
            }
        });
    }

    // returns current to-do item
    public Todo getTodo() {
        return todo;
    }

    // sets current to-do item
    public void setTodo(Todo todo) {
        this.todo = todo;
    }

}
