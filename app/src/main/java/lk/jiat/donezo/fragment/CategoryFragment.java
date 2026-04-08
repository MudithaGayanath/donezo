package lk.jiat.donezo.fragment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import lk.jiat.donezo.activity.CategoryActivity;
import lk.jiat.donezo.adapter.CategoryAdapter;
import lk.jiat.donezo.databinding.FragmentCategoryBinding;
import lk.jiat.donezo.entity.Category;
import lk.jiat.donezo.service.CategoryService;
import lk.jiat.donezo.service.TaskService;


public class CategoryFragment extends Fragment {

private FragmentCategoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.btnAddCategory.setOnClickListener(v -> moveCategoryActivity());
        binding.btnCreateFirst.setOnClickListener(v -> moveCategoryActivity());

        loadCategories();
    }

    private void moveCategoryActivity() {
        Intent intent = new Intent(requireContext().getApplicationContext(), CategoryActivity.class);
        intent.putExtra("is_update",false);

        requireContext().startActivity(intent);
    }

    private void loadCategories() {
        CategoryService service = CategoryService.getInstance(requireContext().getApplicationContext());
        List<Category> list = service.loadCategories();
        if(list.isEmpty()){

            showEmptyState();

        }else{
            showList(list);
        }


    }

    private void showList(List<Category> list) {
        CategoryService service = CategoryService.getInstance(requireContext().getApplicationContext());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(new CategoryAdapter(service.setDataIntoAdapter(list)));

        hideEmptyState();
        stopAnimation();
        binding.recyclerView.setVisibility(VISIBLE);
    }

    private void showEmptyState() {
        stopAnimation();
        binding.layoutEmptyState.setVisibility(VISIBLE);
    }
    private void hideEmptyState() {
        binding.layoutEmptyState.setVisibility(GONE);
    }

    private void stopAnimation() {
        binding.layoutLoading.setVisibility(GONE);
    }
}