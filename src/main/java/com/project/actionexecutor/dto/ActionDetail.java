package com.project.actionexecutor.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ActionDetail {
    @CsvDate(value = "HH:mm")
    @CsvBindByPosition(position = 0)
    private LocalTime time;

    /**
     * It represents decimal value of 7 digit binary value which each digit represent the days of week
     * from Monday to Sunday orderly. First digit represents Monday and last one 7th Sunday,
     * Digit 0 means NOT CONFIGURED, 1 means CONFIGURED for that day.
     * <p>
     * Examples:
     * bitmask = 3 is equal to 0000011 in binary format. Saturday and Sunday
     * bitmask = 71 is equal to 1000111 in binary format. Monday, Friday, Saturday, Sunday
     * bitmask = 64 is equal to 1000000 in binary format. Monday
     * bitmask = 127 is equal to 1111111 in binary format. Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
     */
    @CsvBindByPosition(position = 1)
    private int bitmask;

    @CsvBindByPosition(position = 2)
    private String action;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getBitmask() {
        return bitmask;
    }

    public void setBitmask(int bitmask) {
        this.bitmask = bitmask;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<DayOfWeek> getConfiguredDays() {
        char[] bitmask = Integer.toBinaryString(getBitmask()).toCharArray(); //11  => 0000011
        if (bitmask.length > 7) {
            throw new RuntimeException("Wrong bitmask value");
        }
        List<DayOfWeek> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < bitmask.length; i++) {
            if (bitmask[i] == '1') {
                daysOfWeek.add(DayOfWeek.of(8 - bitmask.length + i));
            }
        }
        return daysOfWeek;
    }

    @Override
    public String toString() {
        return "ActionDetail{" +
                "time=" + time +
                ", bitmask=" + bitmask +
                ", action='" + action + '\'' +
                '}';
    }
}
