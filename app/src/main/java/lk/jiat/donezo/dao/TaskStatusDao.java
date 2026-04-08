package lk.jiat.donezo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lk.jiat.donezo.entity.TaskStatus;

@Dao
public interface TaskStatusDao {
    @Insert
    long insert(TaskStatus ts);

    @Query("SELECT * FROM task_status")
    List<TaskStatus> getAll();

    @Query("SELECT * FROM task_status WHERE name =:name")
    TaskStatus getByName(String name);

    @Query("SELECT * FROM task_status WHERE id =:id")
    TaskStatus getById(int id);
}
