package info.atalou.apps.myatapos.ui.activity.people;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;
import info.atalou.apps.myatapos.ui.activity.MainActivity;
import info.atalou.apps.myatapos.ui.adapter.CustomerGroupAdapter;
import info.atalou.apps.myatapos.viewmodel.people.CustomerGroupListViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CATEGORY_ID_KEY;

public class CustomerGroupListActivity extends AppCompatActivity implements CustomerGroupAdapter.OnCustomerGroupListener{

    RecyclerView mRecyclerView;
    private List<CustomerGroupEntity> noteData = new ArrayList<>();
    private CustomerGroupAdapter noteAdapter;
    private CustomerGroupListViewModel mViewModel;
    private ActionBar toolbar;

    CustomerGroupAdapter.OnCustomerGroupListener onCustomerGroupListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_group_list);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.customer_group));

        onCustomerGroupListener = this;

        initRecyclerView();
        initViewModel();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerGroupEditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            //backToActivity();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void backToActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        finish();
    }

    private void backTo(){
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(parentIntent);
        finish();

    }

    private void initViewModel() {
        final Observer<List<CustomerGroupEntity>> notesObserver =
                new Observer<List<CustomerGroupEntity>>() {
                    @Override
                    public void onChanged(List<CustomerGroupEntity> noteEntities) {
                        noteData.clear();
                        noteData.addAll(noteEntities);
                        if (noteAdapter == null){
                            noteAdapter = new CustomerGroupAdapter(noteData, CustomerGroupListActivity.this, onCustomerGroupListener);
                            mRecyclerView.setAdapter(noteAdapter);
                        }else{
                            noteAdapter.notifyDataSetChanged();
                        }

                    }
                };
        mViewModel = ViewModelProviders.of(this)
                .get(CustomerGroupListViewModel.class);

        mViewModel.mGroups.observe(this,notesObserver);
    }

    public void initRecyclerView(){
        mRecyclerView = findViewById(R.id.client_group_recycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(divider);

    }

    @Override
    public void onCustomerGroupClick(int position) {
        Intent intent = new Intent(this, CustomerGroupEditorActivity.class);
        intent.putExtra(CATEGORY_ID_KEY, noteData.get(position).getId());
        startActivity(intent);
    }
}
