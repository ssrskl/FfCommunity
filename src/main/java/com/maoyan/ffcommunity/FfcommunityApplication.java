package com.maoyan.ffcommunity;

import com.maoyan.ffcommunity.entity.vo.qeuser.QeUserDetailVO;
import com.maoyan.ffcommunity.mapper.QeUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    private QeUserMapper qeUserMapper;

    @Test
    public void test() {
        QeUserDetailVO qeUserDetailVO = qeUserMapper.selectQeUserDetailById(1L);
        System.out.println(qeUserDetailVO.toString());
    }
}
