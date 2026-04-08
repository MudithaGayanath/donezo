package lk.jiat.donezo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lk.jiat.donezo.entity.Category;
import lk.jiat.donezo.entity.Task;

@Dao
public interface CategoryDao {
    @Insert
    long insert(Category category);

    @Update
    void update(Category category);


    @Query("SELECT * FROM category")
    List<Category> getAll();


    @Query( "SELECT * FROM category WHERE id =:catId")
    Category getById(int catId);

    @Query( "SELECT * FROM category WHERE name =:name")
    Category getByName(String name);
}
