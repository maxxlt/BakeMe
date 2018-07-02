package ru.maxxlt.bakeme.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Steps;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<Steps> stepsList;

    public void setStepsList(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_detail_steps_material,
                viewGroup,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Steps step = stepsList.get(i);
        viewHolder.stepID.setText(Integer.toString(step.getId()));
    }

    @Override
    public int getItemCount() {
        if (stepsList != null) {
            return stepsList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stepID;

        public ViewHolder(View itemView) {
            super(itemView);
            stepID = itemView.findViewById(R.id.id_step_tv);
        }
    }
}
