package com.dev.GymForDevelopers.util;

public class ValueLikes {
    private Long startValue;
    private Long endValue;


    public ValueLikes(Long startValue, Long endValue) {
        this.startValue = startValue == null ? 0L : startValue;
        this.endValue = endValue == null ? 0L : endValue;
    }

    public boolean eq() {
        return startValue.equals(endValue);
    }

    public boolean notEq() {
        return !eq();
    }

    public Long getStartValue() {
        return startValue;
    }

    public void setStartValue(Long startValue) {
        this.startValue = startValue;
    }

    public Long getEndValue() {
        return endValue;
    }

    public Long setEndValue(Long endValue) {
        this.endValue = endValue;
        return this.endValue;
    }
}
