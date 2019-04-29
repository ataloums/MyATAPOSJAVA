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
import info.atalou.apps.myatapos.ui.activity.catalog.CategoryListActivity;

public class CatalogFragment extends Fragment {

    LinearLayout product;
    LinearLayout categories;
    LinearLayout brands;
    LinearLayout modifiers;

    public CatalogFragment() {
        // Required empty public constructor
    }

    public static CatalogFragment newInstance(String param1, String param2) {
        CatalogFragment fragment = new CatalogFragment();
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
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);


        product = view.findViewById(R.id.products);
        categories = view.findViewById(R.id.categories);
        brands = view.findViewById(R.id.brands);
        modifiers = view.findViewById(R.id.modifiers);


        initOnClick();

        return  view;
    }


    private void initOnClick(){
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProducts();
            }
        });
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCategories();
            }
        });
        brands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBrands();
            }
        });
        modifiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchModifiers();
            }
        });
    }



    private void launchCategories() {
        // launch new intent instead of loading fragment
        startActivity(new Intent(getActivity(), CategoryListActivity.class));
    }

    private void launchProducts() {
        Toast.makeText(getActivity(), "Products", Toast.LENGTH_LONG).show();
    }

    private void launchBrands() {
        Toast.makeText(getActivity(), "launchBrands", Toast.LENGTH_LONG).show();
    }

    private void launchModifiers() {
        Toast.makeText(getActivity(), "launchModifiers", Toast.LENGTH_LONG).show();
    }
}
