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
package de.openknowledge.todo.domain;

import java.io.Serializable;

import androidx.annotation.NonNull;

/**
 * Representation of the main app entity, the to-do item.
 */
public class Todo implements Serializable {

    // unique title of the item, e.g. for displaying in a list
    private String title;

    // short, human readable description (one liner)
    private String shortDescription;

    // long description showing details of the item.
    private String description;

    // type, used to categorize the item
    // for possible values see <code>TodoType</code>
    private TodoType todoType;

    public Todo(String title, String shortDescription, String description, TodoType todoType) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.todoType = todoType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoType getTodoType() {
        return todoType;
    }

    public void setTodoType(TodoType todoType) {
        this.todoType = todoType;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
