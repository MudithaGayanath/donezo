package lk.jiat.donezo.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.donezo.activity.CategoryActivity;
import lk.jiat.donezo.dao.CategoryDao;
import lk.jiat.donezo.database.AppDatabase;
import lk.jiat.donezo.entity.Category;
import lk.jiat.donezo.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

public class CategoryService {
    private static CategoryService instance;

    private final Context context;
    private final CategoryDao table;


    private CategoryService(Context context){
        this.context = context;
        this.table = AppDatabase.getInstance(context).categoryDao();
    }

    public static synchronized CategoryService getInstance(Context context){
        if(instance == null){
            instance = new CategoryService(context);
        }
        return instance;

    }


    public Category getById(int id){
       return table.getById(id);
    }

    public List<Category> loadCategories() {
        List<Category> list = new ArrayList<>();

        AppDatabase database = AppDatabase.getInstance(context);
      return   database.categoryDao().getAll();


    }


    public boolean save(Category c){
        AppDatabase database = AppDatabase.getInstance(context);
        long insert = database.categoryDao().insert(c);


        return insert > -1;
    }

    public List<CategoryAdapterData> setDataIntoAdapter(List<Category> list){
        List<CategoryAdapterData> dataList = new ArrayList<>();
        list.forEach(category -> {
            CategoryAdapterData data = new CategoryAdapterData();
            data.setCategory(category);
            data.setInfo(TaskService.getInstance(context).getByCategory(category.getId()));
            dataList.add(data);

        });
        return dataList;
    }

    public Category getByName(String name) {
        return table.getByName(name);
    }

    public void update(Category category) {
        table.update(category);
    }

    @Data
    public static class CategoryAdapterData{
        private Category category;
        private TaskService.TaskInfoForCategory info;

        public void showCategory(int catId,Context context){
            Intent intent = new Intent(context.getApplicationContext(), CategoryActivity.class);
            intent.putExtra("is_update",true);
            intent.putExtra("cat_id",catId);
            context.startActivity(intent);
        }
    }

}
