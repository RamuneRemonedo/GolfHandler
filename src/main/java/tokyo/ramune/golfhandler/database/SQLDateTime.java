package tokyo.ramune.golfhandler.database;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Calendar;
import java.util.Date;

public class SQLDateTime {

    private int year, month, day, hour, minute, second;

    public SQLDateTime(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public SQLDateTime(@NonNull String sqlDateTime) {
        year = sqlDateTime.charAt(0) + sqlDateTime.charAt(1) + sqlDateTime.charAt(2) + sqlDateTime.charAt(3);
        month = sqlDateTime.charAt(5) + sqlDateTime.charAt(6);
        day = sqlDateTime.charAt(8) + sqlDateTime.charAt(9);
        hour = sqlDateTime.charAt(11) + sqlDateTime.charAt(12);
        minute = sqlDateTime.charAt(14) + sqlDateTime.charAt(15);
        second = sqlDateTime.charAt(17) + sqlDateTime.charAt(18);
    }

    public SQLDateTime() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
    }

    public String getSQLDate() {
        StringBuilder date = new StringBuilder();
        date.append(year);
        date.append("-");

        if (String.valueOf(month).length() == 1) {
            date.append("0");
            date.append(month);
        }
        if (String.valueOf(month).length() == 2) {
            date.append(month);
        }
        date.append("-");
        if (String.valueOf(day).length() == 1) {
            date.append("0");
            date.append(day);
        }
        if (String.valueOf(day).length() == 2) {
            date.append(day);
        }
        date.append(" ");
        if (String.valueOf(hour).length() == 1) {
            date.append("0");
            date.append(hour);
        }
        if (String.valueOf(hour).length() == 2) {
            date.append(hour);
        }
        date.append(":");
        if (String.valueOf(minute).length() == 1) {
            date.append("0");
            date.append(minute);
        }
        if (String.valueOf(minute).length() == 2) {
            date.append(minute);
        }
        date.append(":");
        if (String.valueOf(second).length() == 1) {
            date.append("0");
            date.append(second);
        }
        if (String.valueOf(second).length() == 2) {
            date.append(second);
        }
        return date.toString();
    }

    public Date getDate() {
        return new Date(year, month, day, hour, minute, second);
    }

    public String toString() {
        return getSQLDate();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
