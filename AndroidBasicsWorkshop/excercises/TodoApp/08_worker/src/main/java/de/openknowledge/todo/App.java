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

import android.app.Application;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

/**
 * Application instance.
 */
public class App extends Application {

    /**
     * Creates internal database.
     */

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize DB instance
        AppDatabase.initDatabase(this);

        Constraints networkConstraints = new Constraints.Builder().setRequiredNetworkType(
                NetworkType.CONNECTED).build();

        // initialize DB periodic work request (every 15 minutes, which is the allowed minimum)
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(SyncWorker.class, 15,
                TimeUnit.MINUTES).setConstraints(networkConstraints).build();

        // enqueue work request with the help of the work manager
        WorkManager.getInstance().enqueue(workRequest);
    }

}
