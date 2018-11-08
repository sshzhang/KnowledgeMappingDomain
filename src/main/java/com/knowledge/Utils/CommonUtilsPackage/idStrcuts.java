package com.knowledge.Utils.CommonUtilsPackage;

public class idStrcuts {

    private String first;

    private String next;

    public idStrcuts(String first, String next) {
        this.first = first;
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof idStrcuts) {

            idStrcuts ids = (idStrcuts) obj;

            return (this.first.equals(ids.first) && this.next.equals(ids.next)) || (this.first.equals(ids.next) && this.next.equals(ids.first));

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.next.hashCode() + this.first.hashCode();
    }
}