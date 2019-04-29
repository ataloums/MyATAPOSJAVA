package info.atalou.apps.myatapos.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.ui.activity.AboutActivity;
import info.atalou.apps.myatapos.ui.activity.more.CompanyDetailsActivity;
import info.atalou.apps.myatapos.ui.activity.more.TaxListActivity;

public class MoreFragment extends Fragment {

    LinearLayout company, tax, support;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);


        company = view.findViewById(R.id.company);
        tax = view.findViewById(R.id.taxes);
        support = view.findViewById(R.id.support);



        initOnClick();
        return  view;
    }


    private void initOnClick(){
        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCompany();
            }

        });
        tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTax();
            }

        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSupport();
            }

        });
    }

    private void launchCompany() {
        startActivity(new Intent(getActivity(), CompanyDetailsActivity.class));
    }


    private void launchTax() {
        startActivity(new Intent(getActivity(), TaxListActivity.class));
    }

    private void launchSupport() {
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }
}
