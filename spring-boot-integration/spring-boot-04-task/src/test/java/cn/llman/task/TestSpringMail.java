package cn.llman.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 在pom依赖中加入Mail的starter后
 * Spring-boot在启动过程中，会向容器中自动配置一个JavaMailSenderImpl
 * 自动配置的描述文件在 spring-boot-autoconfigure-2.1.2.RELEASE.jar/META-INF/spring.factories 文件中
 * 有一个属性用于描述自动配置：org.springframework.boot.autoconfigure.<b>EnableAutoConfiguration</b>
 * 在这个KEY为EnableAutoConfiguration的属性中，VALUE是所有可能通过Spring-boot自动配置的类的全类名
 * 借助于条件注入(@Conditionalxxx)和@Import导入注解等，向IOC容器中注入满足条件的组件
 * {@link EnableAutoConfiguration}
 * <p>
 * 自动注入完毕后，我们在需要某个组件的位置，就可以通过自动注入@Autowired
 *
 * @author
 * @date 2019/1/18
 */
public class TestSpringMail extends SpringBoot04TaskApplicationTests {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Test
    public void testSendMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置邮件
        mailMessage.setSubject("Notice - A simple mail from Spring");
        mailMessage.setText("This is a sweet hello from Spring mail");
        mailMessage.setTo("idyllo@163.com");
        mailMessage.setFrom("383430266@qq.com");

        javaMailSender.send(mailMessage);
    }

    /**
     * TODO 使用SpringMail发送带有附件的邮件
     *
     * @throws MessagingException
     */
    @Test
    public void testSendMailTwo() throws MessagingException {
        // 1. 创建一个复杂的消息邮件
        MimeMessage message = javaMailSender.createMimeMessage();
        // 获取一个MimeMessageHelper
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setSubject("Notice - A simple mail from Spring");
        messageHelper.setText("<b style='color:red'>This is a sweet hello from Spring mail with an appendix.</b>", true);
        // 添加附件
        messageHelper.addAttachment("1.jpg", new File("G:\\img-test\\1.jpg"));
        messageHelper.addAttachment("2.jpg", new File("G:\\img-test\\2.jpg"));
        messageHelper.setTo("idyllo@163.com");
        messageHelper.setFrom("383430266@qq.com");

        javaMailSender.send(message);

        System.out.println("Send Success");

    }
}
