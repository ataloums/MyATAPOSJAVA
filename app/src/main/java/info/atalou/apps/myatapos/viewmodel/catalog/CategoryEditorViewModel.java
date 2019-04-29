package info.atalou.apps.myatapos.viewmodel.catalog;

import android.app.Application;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;
import info.atalou.apps.myatapos.database.repository.CategoryRepository;

public class CategoryEditorViewModel extends AndroidViewModel {

    public MutableLiveData<CategoryEntity> mLiveCategory = new MutableLiveData<>();
    private CategoryRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CategoryEditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = CategoryRepository.getInstance(getApplication());

    }


    public void loadData(final int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                CategoryEntity note = mRepository.getCategoryById(noteId);
                mLiveCategory.postValue(note);
            }
        });
    }


    public void saveCategory(String noteText, String code, int color) {
        CategoryEntity category = mLiveCategory.getValue();

        if (category == null){
            if (TextUtils.isEmpty(noteText.trim())){
                return;
            }
            category = new CategoryEntity();
            category.setName(noteText.trim());
            category.setCode(code.trim());
            category.setColor(color);
            category.setCreated(new Date());
        }else{
            category.setCode(code.trim());
            category.setName(noteText.trim());
            category.setColor(color);
            category.setUpdated(new Date());
        }

       mRepository.insertCategory(category);

    }

    public void deleteCategory() {
        mRepository.deleteCategory(mLiveCategory.getValue());
    }

}
