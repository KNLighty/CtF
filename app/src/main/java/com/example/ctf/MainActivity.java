package com.example.ctf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timeLeft = findViewById(R.id.time_left);

        // Текущее время
        Date currentDate = new Date();
        String resultMessage = " ";
        if (checkDateTime(currentDate)) {
            Lesson currentLesson = getCurrentLesson(currentDate);

            resultMessage = "     Сейчас идёт " + currentLesson;
            int hoursLeft, minutesLeft, secondsLeft;
            hoursLeft = currentLesson.timeLeft[0];
            minutesLeft = currentLesson.timeLeft[1];
            secondsLeft = currentLesson.timeLeft[2];

            resultMessage += ".\n\n До её конца осталось: " + hoursLeft + " ч. ";
            resultMessage += minutesLeft + " мин. " + secondsLeft + " сек. ";
        }
        else {
            resultMessage = "Никаких пар нет!";
        }


        // Выводим результат
        timeLeft.setText(resultMessage);
    }


    // Проверка на необходимость расчёта времени (пон.-пят. 08:00 - 15:00)
    private static boolean checkDateTime(Date currentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfTheWeek = sdf.format(currentDate);

        int hourNum = getHour(currentDate);

        return hourNum >= 8 && hourNum <= 15
                && !dayOfTheWeek.equals("суббота") && !dayOfTheWeek.equals("воскресенье");
    }

    private static int getHour(Date currentDate) {
        DateFormat timeFormat = new SimpleDateFormat("H", Locale.getDefault());
        String hour = timeFormat.format(currentDate);

        return Integer.parseInt(hour);
    }

    private static int getMinutes(Date currentDate) {
        DateFormat timeFormat = new SimpleDateFormat("m", Locale.getDefault());
        String minutes = timeFormat.format(currentDate);

        return Integer.parseInt(minutes);
    }

    private static int getSeconds(Date currentDate) {
        DateFormat timeFormat = new SimpleDateFormat("s", Locale.getDefault());
        String seconds = timeFormat.format(currentDate);

        return Integer.parseInt(seconds);
    }

    private Lesson getCurrentLesson(Date currentDate) {
        int hour = getHour(currentDate);
        int minutes = getMinutes(currentDate);
        int seconds = getSeconds(currentDate);

        int lessonNumber = 0; // по умолчанию перемена

        if (hour == 8) lessonNumber = 1;
        else if (hour == 9) {
            if (minutes <= 30) lessonNumber = 1;
            else if (minutes >= 40) lessonNumber = 2;
        }
        else if (hour == 10) lessonNumber = 2;
        else if (hour == 11) {
            if (minutes <= 10) lessonNumber = 2;
            else if (minutes >= 50) lessonNumber = 3;
        }
        else if (hour == 12) lessonNumber = 3;
        else if (hour == 13) {
            if (minutes <= 20) lessonNumber = 3;
            else if (minutes >= 30) lessonNumber = 4;
        }
        else if (hour == 14) lessonNumber = 4;

        return new Lesson(lessonNumber, hour, minutes, seconds);
    }
}