package com.zql.comm.helper;

import java.io.Serializable;

/**
 * Create by Totoro
 * 2019-11-11 17:34
 **/
public class PowerInfo implements Serializable {

    private int current;
    private int total;
    private int temp;
    private int state;


    public PowerInfo() {
    }

    public PowerInfo(int current, int total, int temp, int state) {
        this.current = current;
        this.total = total;
        this.temp = temp;
        this.state = state;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "PowerInfo{" +
                "current=" + current +
                ", total=" + total +
                ", temp=" + temp +
                ", state=" + state +
                '}';
    }
}
