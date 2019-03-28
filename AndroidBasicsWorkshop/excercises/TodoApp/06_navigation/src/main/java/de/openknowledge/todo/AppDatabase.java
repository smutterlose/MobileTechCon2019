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

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import de.openknowledge.todo.domain.Todo;
import de.openknowledge.todo.domain.TodoDao;
import de.openknowledge.todo.domain.TodoType;

/**
 * Application database.
 */
@Database(entities = {Todo.class}, version = 1)
@TypeConverters({ TodoType.TodoTypeConverter.class })
public abstract class AppDatabase extends RoomDatabase {

    // singleton database instance
    public static AppDatabase INSTANCE;

    /**
     * Database initialization
     * @param context app context
     */
    public static void initDatabase(Context context) {

        // create static room database instance
        INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "appDatabase")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        MIGRATION_0_1.migrate(db);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }
                })
                .build();
    }

    private static final Migration MIGRATION_0_1 = new Migration(0, 1) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("INSERT INTO todo values(1, 'Go shopping', 'Buy a new phone', 'Looking for a new mobile phone and some accessories.', 'SHOPPING')");
            database.execSQL("INSERT INTO todo values(2, 'Mother', 'Call my mother', 'It is mothers birthday. So better do not forget to call her (+49 171 555 555)!', 'CALL')");
            database.execSQL("INSERT INTO todo values(3, 'Make Sports', 'Hansefit', 'A cardio training a day keeps the doctor away', 'SPORT')");
        }
    };

    public abstract TodoDao todoDao();
}