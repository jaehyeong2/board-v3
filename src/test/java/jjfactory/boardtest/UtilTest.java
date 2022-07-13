package jjfactory.boardtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilTest {

    @Test
    @DisplayName("로컬데이트타임 형식 변환")
    void timeFormat(){
        LocalDateTime now = LocalDateTime.now();
        now.toString();
        System.out.println("now = " + now);

        String parsed = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("parsed = " + parsed);
    }
}
