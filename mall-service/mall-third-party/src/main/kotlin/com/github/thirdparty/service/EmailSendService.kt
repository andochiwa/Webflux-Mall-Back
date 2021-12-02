package com.github.thirdparty.service

import cn.hutool.core.util.RandomUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadPoolExecutor

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-18:15
 */
@Service
class EmailSendService {

    @Autowired
    lateinit var mailSender: MailSender

    @Autowired
    lateinit var emailThreadPool: ThreadPoolExecutor

    fun sendEmailCode(email: String): Int {
        val code = RandomUtil.randomNumbers(6).toInt()
        emailThreadPool.execute {
            val msg = SimpleMailMessage()
            msg.setTo("1066079469@qq.com")
            msg.setSubject("Verification code")
            msg.setText("Your Verification code is: $code")
            mailSender.send(msg)
        }
        return code
    }


}
