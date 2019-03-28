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
package de.openknowledge.todo.view.create;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import de.openknowledge.todo.R;
import de.openknowledge.todo.domain.TodoType;

/**
 * Custom list adapter extending <code>RecyclerView.Adapter</code>
 * to map <code>TodoType</code> item to <code>listitem_todo_type</code> layout resource.
 */
public class TodoTypeAdapter extends RecyclerView.Adapter<TodoTypeAdapter.ViewHolder> {

    // list of possible to-do types
    private List<TodoType> itemList = Arrays.asList(TodoType.values());

    // selection listener
    private TodoTypeSelectedListener listener;

    // currently selected view
    private View selected;

    // preselected to-do type
    private TodoType preSelected;

    /**
     * Default constructor.
     *
     * @param listener click listener
     * @param preSelected preselected to-do type
     */
    public TodoTypeAdapter(@Nullable TodoTypeSelectedListener listener, TodoType preSelected) {
        this.listener = listener;
        this.preSelected = preSelected != null ? preSelected : TodoType.SHOPPING;
    }

    //------------ callback methods of RecyclerView.Adapter

    /**
     * Create a new {@link RecyclerView.ViewHolder}.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_todo_type, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Update the {@link RecyclerView.ViewHolder} contents with the item at the given position .
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TodoType item = itemList.get(position);

        if (item != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onTodoTypeSelected(item);
                    }
                    if (selected != null) {
                        selected.setSelected(false);
                    }
                    selected = v;
                    v.setSelected(true);
                }
            });

            if (selected == null && item.equals(preSelected)) {
                holder.itemView.setSelected(true);
                selected = holder.itemView;
            }

            Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), item.getImageId());
            holder.type.setImageDrawable(drawable);
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


    /**
     * View holder class for to-do item image
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView type;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.img_type);
        }
    }

    //---------- click listener


    interface TodoTypeSelectedListener {
        void onTodoTypeSelected(TodoType todoType);
    }

}
