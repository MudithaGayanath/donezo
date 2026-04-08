package lk.jiat.donezo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lk.jiat.donezo.dao.CategoryDao;
import lk.jiat.donezo.dao.PriorityDao;
import lk.jiat.donezo.dao.TaskDao;
import lk.jiat.donezo.dao.TaskStatusDao;
import lk.jiat.donezo.entity.Category;
import lk.jiat.donezo.entity.Priority;
import lk.jiat.donezo.entity.Task;
import lk.jiat.donezo.entity.TaskStatus;


@Database(entities = {Category.class,
        Priority.class,
TaskStatus.class,
Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static String DATABASE_NAME = "donezo.db";
    private static AppDatabase INSTANCE;


    public abstract CategoryDao categoryDao();
    public abstract TaskDao taskDao();
    public abstract PriorityDao priorityDao();
    public abstract TaskStatusDao taskStatusDao();


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME
                    ).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }








}
