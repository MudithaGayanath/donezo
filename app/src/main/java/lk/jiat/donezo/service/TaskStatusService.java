package lk.jiat.donezo.service;

import android.content.ContentValues;
import android.content.Context;

import lk.jiat.donezo.dao.TaskStatusDao;
import lk.jiat.donezo.database.AppDatabase;
import lk.jiat.donezo.entity.TaskStatus;

public class TaskStatusService {
    private final TaskStatusDao table;

    private static TaskStatusService instance;
    public static final String PENDING = "Pending";
    public static final String IN_PROGRESS = "In Progress";
    public static final String EXPIRED = "Expired";
    public static final String SCHEDULED = "Scheduled";
    public static final String COMPLETED = "Completed";


    private TaskStatusService(Context context) {
        this.table = AppDatabase.getInstance(context).taskStatusDao();
        initDefaultData();
    }

    public static synchronized TaskStatusService getInstance(Context context) {
        if (instance == null) {
            instance = new TaskStatusService(context);
        }
        return instance;
    }

    private void initDefaultData(){
        if(!table.getAll().isEmpty()) return;
        String[] statuses = {"Pending", "In Progress", "Expired","Scheduled","Completed"};
        for (String status : statuses) {
            TaskStatus st = new TaskStatus();
            st.setName(status);
            table.insert(st);
        }
    }

    TaskStatus getByName(String name){
        return  table.getByName(name);
    }
    TaskStatus getById(int id){
        return  table.getById(id);
    }
}
