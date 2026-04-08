package lk.jiat.donezo.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.donezo.database.AppDatabase;
import lk.jiat.donezo.entity.Task;
import lk.jiat.donezo.entity.TaskStatus;
import lombok.Data;

public class TaskService {

    private static TaskService instance;
    private Context context;
    private TaskService(){}

    public static synchronized TaskService getInstance(Context context){
        if (instance == null){
            instance = new TaskService();
            instance.context = context;
        }
        return instance;
    }

    /**
     *
     * @param catId
     * @return TaskInfoForCategory
     */

    public TaskInfoForCategory getByCategory(int catId){

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        // Start of day
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.set(java.util.Calendar.MINUTE, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        long start = calendar.getTimeInMillis();

        // End of day
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
        calendar.set(java.util.Calendar.MINUTE, 59);
        calendar.set(java.util.Calendar.SECOND, 59);
        long end = calendar.getTimeInMillis();


        List<Task> taskList = AppDatabase.getInstance(context).taskDao().getAllByCategoryAndDateRange(catId,start,end);
        if(taskList.isEmpty()) return null;
//
        TaskInfoForCategory info = new TaskInfoForCategory();
//
        taskList.forEach( task -> {
            // count tasks
            info.task++;
            TaskStatus status = TaskStatusService.getInstance(context).getById(task.status_id);
            if(status.equals(TaskStatusService.PENDING) || status.equals(TaskStatusService.IN_PROGRESS)){
                info.setRemaining(info.getRemaining() + 1);
            }
        });
info.setCompletedPercentage(info.getRemaining()/ info.getTask() * 100);
        return info;
    }

    @Data
    public static  class TaskInfoForCategory{
        private int task;
        private int remaining;
        private int completedPercentage;
    }
}
