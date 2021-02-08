package rabbitmq.direct.config;

import com.dean.dean.common.dto.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rabbitmq.direct.config.Sender;

import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class RabbitmqSenderTest {

    @Autowired
    private Sender sender;

    @Test
    void sdendMessage() throws InterruptedException {
      for(int i=0;i<100000;i++){
          User user = new User();
          user.setUserId(String.valueOf(i));
          user.setName("dean");
          Thread thread=new Thread(new ThreadSender(user));
          thread.start();
          Thread.sleep(1000L);
      }

    }
    class ThreadSender implements Runnable {
            private User user;
            public ThreadSender(User user){
                this.user=user;
        }

        @Override
        public void run() {
            try {
                sender.sendDirectQueue(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}