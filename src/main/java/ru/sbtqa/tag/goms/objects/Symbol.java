package ru.sbtqa.tag.goms.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Symbol {
    @JsonProperty("BB")
    BB("BB"),
    @JsonProperty("K")
    K("K"),
    @JsonProperty("KK")
    KK("KK"),
    @JsonProperty("O")
    O("O"),
    @JsonProperty("F")
    F("F"),
    @JsonProperty("H")
    H("H"),
    @JsonProperty("M")
    M("M"),
    @JsonProperty("P")
    P("P"),
    @JsonProperty("T")
    T("T"),
    @JsonProperty("KY")
    KY("KY"),
    @JsonProperty("S")
    S("S"),
    @JsonProperty("HE")
    HE("HE"),
    @JsonProperty("SP")
    SP("SP"),
    @JsonProperty("RE")
    RE("RE"),
    @JsonProperty("ST")
    ST("ST");

    private final String value;

    Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Symbol getSymbol(String value) {
        for (Symbol symbol : values()) {
            if (symbol.getValue().equalsIgnoreCase(normalize(value))) {
                return symbol;
            }
        }

        throw new IllegalArgumentException("Symbol enum doesn't have '" + value + "' keyword");
    }
    
    private static String normalize(String value) {
        return value.replaceAll(Symbol.class.getName() + ".", "").replaceAll(Symbol.class.getSimpleName() + ".", "");
    }
}
