package com.j7arsen.mvvmexampleproject.main.peopledetail.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.j7arsen.mvvmexampleproject.base.viewmodel.BaseViewModel;
import com.j7arsen.mvvmexampleproject.dataclasses.People;
import com.j7arsen.mvvmexampleproject.di.qualifier.ActivityContext;
import com.j7arsen.mvvmexampleproject.di.scopes.PerActivity;
import com.j7arsen.mvvmexampleproject.main.peopledetail.IPeopleDetailContract;
import com.j7arsen.mvvmexampleproject.service.LogService;

import org.parceler.Parcels;

import javax.inject.Inject;

/**
 * Created by j7ars on 25.05.2017.
 */
@PerActivity
public class PeopleDetailViewModel extends BaseViewModel<IPeopleDetailContract.View> implements IPeopleDetailContract.ViewModel {

    private static final String SAVE_PEOPLE = "PeopleDetailViewModel.SAVE_PEOPLE";

    private People mCurrentPeople;

    @Inject
    @ActivityContext
    Context mContext;

    @Inject
    public PeopleDetailViewModel() {
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);
        outState.putParcelable(SAVE_PEOPLE, Parcels.wrap(mCurrentPeople));
    }

    @Override
    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.restoreInstanceState(savedInstanceState);
        mCurrentPeople = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_PEOPLE));
    }

    public void setPeople(People people) {
        this.mCurrentPeople = people;
        notifyChange();
    }

    @Override
    public void onClickFab(View view) {
        LogService.startService(mContext);
    }

    //properties
    public String getFullUserName() {
        return mCurrentPeople.getName().getTitle() + "." + mCurrentPeople.getName().getFirst() + " " + mCurrentPeople.getName().getLast();
    }

    public String getUserName() {
        return getFullUserName();
    }

    public String getEmail() {
        return mCurrentPeople.getEmail();
    }

    public int getEmailVisibility() {
        return mCurrentPeople.getEmail() != null ? View.VISIBLE : View.GONE;
    }

    public String getCell() {
        return mCurrentPeople.getCell();
    }

    public String getPictureProfile() {
        return mCurrentPeople.getPicture().getLarge();
    }

    public String getAddress() {
        return mCurrentPeople.getLocation().getStreet()
                + " "
                + mCurrentPeople.getLocation().getCity()
                + " "
                + mCurrentPeople.getLocation().getState();
    }

    public String getGender() {
        return mCurrentPeople.getGender();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

}
