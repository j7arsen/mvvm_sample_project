package com.j7arsen.mvvmexampleproject.main.peopledetail.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.j7arsen.mvvmexampleproject.R;
import com.j7arsen.mvvmexampleproject.base.BaseActivity;
import com.j7arsen.mvvmexampleproject.databinding.ActivityDetailBinding;
import com.j7arsen.mvvmexampleproject.dataclasses.People;
import com.j7arsen.mvvmexampleproject.di.components.ActivityComponent;
import com.j7arsen.mvvmexampleproject.main.peopledetail.IPeopleDetailContract;

import org.parceler.Parcels;

/**
 * Created by j7ars on 25.05.2017.
 */

public class PeopleDetailActivity extends BaseActivity<ActivityDetailBinding, IPeopleDetailContract.ViewModel> implements IPeopleDetailContract.View{

    private static final String PEOPLE_DETAIL = "PeopleDetailActivity.PEOPLE_DETAIL";

    public static void startActivity(Activity activity, People people) {
        Intent intent = new Intent(activity, PeopleDetailActivity.class);
        intent.putExtra(PEOPLE_DETAIL, Parcels.wrap(people));
        activity.startActivity(intent);
    }

    public static void startActivity(Fragment fragment, Activity activity, People people) {
        Intent intent = new Intent(activity, PeopleDetailActivity.class);
        intent.putExtra(PEOPLE_DETAIL, Parcels.wrap(people));
        fragment.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(savedInstanceState, R.layout.activity_detail);
        getExtras();
    }

    private void getExtras(){
        if(getIntent().getExtras() != null){
            mViewModel.setPeople(Parcels.unwrap(getIntent().getExtras().getParcelable(PEOPLE_DETAIL)));
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
