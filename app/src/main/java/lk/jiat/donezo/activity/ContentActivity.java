package lk.jiat.donezo.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;

import lk.jiat.donezo.R;
import lk.jiat.donezo.databinding.ActivityContentBinding;
import lk.jiat.donezo.databinding.ActivityMainBinding;
import lk.jiat.donezo.fragment.CategoryFragment;
import lk.jiat.donezo.fragment.TaskFragment;

public class ContentActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
private ActivityContentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setFragment(new TaskFragment());
        binding.toolBar.setTitle("Task");
        initNavigation();

    }

    private void initNavigation() {
        binding.navigation.setOnItemSelectedListener(this);
    }

    private void setFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainerView.getId(), f).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Menu menu = binding.navigation.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setChecked(false);
        }
            menuItem.setChecked(true);

        if(menuItem.getItemId() == R.id.navigation_categories){
            setFragment(new CategoryFragment());
            binding.toolBar.setTitle("Categories");
        }else if(menuItem.getItemId()==R.id.navigation_tasks){
            setFragment(new TaskFragment());
            binding.toolBar.setTitle("Task");
        }



        return false;
    }
}