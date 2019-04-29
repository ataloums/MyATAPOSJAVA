package info.atalou.apps.myatapos.ui.activity.more;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.TaxEntity;
import info.atalou.apps.myatapos.viewmodel.more.TaxEditorViewModel;

import static info.atalou.apps.myatapos.utility.Constants.EDIT_TAX_KEY;
import static info.atalou.apps.myatapos.utility.Constants.TAX_ID_KEY;

public class TaxEditorActivity extends AppCompatActivity  {
    private TaxEditorViewModel mViewModel;
    private TextView mName, mCode , mValue;
    private boolean mNewNote, mEditing;
    private ActionBar toolbar;
    private AppCompatButton delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_detail);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setHomeAsUpIndicator(R.drawable.ic_close);

        mName = findViewById(R.id.name);
        delete = findViewById(R.id.delete);
        mValue = findViewById(R.id.value);
        mCode = findViewById(R.id.code);

        delete.setVisibility(View.GONE);

        if (savedInstanceState != null){
            mEditing = savedInstanceState.getBoolean(EDIT_TAX_KEY);
        }


        initViewModel();
        initOnClick();
    }




    private void initOnClick(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTax();
            }
        });

    }




    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(TaxEditorViewModel.class);

        mViewModel.mLiveTax.observe(this, new Observer<TaxEntity>() {
            @Override
            public void onChanged(TaxEntity noteEntity) {
               if (noteEntity != null && !mEditing){
                    mName.setText(noteEntity.getName());
                    mCode.setText(noteEntity.getCode());
                    mValue.setText(""+noteEntity.getValue());
                    delete.setVisibility(View.VISIBLE);
               }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            setTitle(R.string.new_tax);
            mNewNote = true;
        }else {
            setTitle(R.string.edit_tax);
            int noteId = extras.getInt(TAX_ID_KEY);
            mViewModel.loadData(noteId);
        }
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
            navigateUpTo(new Intent(this, TaxListActivity.class));
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

    private void saveAndReturn() {


        String name = mName.getText().toString();
        String code = mCode.getText().toString();
        double value = Double.parseDouble(mValue.getText().toString());


        if ((TextUtils.isEmpty(name.trim()) || TextUtils.isEmpty(code.trim()))){
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }else{
            mViewModel.saveTax(name,code, value);
            finish();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDIT_TAX_KEY,true);
        super.onSaveInstanceState(outState);
    }

    private void deleteTax(){
        mViewModel.deleteTax();
        finish();
    }
}
