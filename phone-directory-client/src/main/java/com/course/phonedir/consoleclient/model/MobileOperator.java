package com.course.phonedir.consoleclient.model;

public enum MobileOperator {

    MTS("099", 100),
    KYIVSTAR("068", 200),
    LIFECELL("066", 300),
    OTHER("", 400);

    private String numberPrefix;
    private Integer operatorCost;

    MobileOperator(String numberPrefix, Integer operatorCost) {
        this.numberPrefix = numberPrefix;
        this.operatorCost = operatorCost;
    }

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public Integer getOperatorCost() {
        return operatorCost;
    }
}
