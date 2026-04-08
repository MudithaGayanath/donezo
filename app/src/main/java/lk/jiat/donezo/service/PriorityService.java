package lk.jiat.donezo.service;

import android.content.Context;

import lk.jiat.donezo.dao.PriorityDao;
import lk.jiat.donezo.database.AppDatabase;
import lk.jiat.donezo.entity.Priority;

public class PriorityService {

    private final PriorityDao table; 
    private static PriorityService instance;

    public static final String LOW = "Low";
    public static final String MEDIUM = "Medium";
    public static final String HIGH = "High";
    public static final String URGENT = "Urgent";


    private PriorityService(Context context) {
        this.table = AppDatabase.getInstance(context).priorityDao();
        initDefaultValues();
    }

    private void initDefaultValues() {
        if(!table.getAll().isEmpty()) return;
        String[] priorities = {"Low", "Medium", "High", "Urgent"};
        for (String pri :priorities){
            Priority priority = new Priority();
            priority.setName(pri);
            table.insert(priority);
        }
    }


    public Priority getByName(String name){
        return table.getByName(name);
    }
    public Priority getById(int id){
        return table.getById(id);
    }

    public static synchronized PriorityService getInstance(Context context) {
        if (instance == null) {
            instance = new PriorityService(context);
        }
        return instance;
    }


}