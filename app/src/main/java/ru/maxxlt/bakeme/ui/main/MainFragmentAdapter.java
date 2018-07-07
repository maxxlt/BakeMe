package ru.maxxlt.bakeme.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;

public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder> {
    private List<Baking> bakingList;
    private Context mContext;
    private OnTitleClickListener onTitleClickListener;

    //Interface credits: https://stackoverflow.com/questions/15444375/how-to-create-interface-between-fragment-and-adapter
    public interface OnTitleClickListener {
        void onTitleSelected(int position);
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.onTitleClickListener = onTitleClickListener;
    }

    public MainFragmentAdapter() {

    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setBakingList(List<Baking> bakingList) {
        this.bakingList = bakingList;
    }

    @NonNull
    @Override
    public MainFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_material, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainFragmentAdapter.ViewHolder viewHolder, int position) {
        Baking baking = bakingList.get(position);
        viewHolder.title.setText(baking.getName());
        viewHolder.servings.setText("Servings: " + Integer.toString(baking.getServings()));
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTitleClickListener.onTitleSelected(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bakingList != null)
            return bakingList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, servings;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_fragment_main);
            title = itemView.findViewById(R.id.title_tv);
            servings = itemView.findViewById(R.id.servings_tv);
        }
    }
}
