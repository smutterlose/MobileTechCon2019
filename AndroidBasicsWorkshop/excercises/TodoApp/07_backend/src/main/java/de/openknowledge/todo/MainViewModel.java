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

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import de.openknowledge.todo.domain.Todo;

/**
 * Shared view model of for app wide access
 */
public class MainViewModel extends ViewModel {

    // current selected to-do item
    private MutableLiveData<Todo> selectedTodo = new MediatorLiveData<>();


    /**
     * returns the current selected to-do item
     * @return current selected to-do item
     */
    public LiveData<Todo> getSelectedTodo() {
        return selectedTodo;
    }

    /**
     * sets the current selected to-do item
     * @param item current selected to-do item
     */
    public void setSelectedTodo(Todo item) {
        selectedTodo.postValue(item);
    }
}
