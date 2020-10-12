package me.apjung.backend.component.MailHandler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@SpringBootTest
public class MailHandlerTest {
    @Autowired
    private MailHandler mailHandler;

    @Test
    public void 메일_전송() throws MessagingException {
        try {
            // given
            Context context = new Context();
            context.setVariable("title", "This is Test Title");
            context.setVariable("content", "This is <p> content");

            String text = mailHandler.getTemplateHtml("test", context);

            // when
            CustomMailMessage message = CustomMailMessage.builder()
                    .from("admin@apjung.me")
                    .to("labyu2020@gmail.com")
                    .subject("This is Test Email")
                    .text(text)
                    .isHtml(true)
                    .build();

            mailHandler.send(message);

            // then not exception
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}