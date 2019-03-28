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

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * RESTful Web Service for to-do items
 */
public interface TodoWebServiceClient {

    String TODO_WEB_SERVICE_URL = "http://10.0.2.2:8080/api/";

    @GET("todos")
    Call<List<Todo>> getAllTodos();

    @GET("todos/{id}")
    Call<Todo> getTodo(@Path("id") String id);

    @POST("todos")
    Call<Todo> createTodo(@Body Todo todo);

    @PUT("todos/{id}")
    Call<Todo> updateTodo(@Path("id") String id, @Body Todo todo);

    @DELETE("todos/{id}")
    Call<Todo> deleteTodo(@Path("id") String id);

}
