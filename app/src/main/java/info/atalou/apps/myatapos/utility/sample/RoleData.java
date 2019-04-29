package info.atalou.apps.myatapos.utility.sample;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import info.atalou.apps.myatapos.database.entity.RoleEntity;

public class RoleData {

    private static final String SAMPLE_TEXT_1 = "Administrator";
    private static final String SAMPLE_TEXT_2 = "Cashier";

    private static Date getDate(int diff){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND,diff);
        return cal.getTime();
    }

    public static List<RoleEntity> getRoles(){
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(new RoleEntity(1,SAMPLE_TEXT_1,getDate(0)));
        roles.add(new RoleEntity(2,SAMPLE_TEXT_2, getDate(-1)));

        return roles;
    }

}
