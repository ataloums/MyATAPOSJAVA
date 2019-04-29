package info.atalou.apps.myatapos.myenum;

public enum Gender {
    MALE("m"),
    FEMALE("f");

    private String sex;

    Gender(String sex){
        this.sex = sex;
    }

    public  String getGender(){
        return sex;
    }
}
