package info.atalou.apps.myatapos.database.dao;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;

@Dao
public interface CustomerGroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroup(CustomerGroupEntity group);


    @Delete
    void deleteGroup(CustomerGroupEntity group);


    @Query("SELECT * FROM customer_groups WHERE id = :id")
    CustomerGroupEntity getGroupById(int id);

    @Query("SELECT * FROM customer_groups ORDER BY created_at DESC")
    LiveData<List<CustomerGroupEntity>> getAll();
}
