package de.openknowledge.todo.domain;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Data access object for to-do items via room database
 */
@Dao
public interface TodoDao {

    /**
     * Insert to-do items into database
     * @param item list of to-do items to insert
     */
    @Insert
    void insertAll(Todo... item);

    /**
     * Update a item
     * @param item to-do item to update
     */
    @Update
    void update(Todo item);

    /**
     * run sql query to select all items
     * @return <code>LiveData</code> list of to-do items
     */
    @Query("select * from todo")
    LiveData<List<Todo>> getAll();

    /**
     * run sql query to select one item by id
     * @return <code>LiveData</code> to-do item
     */
    @Query("select * from todo where id = :id")
    LiveData<Todo> getById(long id);

    /**
     * run sql query to delete one item
     * @param item to-do item to delete
     */
    @Delete
    void delete(Todo item);

    /**
     * run sql query to delete all items
     */
    @Query("delete from todo")
    void deleteAll();
}
