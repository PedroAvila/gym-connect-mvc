package pe.com.gymconnect.enums;

public enum GenderCustomer {

    MALE(1),
    FEMALE(2);

    private final int value;

    GenderCustomer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
