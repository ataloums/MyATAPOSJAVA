package info.atalou.apps.myatapos.viewmodel.catalog;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;
import info.atalou.apps.myatapos.database.repository.CategoryRepository;

public class CategoryListViewModel extends AndroidViewModel {

    public LiveData<List<CategoryEntity>> mCategories;
    private CategoryRepository mRepository;

    public CategoryListViewModel(@NonNull Application application) {
        super(application);

        mRepository = CategoryRepository.getInstance(application.getApplicationContext());
        mCategories = mRepository.mCategories;
    }
}
