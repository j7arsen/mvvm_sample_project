package com.j7arsen.mvvmexampleproject.main.people.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.j7arsen.mvvmexampleproject.R;
import com.j7arsen.mvvmexampleproject.base.viewmodel.BaseAdapterMvvmViewModel;
import com.j7arsen.mvvmexampleproject.base.viewmodel.BaseViewModel;
import com.j7arsen.mvvmexampleproject.dataclasses.People;
import com.j7arsen.mvvmexampleproject.di.qualifier.ActivityContext;
import com.j7arsen.mvvmexampleproject.di.scopes.PerActivity;
import com.j7arsen.mvvmexampleproject.main.people.IPeopleContract;
import com.j7arsen.mvvmexampleproject.main.people.adapter.adapter.PeopleItemAdapter;
import com.j7arsen.mvvmexampleproject.network.response.PeopleResponse;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by j7ars on 25.05.2017.
 */
@PerActivity
public class PeopleViewModel extends BaseViewModel<IPeopleContract.View> implements IPeopleContract.ViewModel, BaseAdapterMvvmViewModel<IPeopleContract.View> {

    private static final String SAVE_PEOPLE_LIST = "PeopleViewModel.SAVE_PEOPLE_LIST";

    private ObservableInt mPeopleProgress;
    private ObservableInt mPeopleRecycler;
    private ObservableInt mPeopleLabel;
    private ObservableField<String> mMessageLabel;

    private List<People> mPeopleList;

    private Disposable mDisposable = Disposables.empty();

    private Context mContext;

    private PeopleItemAdapter mPeopleItemAdapter;

    @Inject
    public PeopleViewModel(@ActivityContext Context context, PeopleItemAdapter peopleItemAdapter) {
        this.mContext = context;
        this.mPeopleItemAdapter = peopleItemAdapter;
        mPeopleProgress = new ObservableInt(View.GONE);
        mPeopleRecycler = new ObservableInt(View.GONE);
        mPeopleLabel = new ObservableInt(View.VISIBLE);
        mMessageLabel = new ObservableField<>(mContext.getString(R.string.people_load_error));
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return mPeopleItemAdapter;
    }

    @Override
    public void onClickFab(View view) {
        startLoading();
        fetchPeople();
    }

    private void startLoading() {
        mPeopleLabel.set(View.GONE);
        mPeopleRecycler.set(View.GONE);
        mPeopleProgress.set(View.VISIBLE);
    }

    private void completeLoading() {
        mPeopleProgress.set(View.GONE);
        mPeopleLabel.set(View.GONE);
        mPeopleRecycler.set(View.VISIBLE);
    }

    private void errorLoading() {
        mPeopleProgress.set(View.GONE);
        mPeopleLabel.set(View.VISIBLE);
        mPeopleRecycler.set(View.GONE);
        mMessageLabel.set(mContext.getString(R.string.people_load_error));
    }

    private void fetchPeople() {
        mDisposable = mDataManager.fetchPeople().subscribe(s -> successFetchPeople(s), this::onError);
        addDisposable(mDisposable);
    }

    private void successFetchPeople(PeopleResponse peopleResponse) {
        undisposable(mDisposable);
        Log.i("Fetch People", "Fetch people");
        completeLoading();
        mPeopleList = peopleResponse.getPeopleList();
        mPeopleItemAdapter.setData(mPeopleList);
    }

    private void onError(Throwable t) {
        undisposable(mDisposable);
        errorLoading();
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);
        outState.putParcelable(SAVE_PEOPLE_LIST, Parcels.wrap(mPeopleList));
    }

    @Override
    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.restoreInstanceState(savedInstanceState);
        mPeopleList = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_PEOPLE_LIST));
    }

    //properties

    @Override
    public ObservableInt getPeopleProgress() {
        return mPeopleProgress;
    }

    @Override
    public ObservableInt getPeopleRecycler() {
        return mPeopleRecycler;
    }

    @Override
    public ObservableInt getPeopleLabel() {
        return mPeopleLabel;
    }

    @Override
    public ObservableField<String> getMessageLabel() {
        return mMessageLabel;
    }
}
