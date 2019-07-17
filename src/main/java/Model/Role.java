package Model;

import java.text.ParseException;

public enum Role {
    KOD,
    ODON,
    SaleGuy,
    PhoneGuy,
    Universal;

    public static Role parseString(String value) throws ParseException{
        switch (value) {
            case "КОД":
                return Role.KOD;
            case "ОДОН":
                return Role.ODON;
            case "Продажник":
                return Role.SaleGuy;
            case "Телефонист":
                return Role.PhoneGuy;
            case "Универсал":
                return Role.Universal;
            default:
                throw new ParseException(value, 0);

        }
    }

    @Override
    public String toString() {
        switch (this) {
            case KOD:
                return "КОД";
            case ODON:
                return "ОДОН";
            case SaleGuy:
                return "Продажник";
            case PhoneGuy:
                return "Телефонист";
            case Universal:
                return "Универсал";

        }
        return "";
    }
}
