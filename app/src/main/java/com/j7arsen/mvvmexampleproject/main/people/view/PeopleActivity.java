package com.j7arsen.mvvmexampleproject.main.people.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.j7arsen.mvvmexampleproject.R;
import com.j7arsen.mvvmexampleproject.base.BaseActivity;
import com.j7arsen.mvvmexampleproject.databinding.ActivityPeopleBinding;
import com.j7arsen.mvvmexampleproject.dataclasses.People;
import com.j7arsen.mvvmexampleproject.main.people.IPeopleContract;
import com.j7arsen.mvvmexampleproject.main.people.adapter.adapter.PeopleItemAdapter;
import com.j7arsen.mvvmexampleproject.main.peopledetail.view.PeopleDetailActivity;

import javax.inject.Inject;

/**
 * Created by j7ars on 25.05.2017.
 */

public class PeopleActivity extends BaseActivity<ActivityPeopleBinding, IPeopleContract.ViewModel> implements IPeopleContract.View, PeopleItemAdapter.OnItemClickListener {

    @Inject
    PeopleItemAdapter mPeopleItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(savedInstanceState, R.layout.activity_people);

        setSupportActionBar(mBinding.toolbar);
        initAdapter();
        setListeners();

    }

    private void initAdapter() {
        mBinding.listPeople.setLayoutManager(new LinearLayoutManager(this));
        mBinding.listPeople.setAdapter(mPeopleItemAdapter);
    }

    private void setListeners() {
        mPeopleItemAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void setupComponent() {
        activityComponent().inject(this);
    }

    @Override
    public void onItemClick(People people) {
        PeopleDetailActivity.startActivity(this, people);
    }
}
