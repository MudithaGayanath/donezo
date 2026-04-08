package lk.jiat.donezo.entity;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.util.Date;

import lk.jiat.donezo.entity.Category;
import lk.jiat.donezo.entity.Priority;
import lk.jiat.donezo.entity.TaskStatus;
import lombok.Data;

@Entity(
        tableName = "task",
        foreignKeys = {
                @ForeignKey(
                        entity = Priority.class,
                        parentColumns = "id",
                        childColumns = "priority_id"
                ),
                @ForeignKey(
                        entity = Category.class,
                        parentColumns = "id",
                        childColumns = "category_id"
                ),
                @ForeignKey(
                        entity = TaskStatus.class,
                        parentColumns = "id",
                        childColumns = "status_id"
                )
        }
)
@Data
public class Task {



    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public Integer priority_id;
    public Integer category_id;
    public Integer status_id;

    public long created_at;
}