package com.maoyan.ffcommunity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class FfcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FfcommunityApplication.class, args);
        System.out.println(
                "\033[1;91m" + "       \\`*-.\n" +
                        "\033[1;91m" + "        )  _`-.\n" +
                        "\033[1;92m" + "       .  : `. .\n" +
                        "\033[1;93m" + "       : _   '  \\\n" +
                        "\033[1;94m" + "       ; *` _.   `*-._\n" +
                        "\033[1;95m" + "       `-.-'          `-.\n" +
                        "\033[1;96m" + "         ;       `       `.\n" +
                        "\033[1;91m" + "         :.       .        \\\n" +
                        "\033[1;92m" + "         . \\  .   :   .-'   .\n" +
                        "\033[1;93m" + "         '  `+.;  ;  '      :\n" +
                        "\033[1;94m" + "         :  '  |    ;       ;-.\n" +
                        "\033[1;95m" + "         ; '   : :`-:     _.`* ;\n" +
                        "\033[1;96m" + "     .*' /  .*' ; .*`- +'  `*'\n" +
                        "\033[1;91m" + "      `*-*   `*-*  `*-*'\n" +
                        "\033[1;92m" + "      启动成功!!! \033[0m");
    }

    @Autowired
    private JavaMailSender javaMailSender;
    @Test
    public void testEmail() throws MessagingException {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost("smtp.qq.com");
//        javaMailSender.setPort(465);
//        javaMailSender.setUsername("1071352028@qq.com");
//        javaMailSender.setPassword("ngdfnbqvzgnrbfha");
//        javaMailSender.setJavaMailProperties(new java.util.Properties() {{
//            put("mail.smtp.auth", true);
//            put("mail.smtp.ssl.enable", true);
//        }});
        // 建立信息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setSubject("测试邮件");
        mimeMessageHelper.setText("<h1>测试邮件</h1>", true);
        mimeMessageHelper.setTo("1071352028@qq.com");
        mimeMessageHelper.setFrom("1071352028@qq.com");
        javaMailSender.send(mimeMessage);
    }
}
