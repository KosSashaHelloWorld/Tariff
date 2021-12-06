package edu.kosolobov.task2.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
    private static final Logger log = LogManager.getLogger(Parameters.class);
    private static final String REGEX_PHONE_NUMBER = "[+]\\d{1,3}[(]\\d{2}[)]\\d{3}[-]\\d{2}[-]\\d{2}";
    private float tarification;
    private List<String> favoriteNumbers = new ArrayList<>();

    public Parameters() {
    }

    public void setTarification(float tarification) {
        this.tarification = tarification;
    }

    public void addFavoriteNumber(String favoriteNumber) {
        if (favoriteNumber.matches(REGEX_PHONE_NUMBER)) {
            favoriteNumbers.add(favoriteNumber);
        } else {
            log.log(Level.WARN, "{} does not match regex {}", favoriteNumber, REGEX_PHONE_NUMBER);
        }
    }

    public float getTarification() {
        return tarification;
    }

    public List<String> getFavoriteNumbers() {
        return favoriteNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return Float.compare(that.tarification, tarification) == 0
                && favoriteNumbers.hashCode() == that.favoriteNumbers.hashCode();
    }

    @Override
    public int hashCode() {
        float prime = 5.2f;
        float temp = 1.0f;
        temp = temp * prime + tarification;
        int result = (int) temp;
        for (String number : favoriteNumbers) {
            for (char aChar : number.toCharArray()) {
                result += aChar;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("Parameters{tarification:%.1f, favoriteNumbers: %s}", tarification, favoriteNumbers);
    }
}
