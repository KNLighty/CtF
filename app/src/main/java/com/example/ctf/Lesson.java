package com.example.ctf;

import java.util.Date;

public class Lesson {
    private int currentHour, currentMinutes, currentSeconds;

    private final int lessonNumber;
    private String lessonNumber_s;

    private int endHour, endMinute, endSecond;
    public int[] timeLeft = new int[3];

    public Lesson(int lessonNumber, int hour, int minutes, int seconds) {
        this.lessonNumber = lessonNumber;
        this.currentHour = hour;
        this.currentMinutes = minutes;
        this.currentSeconds = seconds;
        setEndTime();
        calculateTimeLeft();

        numToString();
    }

    private void setEndTime() {
        endSecond = 0;
        if (lessonNumber == 1) {
            endHour = 9;
            endMinute = 30;
        }
        else if (lessonNumber == 2) {
            endHour = 11;
            endMinute = 10;
        }
        else if (lessonNumber == 3) {
            endHour = 13;
            endMinute = 20;
        }
        else if (lessonNumber == 4) {
            endHour = 15;
            endMinute = 0;
        }
        else {
            endHour = currentHour;
            if (currentHour == 9) endMinute = 40;
            else if (currentHour == 11) endMinute = 50;
            else if (currentHour == 13) endMinute = 30;
        }
    }

    public void calculateTimeLeft() {
        int totalSecondsCurrent, totalSecondsEnd, totalSecondsLeft;
        totalSecondsCurrent = currentHour * 3600 + currentMinutes * 60 + currentSeconds;
        totalSecondsEnd = endHour * 3600 + endMinute * 60 + endSecond;
        totalSecondsLeft = totalSecondsEnd - totalSecondsCurrent;

        timeLeft[0] = totalSecondsLeft / 3600;
        totalSecondsLeft -= 3600 * timeLeft[0];
        timeLeft[1] = totalSecondsLeft / 60;
        totalSecondsLeft -= 60 * timeLeft[1];
        timeLeft[2] = totalSecondsLeft;
    }

    private void numToString() {
        switch (lessonNumber) {
            case 1:
                lessonNumber_s = "первая пара";
                break;
            case 2:
                lessonNumber_s = "вторая пара";
                break;
            case 3:
                lessonNumber_s = "третья пара";
                break;
            case 4:
                lessonNumber_s = "четвёртая пара";
                break;
            default:
                lessonNumber_s = "перемена";
        }
    }

    @Override
    public String toString() {
        return lessonNumber_s;
    }
}
