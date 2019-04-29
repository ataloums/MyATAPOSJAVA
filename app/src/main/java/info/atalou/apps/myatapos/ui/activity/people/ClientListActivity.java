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
import info.atalou.apps.myatapos.database.entity.ClientEntity;
import info.atalou.apps.myatapos.ui.adapter.ClientAdapter;
import info.atalou.apps.myatapos.viewmodel.people.ClientListViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CLIENT_ID_KEY;

public class ClientListActivity extends AppCompatActivity implements ClientAdapter.OnClientListener {

    private ActionBar toolbar;

    RecyclerView mRecyclerView;
    private List<ClientEntity> clientData = new ArrayList<>();
    private ClientAdapter clientAdapter;
    private ClientListViewModel mViewModel;

    ClientAdapter.OnClientListener onClientListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.my_clients));

        onClientListener = this;

        initRecyclerView();
        initViewModel();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClientEditorActivity.class);
                startActivity(intent);
            }
        });


    }

    public void initRecyclerView(){
        mRecyclerView = findViewById(R.id.client_recycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(divider);

    }

    private void initViewModel() {
        final Observer<List<ClientEntity>> notesObserver =
                new Observer<List<ClientEntity>>() {
                    @Override
                    public void onChanged(List<ClientEntity> noteEntities) {
                        clientData.clear();
                        clientData.addAll(noteEntities);
                        if (clientAdapter == null){
                            clientAdapter = new ClientAdapter(clientData,ClientListActivity.this, onClientListener);
                            mRecyclerView.setAdapter(clientAdapter);
                        }else{
                            clientAdapter.notifyDataSetChanged();
                        }

                    }
                };
        mViewModel = ViewModelProviders.of(this)
                .get(ClientListViewModel.class);

        mViewModel.mClients.observe(this,notesObserver);
    }




    @Override
    public void onClientClick(int position) {
        Intent intent = new Intent(this, ClientEditorActivity.class);
        intent.putExtra(CLIENT_ID_KEY, clientData.get(position).getId());
        startActivity(intent);
    }
}
