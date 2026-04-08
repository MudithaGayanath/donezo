package lk.jiat.donezo.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import lk.jiat.donezo.R;
import lk.jiat.donezo.service.CategoryService;
import lk.jiat.donezo.service.TaskService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static final String TAG = "CategoryAdapter";

    private List<CategoryService.CategoryAdapterData> list;

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryService.CategoryAdapterData data = list.get(position);
        holder.tvCategoryName.setText(data.getCategory().getName());

        holder.viewColorStrip.setBackgroundColor(data.getCategory().getColor());

        holder.viewInitialBg.setBackgroundColor(data.getCategory().getColor());

        holder.tvCategoryInitial.setText(String.valueOf(data.getCategory().getName().toCharArray()[0]));
        holder.tvCategoryInitial.setBackgroundColor(data.getCategory().getColor());


        TaskService.TaskInfoForCategory info = data.getInfo();
        if (info != null) {
            holder.tvTaskCountLabel.setText(String.valueOf(info.getTask() + " tasks"));
            holder.tvTaskCount.setText(String.valueOf(info.getRemaining()));
            holder.tvProgressLabel.setText(info.getCompletedPercentage()+"%");
            holder.progressBar.setProgress(info.getCompletedPercentage());

        } else {
            holder.tvTaskCountLabel.setText("0 tasks");
            holder.tvTaskCount.setText(String.valueOf(0));
            holder.tvProgressLabel.setText("0%");
            holder.progressBar.setProgress(0);
        }

        holder.cardCategory.setOnClickListener(v ->{
            data.showCategory(data.getCategory().id,v.getContext());
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardCategory;
        View viewColorStrip;
        View viewInitialBg;
        TextView tvCategoryInitial;
        TextView tvCategoryName;
        TextView tvTaskCountLabel;
        TextView tvTaskCount;
        TextView tvProgressLabel;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardCategory = itemView.findViewById(R.id.cardCategory);
            viewColorStrip = itemView.findViewById(R.id.viewColorStrip);
            viewInitialBg = itemView.findViewById(R.id.viewInitialBg);
            tvCategoryInitial = itemView.findViewById(R.id.tvCategoryInitial);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvTaskCountLabel = itemView.findViewById(R.id.tvTaskCountLabel);
            tvTaskCount = itemView.findViewById(R.id.tvTaskCount);
            tvProgressLabel = itemView.findViewById(R.id.tvProgressLabel);
            progressBar = itemView.findViewById(R.id.icProgressBar);

        }
    }
}
