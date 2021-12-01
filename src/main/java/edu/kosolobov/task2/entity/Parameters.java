package edu.kosolobov.task2.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Parameters {
    private static final Logger log = LogManager.getLogger(Parameters.class);
    public static final int DEFAULT = 0;
    public static final int INTERNET = 1;
    public static final int SMS_CALL = 2;
    public static final int FULL = 3;
    public static final int ROAMING = 4;
    private final boolean unlimitedInternet;
    private final boolean unlimitedCalls;
    private final boolean unlimitedSMS;
    private final boolean roaming;

    public static Parameters getParameters(int mode) {
        return switch (mode) {
            case 0 -> new Parameters(false, false, false, false);
            case 1 -> new Parameters(true, false, false, false);
            case 2 -> new Parameters(false, true, true, false);
            case 3 -> new Parameters(true, true, true, true);
            case 4 -> new Parameters(false, false, true, true);
            default -> getParameters(DEFAULT);
        };
    }

    private Parameters(boolean unlimitedInternet, boolean unlimitedCalls, boolean unlimitedSMS, boolean roaming) {
        this.unlimitedInternet = unlimitedInternet;
        this.unlimitedCalls = unlimitedCalls;
        this.unlimitedSMS = unlimitedSMS;
        this.roaming = roaming;
    }

    public boolean isUnlimitedInternet() {
        return unlimitedInternet;
    }

    public boolean isUnlimitedCalls() {
        return unlimitedCalls;
    }

    public boolean isUnlimitedSMS() {
        return unlimitedSMS;
    }

    public boolean isRoaming() {
        return roaming;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return unlimitedInternet == that.unlimitedInternet && unlimitedCalls == that.unlimitedCalls && unlimitedSMS == that.unlimitedSMS && roaming == that.roaming;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result += unlimitedInternet? 1 : 0;
        result += unlimitedCalls? 10 : 0;
        result += unlimitedSMS? 100 : 0;
        result += roaming? 1000 : 0;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Parameters{unlimited internet:%s, unlimited calls:%s, unlimited sms:%s, roaming:%s}",
                unlimitedInternet, unlimitedCalls, unlimitedSMS, roaming);
    }
}
