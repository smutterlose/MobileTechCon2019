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
package de.openknowledge.todo.view.overview;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import de.openknowledge.todo.R;
import de.openknowledge.todo.domain.Todo;

/**
 * Custom list adapter extending <code>RecyclerView.Adapter</code>
 * to map to-do item to <code>listitem_todo</code> layout resource.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<Todo> itemList = Collections.emptyList();

    private TodoItemClickListener listener;

    /**
     * Default constructor.
     *
     * @param listener click listener
     */
    public TodoAdapter(@Nullable TodoItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Sets list of to-do items and notifies <code>RecyclerView.Adapter</code>
     *
     * @param list List of to-do items.
     */
    public void setItemList(List<Todo> list) {
        itemList = list;
        notifyDataSetChanged();
    }


    //------- callback method of RecyclerView.Adapter


    /**
     * Create a new {@link RecyclerView.ViewHolder}.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_todo, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Update the {@link RecyclerView.ViewHolder} contents with the item at the given position .
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Todo item = itemList.get(position);

        if (item != null) {

            // step 1: map item values to view holder fields
            holder.title.setText(item.getTitle());
            holder.shortDescription.setText(item.getShortDescription());
            Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), item.getTodoType().getImageId());
            holder.type.setImageDrawable(drawable);

            // step 2: set click listener to view holder
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onTodoItemClicked(item);
                    }
                }
            });

        }
    }

    /**
     * Counts number of items of related list.
     *
     * @return total item count of list
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }


    //---------- ViewHolder class


    /**
     * View holder for view related data of to-do item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView shortDescription;
        TextView title;
        ImageView type;


        ViewHolder(@NonNull View itemView) {
            super(itemView);

            shortDescription = itemView.findViewById(R.id.txt_short_description);
            title = itemView.findViewById(R.id.txt_title);
            type = itemView.findViewById(R.id.img_type);
        }
    }


    interface TodoItemClickListener {
        void onTodoItemClicked(Todo todo);
    }
}
