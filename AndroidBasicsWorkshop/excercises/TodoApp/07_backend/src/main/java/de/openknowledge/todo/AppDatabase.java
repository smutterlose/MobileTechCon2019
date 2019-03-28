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

import java.util.UUID;

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
@Database(entities = {Todo.class}, version = 2)
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
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE todo");
            database.execSQL("CREATE TABLE IF NOT EXISTS `todo` (`id` TEXT NOT NULL, `title` TEXT, `shortDescription` TEXT, `description` TEXT, `todoType` TEXT, PRIMARY KEY(`id`))");

            database.execSQL("INSERT INTO todo values('70e5aa7f-b956-42fb-b0f7-0dfb85b0dd67', 'Go shopping', 'Buy a new phone', 'Looking for a new mobile phone and some accessories.', 'SHOPPING')");
            database.execSQL("INSERT INTO todo values('af82401b-0d66-44cc-aeea-851371b9d4c5', 'Mother', 'Call my mother', 'It is mothers birthday. So better do not forget to call her (+49 171 555 555)!', 'CALL')");
            database.execSQL("INSERT INTO todo values('917bf594-388c-4e46-bc36-afe70a51995a', 'Make Sports', 'Hansefit', 'A cardio training a day keeps the doctor away', 'SPORT')");
        }
    };


    public abstract TodoDao todoDao();
}