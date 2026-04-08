package lk.jiat.donezo.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import lk.jiat.donezo.database.AppDatabase;
import lk.jiat.donezo.databinding.ActivityCategoryBinding;
import lk.jiat.donezo.entity.Category;
import lk.jiat.donezo.service.CategoryService;
import yuku.ambilwarna.AmbilWarnaDialog;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initBack();
        binding.backIcon.setOnClickListener(v -> moveToPrevious());
        binding.btnPickColor.setOnClickListener(v -> showColorModel());
        if (getIntent().getBooleanExtra("is_update", false)) {
            initUpdate();
        } else {
            initCreate();
        }
    }

    private void initBack() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    private void initUpdate() {
        int catId = getIntent().getIntExtra("cat_id", 0);
        if (catId == 0) {
            Toast.makeText(this, "Category not found", Toast.LENGTH_SHORT).show();
            moveToPrevious();
        } else {

            binding.title.setText("Update Category");
            loadData(catId);
            binding.btnActionCategory.setOnClickListener(v -> update(catId));

        }
    }

    private void update(int catId) {
        clearErrors();
        String name = String.valueOf(binding.etCategoryName.getText());
        int color = selectedColor[0];

        HashMap<String, String> map = validate(true);
        if (!map.isEmpty()) {
            binding.tvCategoryNameError.setText(map.get("name"));
            binding.tvCategoryNameError.setVisibility(VISIBLE);
        }else {
            Category category = CategoryService.getInstance(this).getById(catId);
        category.setName(name);
        category.setColor(color);
        CategoryService.getInstance(this).update(category);
        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        moveToPrevious();
        }


    }

    private void clearErrors() {
        binding.tvCategoryNameError.setVisibility(GONE);
    }

    private HashMap<String, String> validate(boolean isUpdate) {
        HashMap<String, String> map = new HashMap<>();
        String name = String.valueOf(binding.etCategoryName.getText());


        String nameValidation = validateName(name, isUpdate);

        if (nameValidation != null) map.put("name", nameValidation);

        return map;


    }

    private String validateName(String name, boolean isUpdate) {
        if (name == null || name.isEmpty()) {
            return "Name cannot be empty";
        } else if (name.length() > 20) {
            return "Name cannot be more than 20 characters";
        } else if (isUpdate) {
            if (CategoryService.getInstance(this).getByName(name) != null) {
                return "Category already exists";
            }
        }

        return null;
    }

    private void loadData(int catId) {
        Category category = CategoryService.getInstance(this).getById(catId);

        binding.etCategoryName.setText(category.getName());
        binding.viewSelectedColor.setBackgroundColor(category.getColor());


    }

    private void initCreate() {
        binding.title.setText("New Category");
        binding.btnDeleteCategory.setVisibility(GONE);
        binding.btnActionCategory.setOnClickListener(v -> save());
    }

    private void save() {
        clearErrors();
        HashMap<String, String> validate = validate(false);
        if (!validate.isEmpty()) {
            binding.tvCategoryNameError.setText(validate.get("name"));
            binding.tvCategoryNameError.setVisibility(VISIBLE);
        }else{
            Category c = new Category();
            c.setName(binding.etCategoryName.getText().toString().trim());
            c.setColor(selectedColor[0]);
            c.setStatus(1);
            boolean save = CategoryService.getInstance(this).save(c);
            if (save) {
                Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                moveToPrevious();
            } else {
                Toast.makeText(this, "not saved", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void moveToPrevious() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    private boolean isValid() {
        return true;
    }

    int[] selectedColor = {Color.parseColor("#173600")};

    private void showColorModel() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(
                this,
                selectedColor[0],
                new AmbilWarnaDialog.OnAmbilWarnaListener() {

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        selectedColor[0] = color;
                        binding.viewSelectedColor.setBackgroundColor(color);
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }
                }
        );
        colorPicker.show();
    }
}
