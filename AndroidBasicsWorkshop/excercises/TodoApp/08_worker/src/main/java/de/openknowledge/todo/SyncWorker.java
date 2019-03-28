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

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import de.openknowledge.todo.domain.TodoRepository;

/**
 * Worker to synchronize todos via web service.
 */
public class SyncWorker extends Worker {

    /**
     * Default construtor
     * @param context application context
     * @param workerParams additional worker parameter (optional)
     */
    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /**
     * Worker logic: refresh the repository data via web service call.
     * @return result status of the executed job
     */
    @NonNull
    @Override
    public Result doWork() {
        Log.d(SyncWorker.class.getSimpleName(), "Worker do his work");
        new TodoRepository().refresh();
        return Result.success();
    }

}
