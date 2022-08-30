package toy.project;

import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableScheduling
public class batchTest {

//    @Test
    @Scheduled(fixedRate = 1000)
    public void test() {
        System.out.println("hello!");
    }

    @Test
    public void LocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("_ now => " + now);
        System.out.println("_ 1=> " + now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        System.out.println("_ 2=> " + now.getDayOfWeek());
    }
}
