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

import android.util.Log;

import java.util.List;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import de.openknowledge.todo.App;
import de.openknowledge.todo.AppDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Repository for a unified access to different
 * datasources (remote web service and local database)
 */
public class TodoRepository {

    // dao for database access
    private TodoDao todoDao = AppDatabase.INSTANCE.todoDao();

    // retrofit instance for web service access
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TodoWebServiceClient.TODO_WEB_SERVICE_URL)
            .client(HttpClientFactory.getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * Retrieves all to-do items from database via DAO
     * @return all to-do items
     */
    public LiveData<List<Todo>> getAllTodos() {
        return getAllTodos(false);
    }

    /**
     * Retrieves all to-do items from web service,
     *
     * @return all to-do items
     */
    public LiveData<List<Todo>> refresh() {
        return getAllTodos(true);
    }

    /**
     * Retrieves all to-do items
     * - from web service, if parameter is true
     * - from local db, else
     *
     * @param synced
     * @return all to-do items
     */
    public LiveData<List<Todo>> getAllTodos(boolean synced) {
        if(synced) {
            sync();
        }
        return todoDao.getAll();
    }


    /**
     * fetches all available to-do items via web services and updates local database
     */
    private void sync() {

        retrofit.create(TodoWebServiceClient.class).getAllTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, final Response<List<Todo>> response) {
                Executors.newSingleThreadExecutor().submit(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TodoRepository.class.getSimpleName(), "Got " + response.body().size() + " todo entries from web service");
                        todoDao.deleteAll();
                        todoDao.insertAll(response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TodoRepository.class.getSimpleName(), "error while getting data from web", t);
            }
        });
    }
}
