package info.atalou.apps.myatapos.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import info.atalou.apps.myatapos.database.entity.CompanyEntity;

@Dao
public interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompany(CompanyEntity company);

    @Delete
    void deleteCompany(CompanyEntity company);


    @Query("SELECT * FROM companies WHERE id = :id")
    CompanyEntity getCompanyById(int id);


    @Query("SELECT * FROM companies")
    LiveData<CompanyEntity> getCompany();

}
