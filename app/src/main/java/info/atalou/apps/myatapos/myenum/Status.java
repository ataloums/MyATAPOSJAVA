package info.atalou.apps.myatapos.myenum;

public enum Status {
    ACTIVE(0),
    INACTIVE(1),
    COMPLETED(2);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}