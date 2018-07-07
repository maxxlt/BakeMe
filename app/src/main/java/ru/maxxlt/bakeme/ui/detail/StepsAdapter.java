package ru.maxxlt.bakeme.ui.detail;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Steps;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private List<Steps> stepsList;
    private OnStepClickListener onStepClickListener;

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    public void setOnStepClickListener(OnStepClickListener onStepClickListener) {
        this.onStepClickListener = onStepClickListener;
    }


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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Steps step = stepsList.get(i);
        viewHolder.stepID.setText("Step " + Integer.toString(step.getId()) +": "+ step.getShortDescription());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStepClickListener.onStepSelected(step.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (stepsList != null) {
            return stepsList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView stepID;

        public ViewHolder(View itemView) {
            super(itemView);
            stepID = itemView.findViewById(R.id.id_step_tv);
            cardView = itemView.findViewById(R.id.card_view_fragment_detail);
        }
    }
}
