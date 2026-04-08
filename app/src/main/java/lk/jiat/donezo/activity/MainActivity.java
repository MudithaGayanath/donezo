package lk.jiat.donezo.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import lk.jiat.donezo.R;
import lk.jiat.donezo.databinding.ActivityMainBinding;
import lk.jiat.donezo.service.PriorityService;
import lk.jiat.donezo.service.TaskStatusService;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        initDatabaseDefaultValues();
        startActivity(new Intent(this,ContentActivity.class));
    }

    private void initDatabaseDefaultValues() {
        PriorityService.getInstance(this);
        TaskStatusService.getInstance(this);
    }
}