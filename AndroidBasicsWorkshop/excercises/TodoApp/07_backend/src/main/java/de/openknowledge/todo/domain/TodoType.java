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

import android.text.TextUtils;

import androidx.room.TypeConverter;
import de.openknowledge.todo.R;

/**
 * Enumeration of supported to-do item types.
 * Wires item type to a corresponding drawable.
 */
public enum TodoType {
    SHOPPING(R.drawable.ic_shopping),
    CALL(R.drawable.ic_meeting),
    SPORT(R.drawable.ic_sport),
    PLAY(R.drawable.ic_gaming),
    WATCH(R.drawable.ic_televisions);

    private int imageId;

    TodoType(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    // converter for room database convenient values
    public static class TodoTypeConverter {

        @TypeConverter
        public static TodoType fromStringToCategory(String category) {
            if (TextUtils.isEmpty(category))
                return SHOPPING;
            return TodoType.valueOf(category);
        }

        @TypeConverter
        public static String fromStringToCategory(TodoType todoType) {
            if (todoType==null)
                return "";
            return todoType.name();
        }
    }
}
