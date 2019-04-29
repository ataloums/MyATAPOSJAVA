package info.atalou.apps.myatapos.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.ui.activity.people.ClientListActivity;
import info.atalou.apps.myatapos.ui.activity.people.CustomerGroupListActivity;
import info.atalou.apps.myatapos.ui.activity.people.UserListActivity;

public class PeopleFragment extends Fragment {

    LinearLayout users, customer_group,clients,suppliers;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public static PeopleFragment newInstance(String param1, String param2) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);


        users = view.findViewById(R.id.employee);
        clients = view.findViewById(R.id.customer);
        suppliers = view.findViewById(R.id.supplier);
        customer_group = view.findViewById(R.id.customer_group);


        initOnClick();

        return  view;
    }


    private void initOnClick(){
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchUsers();
            }
        });
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchClients();
            }
        });
        suppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSuppliers();
            }
        });
        customer_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCustomerGroup();
            }
        });
    }



    private void launchClients() {
        // launch new intent instead of loading fragment
        startActivity(new Intent(getActivity(), ClientListActivity.class));
    }

    private void launchUsers() {
        startActivity(new Intent(getActivity(), UserListActivity.class));
    }

    private void launchSuppliers() {
        Toast.makeText(getActivity(), "Suppliers", Toast.LENGTH_LONG).show();
    }

    private void launchCustomerGroup() {
        startActivity(new Intent(getActivity(), CustomerGroupListActivity.class));
    }


}
