package lk.jiat.donezo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lk.jiat.donezo.entity.Priority;

@Dao
public interface PriorityDao {

    @Insert
    long insert(Priority p);

    @Query("SELECT * FROM priority")
    List<Priority> getAll();

    @Query("SELECT * FROM priority WHERE name =:name")
    Priority getByName(String name);

    @Query("SELECT * FROM priority WHERE id =:id")
    Priority getById(int id);
}
