package lk.jiat.donezo.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

import lk.jiat.donezo.entity.Task;

@Dao
public interface TaskDao {

    @Insert
    long insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM task WHERE id = :id")
    Task getTaskById(int id);

    @Query( "SELECT  * FROM task WHERE category_id = :catId")
    List<Task> getAllByCategory(int catId);
    @Query("SELECT * FROM task WHERE category_id = :catId AND created_at BETWEEN :startOfDay AND :endOfDay")
    List< Task> getAllByCategoryAndDateRange(int catId, long startOfDay, long endOfDay);

    @Query("DELETE FROM task")
    void deleteAll();
}