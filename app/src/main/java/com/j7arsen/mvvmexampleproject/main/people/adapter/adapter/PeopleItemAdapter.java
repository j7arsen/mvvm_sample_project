package com.j7arsen.mvvmexampleproject.main.people.adapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j7arsen.mvvmexampleproject.R;
import com.j7arsen.mvvmexampleproject.base.BaseViewHolder;
import com.j7arsen.mvvmexampleproject.databinding.ItemPeopleBinding;
import com.j7arsen.mvvmexampleproject.dataclasses.People;
import com.j7arsen.mvvmexampleproject.di.scopes.PerActivity;
import com.j7arsen.mvvmexampleproject.main.people.adapter.IPeopleItemContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by j7ars on 25.05.2017.
 */
@PerActivity
public class PeopleItemAdapter extends RecyclerView.Adapter<PeopleItemAdapter.PeopleItemViewHolder>{

    private List<People> mPeopleList;

    private OnItemClickListener mOnItemClickListener;

    @Inject
    public PeopleItemAdapter(){
        mPeopleList = new ArrayList<>();
    }

    public void setData(List<People> peopleList){
        this.mPeopleList = peopleList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PeopleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_people, parent, false);

        return new PeopleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PeopleItemViewHolder holder, int position) {
        holder.viewModel().updatePeople(mPeopleList.get(position), position);
        holder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPeopleList == null ? 0 : mPeopleList.size();
    }

    public class PeopleItemViewHolder extends BaseViewHolder<ItemPeopleBinding, IPeopleItemContract.ViewModel> implements IPeopleItemContract.View {

        public PeopleItemViewHolder(View v) {
            super(v);
            viewHolderComponent().inject(this);
            bindContentView(v);
        }

        @Override
        public void openDetailScreen(int position) {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(mPeopleList.get(position));
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(People people);
    }
}
