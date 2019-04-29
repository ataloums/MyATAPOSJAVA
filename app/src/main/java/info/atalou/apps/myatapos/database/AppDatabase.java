package info.atalou.apps.myatapos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import info.atalou.apps.myatapos.database.converter.DateConverter;
import info.atalou.apps.myatapos.database.dao.CategoryDao;
import info.atalou.apps.myatapos.database.dao.ClientDao;
import info.atalou.apps.myatapos.database.dao.CompanyDao;
import info.atalou.apps.myatapos.database.dao.CustomerGroupDao;
import info.atalou.apps.myatapos.database.dao.RoleDao;
import info.atalou.apps.myatapos.database.dao.TaxDao;
import info.atalou.apps.myatapos.database.dao.UserDao;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;
import info.atalou.apps.myatapos.database.entity.ClientEntity;
import info.atalou.apps.myatapos.database.entity.CompanyEntity;
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;
import info.atalou.apps.myatapos.database.entity.RoleEntity;
import info.atalou.apps.myatapos.database.entity.TaxEntity;
import info.atalou.apps.myatapos.database.entity.UserEntity;

@Database(entities = {
        CategoryEntity.class,
        ClientEntity.class,
        UserEntity.class,
        RoleEntity.class,
        TaxEntity.class,
        CustomerGroupEntity.class,
        CompanyEntity.class
}, version = 1, exportSchema = false)

@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "AppDatabase.db";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract CategoryDao categoryDao();
    public abstract ClientDao clientDao();
    public abstract UserDao userDao();
    public abstract RoleDao roleDao();
    public abstract TaxDao taxDao();
    public abstract CompanyDao companyDao();
    public abstract CustomerGroupDao customerGroupDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }

        return instance;
    }

    public static AppDatabase create(Context context, boolean inMemory){
        RoomDatabase.Builder<AppDatabase> b;

        if(inMemory){
            b = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class);
        }else{
            b = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME);
        }

        return (b.build());
    }
}