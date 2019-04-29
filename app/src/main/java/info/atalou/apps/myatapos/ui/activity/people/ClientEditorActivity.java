package info.atalou.apps.myatapos.ui.activity.people;

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
import info.atalou.apps.myatapos.database.entity.ClientEntity;
import info.atalou.apps.myatapos.viewmodel.people.ClientEditorViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CLIENT_ID_KEY;
import static info.atalou.apps.myatapos.utility.Constants.EDIT_CLIENT_KEY;

public class ClientEditorActivity extends AppCompatActivity {

    private ClientEditorViewModel mViewModel;
    private TextView mName, mEmail, mPhone ;
    private boolean mNewNote, mEditing;
    private ActionBar toolbar;
    private AppCompatButton delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setHomeAsUpIndicator(R.drawable.ic_close);

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPhone = findViewById(R.id.phone);
        delete = findViewById(R.id.delete);

        delete.setVisibility(View.GONE);

        if (savedInstanceState != null){
            mEditing = savedInstanceState.getBoolean(EDIT_CLIENT_KEY);
        }

        initViewModel();
        initOnClick();
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
            navigateUpTo(new Intent(this, ClientListActivity.class));
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
        String email = mEmail.getText().toString();
        String phone = mPhone.getText().toString();


        if ((TextUtils.isEmpty(name.trim()) || TextUtils.isEmpty(name.trim()))){
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }else{
            mViewModel.saveClient(name,email, phone);
            finish();
        }

    }

    private void initOnClick(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClient();
            }
        });

    }

    private void deleteClient() {
        mViewModel.deleteClient();
        finish();
    }



    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(ClientEditorViewModel.class);

        mViewModel.mLiveClient.observe(this, new Observer<ClientEntity>() {
            @Override
            public void onChanged(ClientEntity clientEntity) {
                if (clientEntity != null && !mEditing){
                    mName.setText(clientEntity.getName());
                    mEmail.setText(clientEntity.getEmail());
                    mPhone.setText(clientEntity.getPhone());
                    delete.setVisibility(View.VISIBLE);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            setTitle(R.string.new_client);
            mNewNote = true;
        }else {
            setTitle(R.string.edit_client);
            int noteId = extras.getInt(CLIENT_ID_KEY);
            mViewModel.loadData(noteId);
        }
    }
}
