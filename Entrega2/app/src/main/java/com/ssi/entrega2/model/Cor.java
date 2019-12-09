package com.ssi.entrega2.model;

enum Cor {
    AZUL("Azul", 15),
    PÉROLA("Pérola", 14),
    CARMIM("Carmim", 06),
    PRETO("Preto", 27);

    private String stringValue;
    private int intValue;

    private Cor(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}

