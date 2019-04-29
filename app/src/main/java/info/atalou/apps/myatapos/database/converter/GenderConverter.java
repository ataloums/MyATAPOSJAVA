package info.atalou.apps.myatapos.database.converter;

import androidx.room.TypeConverter;
import info.atalou.apps.myatapos.myenum.Gender;

import static info.atalou.apps.myatapos.myenum.Gender.FEMALE;
import static info.atalou.apps.myatapos.myenum.Gender.MALE;

public class GenderConverter {

    @TypeConverter
    public static Gender stringToGender(String sex) {
        if (sex == MALE.getGender()) {
            return MALE;
        } else if (sex == FEMALE.getGender()) {
            return FEMALE;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static String genderToString(Gender status) {
        return status.getGender();
    }
}
