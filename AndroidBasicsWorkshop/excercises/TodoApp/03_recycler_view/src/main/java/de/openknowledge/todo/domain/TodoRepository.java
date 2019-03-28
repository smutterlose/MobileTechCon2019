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

import java.util.ArrayList;
import java.util.List;

/**
 * Static mock repository for the apps main entities, to-do items.
 */
public class TodoRepository {

    /**
     * Access all to-do items.
     *
     * @return List of to-do items.
     */
    public static List<Todo> getAllTodos() {
        ArrayList<Todo> listValues = new ArrayList<>();
        listValues.add(new Todo("Go shopping", "Buy a new phone", "Looking for a new mobile phone and some accessories.", TodoType.SHOPPING));
        listValues.add(new Todo("Mother", "Call my mother", "It' s mothers birthday. So better do not forget to call her (+49 171 555 555)!", TodoType.CALL));
        listValues.add(new Todo("Make Sports", "Hansefit", "A cardio training a day keeps the doctor away.", TodoType.SPORT));
        return listValues;
    }
}

