package ru.maxxlt.bakeme.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.maxxlt.bakeme.R;
import ru.maxxlt.bakeme.data.Baking;

public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder> {
    private List<Baking> bakings;

    public List<Baking> getBakings() {
        return bakings;
    }

    public void setBakings(List<Baking> bakings) {
        this.bakings = bakings;
        notifyDataSetChanged();
    }

    public MainFragmentAdapter(List<Baking> bakings) {
        this.bakings = bakings;
    }

    public MainFragmentAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_material,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(bakings.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return bakings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        public ViewHolder (View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_fragment_main);
            textView = itemView.findViewById(R.id.title_tv);
        }
    }
}
