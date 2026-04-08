package lk.jiat.donezo.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "task_status")
@Data
public class TaskStatus {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
}
