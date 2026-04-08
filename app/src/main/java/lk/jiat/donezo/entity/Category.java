package lk.jiat.donezo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "category")
@Data
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int status;
    public int color;
}
