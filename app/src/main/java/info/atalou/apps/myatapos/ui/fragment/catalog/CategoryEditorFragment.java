package info.atalou.apps.myatapos.ui.fragment.catalog;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.viewmodel.catalog.CategoryListViewModel;

import static info.atalou.apps.myatapos.utility.Constants.CATEGORY_ID_KEY;

public class CategoryEditorFragment extends Fragment {

    private CategoryListViewModel mViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(CATEGORY_ID_KEY)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

           // mItem = DummyContent.ITEM_MAP.get(getArguments().getString(CATEGORY_ID_KEY));

            Activity activity = this.getActivity();
            //CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
           // if (appBarLayout != null) {
               // appBarLayout.setTitle(mItem.content);
           // }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_category_editor, container, false);

        // Show the dummy content as text in a TextView.
       // if (mItem != null) {
        //    ((TextView) rootView.findViewById(R.id.tempcat_detail)).setText(mItem.details);
       // }

        return rootView;
    }
}
