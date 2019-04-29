package info.atalou.apps.myatapos.ui.activity.more;

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
import info.atalou.apps.myatapos.database.entity.TaxEntity;
import info.atalou.apps.myatapos.ui.activity.MainActivity;
import info.atalou.apps.myatapos.ui.adapter.TaxAdapter;
import info.atalou.apps.myatapos.viewmodel.more.TaxListViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CATEGORY_ID_KEY;

public class TaxListActivity extends AppCompatActivity implements TaxAdapter.OnTaxListener{

    RecyclerView mRecyclerView;
    private List<TaxEntity> noteData = new ArrayList<>();
    private TaxAdapter noteAdapter;
    private TaxListViewModel mViewModel;
    private ActionBar toolbar;

    TaxAdapter.OnTaxListener onTaxListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_list);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.all_taxes));

        onTaxListener = this;

        initRecyclerView();
        initViewModel();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaxEditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            backToActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void backToActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        final Observer<List<TaxEntity>> notesObserver =
                new Observer<List<TaxEntity>>() {
                    @Override
                    public void onChanged(List<TaxEntity> noteEntities) {
                        noteData.clear();
                        noteData.addAll(noteEntities);
                        if (noteAdapter == null){
                            noteAdapter = new TaxAdapter(noteData, TaxListActivity.this, onTaxListener);
                            mRecyclerView.setAdapter(noteAdapter);
                        }else{
                            noteAdapter.notifyDataSetChanged();
                        }

                    }
                };
        mViewModel = ViewModelProviders.of(this)
                .get(TaxListViewModel.class);

        mViewModel.mTaxes.observe(this,notesObserver);
    }

    public void initRecyclerView(){
        mRecyclerView = findViewById(R.id.tax_recycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(divider);

    }

    @Override
    public void onTaxClick(int position) {
       // Toast.makeText(this, "Element ID :"+position, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TaxEditorActivity.class);
        intent.putExtra(CATEGORY_ID_KEY, noteData.get(position).getId());
        startActivity(intent);
    }
}
