package ru.maxxlt.bakeme.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.maxxlt.bakeme.BR;

public class BakeryAdapter extends RecyclerView.Adapter {


    //Reference Link: https://github.com/hitherejoe/MVVM_Hacker_News/blob/master/app/src/main/java/com/hitherejoe/mvvm_hackernews/view/adapter/CommentAdapter.java
    //https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4

    //List of any objects for reusing purposes
    private List<?> stuff = new ArrayList<>();
    private int layoutID;

    //Setters for our local data
    public void setStuff(List<?> stuff) {
        this.stuff = stuff;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public BakeryAdapter(int layoutID) {
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding mBinding = DataBindingUtil.inflate(layoutInflater,layoutID,parent,false);
        return new BakeryViewHolder(mBinding,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BakeryViewHolder mBakeryHolder = (BakeryViewHolder) viewHolder;
        mBakeryHolder.bind(stuff.get(i));

    }

    @Override
    public int getItemCount() {
        return stuff.size();
    }

    //Extending ViewHolder to enable proper binding function
    public static class BakeryViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding mBinding;
        Context mContext;

        public BakeryViewHolder(ViewDataBinding mBinding, Context mContext) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
            this.mContext = mContext;
        }

        public void bind(Object bakery){
            mBinding.setVariable(BR.bakery,bakery);
            mBinding.executePendingBindings();
        }

    }

}
