package info.atalou.apps.myatapos.ui.activity.people;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.RoleEntity;
import info.atalou.apps.myatapos.database.entity.UserEntity;
import info.atalou.apps.myatapos.viewmodel.people.UserEditorViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CLIENT_ID_KEY;
import static info.atalou.apps.myatapos.utility.Constants.EDIT_CLIENT_KEY;

public class UserEditorActivity extends AppCompatActivity {

    private UserEditorViewModel mViewModel;
    private TextView mName, mEmail, mPassword, mUsername, mConfirmed;
    private int roleid;
    private Spinner spinnerRole;
    private boolean mNewNote, mEditing;
    private AppCompatButton delete;

    private List<RoleEntity> mRoles;
    //private List<RoleEntity> mRoles = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setHomeAsUpIndicator(R.drawable.ic_close);

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mUsername = findViewById(R.id.username);
        delete = findViewById(R.id.delete);
        spinnerRole = findViewById(R.id.role);

        delete.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean(EDIT_CLIENT_KEY);
        }

        initViewModel();
       // addSampleRole();
        populateRoleSpinner();
        //listSpinnerTest();
        initSpinner();
        initOnClick();

    }

    private void initSpinner() {
        spinnerRole.setAdapter(roleSpinnerAdapter);
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(UserEditorActivity.this, ""+mRoles.get(i).getId(), Toast.LENGTH_LONG).show();
                roleid = mRoles.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigateUpTo(new Intent(this, UserListActivity.class));
            return true;
        } else if (item.getItemId() == R.id.navigation_add) {
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
        String email = mEmail.getText().toString();
        String username = mUsername.getText().toString();


        if ((TextUtils.isEmpty(name.trim()) || TextUtils.isEmpty(name.trim()))) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        } else {
            mViewModel.saveUser(name, email, username,roleid);
            finish();
        }

    }

    private void initOnClick() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });

    }

    private void deleteUser() {
        mViewModel.deleteUser();
        finish();
    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(UserEditorViewModel.class);

        mViewModel.mLiveUser.observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                if (userEntity != null && !mEditing) {
                    mName.setText(userEntity.getName());
                    mEmail.setText(userEntity.getEmail());
                    mUsername.setText(userEntity.getUsername());
                    delete.setVisibility(View.VISIBLE);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle(R.string.new_user);
            mNewNote = true;
        } else {
            setTitle(R.string.edit_user);
            int noteId = extras.getInt(CLIENT_ID_KEY);
            mViewModel.loadData(noteId);
        }
    }

//    private void addSampleRole() {
//        if(mViewModel.roleQty() == 0){
//            mViewModel.addSampleRole();
//            Toast.makeText(this, "Role 0: "+mViewModel.roleQty(), Toast.LENGTH_LONG).show();
//
//        }else{
//            Toast.makeText(this, "Role 2: "+mViewModel.roleQty(), Toast.LENGTH_LONG).show();
//        }
//    }


//    private void listSpinnerTest(){
//        //List<RoleEntity> languages = mViewModel.populateRoleSpinner();
//        List<RoleEntity> roles = new ArrayList<>();
//
//        roles.add(new RoleEntity(1,"Administrator",new Date()));
//        roles.add(new RoleEntity(2,"Cashier", new Date()));
//
//        Spinner spinner = findViewById(R.id.role);
//        // Pass your list as the third parameter. No need to convert it to List<String>
//        ArrayAdapter<RoleEntity> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, roles);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }


    private void populateRoleSpinner(){
        mRoles = new ArrayList<>();
        //mRoles = mViewModel.populateRoleSpinner();

        mRoles.add(new RoleEntity(1,"Administrator",new Date()));
        mRoles.add(new RoleEntity(2,"Cashier", new Date()));
    }

    private BaseAdapter roleSpinnerAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mRoles.size();
        }

        @Override
        public Object getItem(int position) {
            return mRoles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            RoleHolder holder;
            View roleView = convertView;

            if (roleView == null){
                roleView = getLayoutInflater().inflate(R.layout.row_role_spinner,parent,false);
                holder = new RoleHolder();
                holder.name = roleView.findViewById(R.id.role_name);

                roleView.setTag(holder);

            }else {
                holder = (RoleHolder)roleView.getTag();
            }

            RoleEntity role = mRoles.get(position);
            holder.name.setText(role.getName());


            return roleView;
        }

        class RoleHolder{
            private TextView name;
        }
    };
}
