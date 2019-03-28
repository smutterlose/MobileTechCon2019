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
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.openknowledge.todo.domain.Todo;

/**
 * Custom list adapter to map to-do item
 * to <code>listitem_todo</code> layout resource.
 */
public class TodoAdapter extends ArrayAdapter<Todo> {

    /**
     * Default constructor, initializing the custom adapter
     * with a corresponding list of to-do items.
     *
     * @param context android application context
     * @param objects list of to-do items
     */
    public TodoAdapter(@NonNull Context context, List<Todo> objects) {
        super(context, R.layout.listitem_todo, objects);
    }

    /**
     * Adapter callback method, mapping object values to view resources.
     *
     * @param position position of view to map
     * @param convertView view to convert / map
     * @param parent parent view group
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // retrieve item to convert to list item view (one single row of the to-do list)
        Todo item = getItem(position);

        // inflate list item view (one single row of the to-do list)
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.listitem_todo, parent, false);

        // convert / map fields
        if (item != null) {

            // set R.id.txt_title of list item view to "title"
            TextView title = view.findViewById(R.id.txt_title);
            title.setText(item.getTitle());

            // set R.id.txt_shortDescription of list item view to "shortDescription"
            TextView shortDescription = view.findViewById(R.id.txt_short_description);
            shortDescription.setText(item.getShortDescription());

            // set R.id.img_type of list item view to corresponding drawable
            ImageView type = view.findViewById(R.id.img_type);
            Drawable drawable = getContext().getResources().getDrawable(item.getTodoType().getImageId());
            type.setImageDrawable(drawable);
        }

        // return view with converted / mapped item values
        return view;
    }
}
