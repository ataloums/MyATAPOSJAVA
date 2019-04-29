package info.atalou.apps.myatapos.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.ui.fragment.CatalogFragment;
import info.atalou.apps.myatapos.ui.fragment.DashboardFragment;
import info.atalou.apps.myatapos.ui.fragment.MoreFragment;
import info.atalou.apps.myatapos.ui.fragment.PeopleFragment;
import info.atalou.apps.myatapos.ui.fragment.ReportFragment;

public class MainActivity extends AppCompatActivity {


    private ActionBar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    toolbar.setTitle(R.string.title_dashboard);
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_catalog:
                    toolbar.setTitle(R.string.title_catalog);
                    fragment = new CatalogFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_people:
                    toolbar.setTitle(R.string.title_people);
                    fragment = new PeopleFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_report:
                    toolbar.setTitle(R.string.title_report);
                    fragment = new ReportFragment();
                    loadFragment(fragment);
                    return true;
                    case R.id.navigation_more:
                    toolbar.setTitle(R.string.title_more);
                    fragment = new MoreFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        //CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        //layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        toolbar.setTitle(R.string.title_dashboard);
        loadFragment(new DashboardFragment());
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
