package info.atalou.apps.myatapos.ui.activity.people;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.UserEntity;
import info.atalou.apps.myatapos.ui.adapter.UserAdapter;
import info.atalou.apps.myatapos.viewmodel.people.UserListViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CLIENT_ID_KEY;

public class UserListActivity extends AppCompatActivity implements UserAdapter.OnUserListener {

    private ActionBar toolbar;

    RecyclerView mRecyclerView;
    private List<UserEntity> userData = new ArrayList<>();
    private UserAdapter userAdapter;
    private UserListViewModel mViewModel;

    UserAdapter.OnUserListener onUserListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.my_users));

        onUserListener = this;

        initRecyclerView();
        initViewModel();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserEditorActivity.class);
                startActivity(intent);
            }
        });


    }

    public void initRecyclerView(){
        mRecyclerView = findViewById(R.id.user_recycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(divider);

    }

    private void initViewModel() {
        final Observer<List<UserEntity>> notesObserver =
                new Observer<List<UserEntity>>() {
                    @Override
                    public void onChanged(List<UserEntity> noteEntities) {
                        userData.clear();
                        userData.addAll(noteEntities);
                        if (userAdapter == null){
                            userAdapter = new UserAdapter(userData, UserListActivity.this, onUserListener);
                            mRecyclerView.setAdapter(userAdapter);
                        }else{
                            userAdapter.notifyDataSetChanged();
                        }

                    }
                };
        mViewModel = ViewModelProviders.of(this)
                .get(UserListViewModel.class);

        mViewModel.mUsers.observe(this,notesObserver);
    }


    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, UserEditorActivity.class);
        intent.putExtra(CLIENT_ID_KEY, userData.get(position).getId());
        startActivity(intent);
    }
}
