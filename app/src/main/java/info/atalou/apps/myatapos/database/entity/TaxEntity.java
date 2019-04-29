package info.atalou.apps.myatapos.database.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "taxes")
public class TaxEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "code")
    @NonNull
    private String code;

    @ColumnInfo(name = "value")
    private double value;

    @ColumnInfo(name = "created_at")
    private Date created;

    @ColumnInfo(name = "updated_at")
    private Date updated;


    @Ignore
    public TaxEntity() {
    }

    public TaxEntity(int id, @NonNull String name, @NonNull String code, double value, Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.value = value;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
