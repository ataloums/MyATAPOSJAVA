package info.atalou.apps.myatapos.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import info.atalou.apps.myatapos.database.entity.TaxEntity;

@Dao
public interface TaxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTax(TaxEntity tax);


    @Delete
    void deleteTax(TaxEntity tax);


    @Query("SELECT * FROM taxes WHERE id = :id")
    TaxEntity getTaxById(int id);

    @Query("SELECT * FROM taxes ORDER BY created_at DESC")
    LiveData<List<TaxEntity>> getAll();


}
