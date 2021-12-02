package com.github.thirdparty

import cn.hutool.core.util.RandomUtil
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture


/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-17:22
 */
@SpringBootTest
class EmailTest {

    @Autowired
    lateinit var mailSender: MailSender

    var log = LoggerFactory.getLogger(EmailTest::class.java)

    @Test
    fun sendMail() {
        val code = RandomUtil.randomNumbers(6)

        runBlocking {
            Mono.fromCallable {
                CompletableFuture.runAsync {
                    val msg = SimpleMailMessage()
                    msg.setTo("1066079469@qq.com")
                    msg.setSubject("Verification code")
                    msg.setText("Your Verification code is: $code")
                    mailSender.send(msg)
                    log.info("send success 1")
                }
                log.info("send success 4")
            }.awaitSingle()
            log.info("send success 2")
        }
        log.info("send success 3")
        Thread.sleep(5000)
    }
}
