package info.atalou.apps.myatapos.ui.activity.catalog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;
import info.atalou.apps.myatapos.viewmodel.catalog.CategoryEditorViewModel;
import petrov.kristiyan.colorpicker.ColorPicker;

import static info.atalou.apps.myatapos.utility.Constants.CATEGORY_ID_KEY;
import static info.atalou.apps.myatapos.utility.Constants.EDIT_CATEGORY_KEY;

public class CategoryEditorActivity extends AppCompatActivity  {
    private CategoryEditorViewModel mViewModel;
    private TextView mText, mCode ;
    private boolean mNewNote, mEditing;
    private ActionBar toolbar;
    private AppCompatButton delete,mColor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setHomeAsUpIndicator(R.drawable.ic_close);

        mText = findViewById(R.id.name);
        delete = findViewById(R.id.delete);
        mColor = findViewById(R.id.color);
        mCode = findViewById(R.id.code);

        delete.setVisibility(View.GONE);

        if (savedInstanceState != null){
            mEditing = savedInstanceState.getBoolean(EDIT_CATEGORY_KEY);
        }


        initViewModel();
        initOnClick();
    }




    private void initOnClick(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCategory();
            }
        });

        mColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseColor();
            }
        });
    }

    private void chooseColor() {
        final ColorPicker colorPicker = new ColorPicker(CategoryEditorActivity.this);
        ArrayList<String> colors = new ArrayList<>();
            colors.add("#f84c44");
            colors.add("#82B926");
            colors.add("#a276eb");
            colors.add("#6a3ab2");
            colors.add("#666666");
            colors.add("#FFFF00");
            colors.add("#3C8D2F");
            colors.add("#FA9F00");
            colors.add("#FF0000");


        colorPicker
                .setDefaultColorButton(Color.parseColor("#f84c44"))
                .setColors(colors)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        mColor.setBackgroundColor(color);
                    }

                    @Override
                    public void onCancel() {

                    }
                })
                .show();

    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(CategoryEditorViewModel.class);

        mViewModel.mLiveCategory.observe(this, new Observer<CategoryEntity>() {
            @Override
            public void onChanged(CategoryEntity noteEntity) {
               if (noteEntity != null && !mEditing){
                    mText.setText(noteEntity.getName());
                    mCode.setText(noteEntity.getCode());
                    mColor.setBackgroundColor(noteEntity.getColor());
                    delete.setVisibility(View.VISIBLE);
               }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            setTitle(R.string.new_category);
            mNewNote = true;
        }else {
            setTitle(R.string.edit_category);
            int noteId = extras.getInt(CATEGORY_ID_KEY);
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
            navigateUpTo(new Intent(this, CategoryListActivity.class));
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
        ColorDrawable buttonColor = (ColorDrawable) mColor.getBackground();
        int colorId = buttonColor.getColor();

        String name = mText.getText().toString();
        String code = mCode.getText().toString();


        if ((TextUtils.isEmpty(name.trim()) || TextUtils.isEmpty(code.trim()))){
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }else{
            mViewModel.saveCategory(name,code, colorId);
            finish();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDIT_CATEGORY_KEY,true);
        super.onSaveInstanceState(outState);
    }

    private void deleteCategory(){
        mViewModel.deleteCategory();
        finish();
    }
}
