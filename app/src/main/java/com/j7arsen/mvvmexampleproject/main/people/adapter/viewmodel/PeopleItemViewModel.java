package com.j7arsen.mvvmexampleproject.main.people.adapter.viewmodel;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.j7arsen.mvvmexampleproject.base.viewmodel.BaseViewModel;
import com.j7arsen.mvvmexampleproject.dataclasses.People;
import com.j7arsen.mvvmexampleproject.di.scopes.PerViewHolder;
import com.j7arsen.mvvmexampleproject.main.people.adapter.IPeopleItemContract;

import javax.inject.Inject;

/**
 * Created by j7ars on 25.05.2017.
 */
@PerViewHolder
public class PeopleItemViewModel extends BaseViewModel<IPeopleItemContract.View> implements IPeopleItemContract.ViewModel{

    private People mCurrentPeople;
    private int mPosition;

    @Inject
    public PeopleItemViewModel(){
    }

    @Override
    public void updatePeople(People people, int position) {
        this.mCurrentPeople = people;
        this.mPosition = position;
        notifyChange();
    }

    @Override
    public void onItemClick(View view) {
        getView().openDetailScreen(mPosition);
        Log.i("Start activity", "Start Activity");
    }

    //properties
    @Override
    public String getFullName() {
        return mCurrentPeople.getName().getTitle() + "." + mCurrentPeople.getName().getFirst() + " " + mCurrentPeople.getName().getLast();
    }

    @Override
    public String getCell() {
        return mCurrentPeople.getCell();
    }

    @Override
    public String getMail() {
        return mCurrentPeople.getEmail();
    }

    @Override
    public String getPictureProfile() {
        return mCurrentPeople.getPicture().getMedium();
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
