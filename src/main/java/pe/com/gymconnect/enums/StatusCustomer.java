package pe.com.gymconnect.enums;

public enum StatusCustomer {

    ENABLED(1),
    DISABLED(0);

    private final int value;

    StatusCustomer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
