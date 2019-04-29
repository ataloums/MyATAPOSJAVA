package info.atalou.apps.myatapos.database.converter;

import androidx.room.TypeConverter;
import info.atalou.apps.myatapos.myenum.Status;

import static info.atalou.apps.myatapos.myenum.Status.ACTIVE;
import static info.atalou.apps.myatapos.myenum.Status.COMPLETED;
import static info.atalou.apps.myatapos.myenum.Status.INACTIVE;

public class StatusConverter {

    @TypeConverter
    public static Status toStatus(int status) {
        if (status == ACTIVE.getCode()) {
            return ACTIVE;
        } else if (status == INACTIVE.getCode()) {
            return INACTIVE;
        } else if (status == COMPLETED.getCode()) {
            return COMPLETED;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static Integer toInteger(Status status) {
        return status.getCode();
    }
}