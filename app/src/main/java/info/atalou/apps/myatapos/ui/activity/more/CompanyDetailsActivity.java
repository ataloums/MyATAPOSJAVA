package info.atalou.apps.myatapos.ui.activity.more;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.CompanyEntity;
import info.atalou.apps.myatapos.viewmodel.more.CompanyEditorViewModel;

import static info.atalou.apps.myatapos.utility.Constants.EDIT_COMPANY_KEY;

public class CompanyDetailsActivity extends AppCompatActivity {

    ActionBar toolbar;
    private CompanyEditorViewModel mViewModel;
    private boolean mNewNote, mEditing;
    private TextView mText, mNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_company_editor);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setHomeAsUpIndicator(R.drawable.ic_close);
        // load the store fragment by default
        toolbar.setTitle(R.string.company);


        mText = findViewById(R.id.name);
        mNumber = findViewById(R.id.number);



        if (savedInstanceState != null){
            mEditing = savedInstanceState.getBoolean(EDIT_COMPANY_KEY);
        }

        initViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            navigateUpTo(new Intent(this, CompanyDetailsActivity.class));
            return true;
        }else if (item.getItemId() == R.id.navigation_add){
            saveAndReturn();
            finish();

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDIT_COMPANY_KEY,true);
        super.onSaveInstanceState(outState);
    }


    private void saveAndReturn() {

        String name = mText.getText().toString();


        if (TextUtils.isEmpty(name.trim())){
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }else{
            mViewModel.saveCompany(name);
            finish();
        }

    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CompanyEditorViewModel.class);

        mViewModel.mLiveCompany.observe(this, new Observer<CompanyEntity>() {
            @Override
            public void onChanged(CompanyEntity companyEntity) {
                if (companyEntity != null && !mEditing){
                    mText.setText(companyEntity.getName());
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            setTitle(R.string.new_category);
            mNewNote = true;
        }else {
            setTitle(R.string.edit_category);
            int noteId = extras.getInt(EDIT_COMPANY_KEY);
            mViewModel.loadData(noteId);
        }
    }


}
