package ua.cheesecake.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeMapper {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public String toTime(LocalDateTime time) {
        return dtf.format(time);
    }
}
