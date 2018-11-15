package cespresso.gmail.com.todo.batch

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class SchedulingTaskTest {
    val log = LoggerFactory.getLogger(this.javaClass)
    val dateFormat = SimpleDateFormat("HH:mm:ss")
    @Scheduled(fixedRate = 100000)
    fun reportCurrentTime(){
        log.info("The time is now {}",dateFormat.format(Date()))
    }
}