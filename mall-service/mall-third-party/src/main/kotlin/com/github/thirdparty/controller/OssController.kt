package com.github.thirdparty.controller

import com.alibaba.cloud.spring.boot.context.env.AliCloudProperties
import com.alibaba.cloud.spring.boot.oss.env.OssProperties
import com.aliyun.oss.OSS
import com.aliyun.oss.common.utils.BinaryUtil
import com.aliyun.oss.model.MatchMode
import com.aliyun.oss.model.PolicyConditions
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-15-21:22
 */
@RestController
@RequestMapping("third-party/oss")
@Api
class OssController {

    @Autowired
    lateinit var ossClient: OSS

    @Autowired
    lateinit var ossProperties: OssProperties

    @Autowired
    lateinit var alicloudProperties: AliCloudProperties

    @Value("\${alibaba.cloud.bucketName}")
    lateinit var bucket: String

    @GetMapping("policy")
    @ApiOperation("oss policy")
    suspend fun policy(): Map<String, Any>? {
        val host = "https://$bucket.${ossProperties.endpoint}" // host的格式为 bucketName.endpoint

        val dir = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now())  // 用户上传文件时指定的前缀。
        val respMap: MutableMap<String, String> = mutableMapOf()
        try {
            val expireTime: Long = 30
            val expireEndTime = System.currentTimeMillis() + expireTime * 1000
            val expiration = Date(expireEndTime)
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            val policyCons = PolicyConditions()
            policyCons.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000)
            policyCons.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir)
            val postPolicy: String = ossClient.generatePostPolicy(expiration, policyCons)
            val binaryData = postPolicy.toByteArray(charset("utf-8"))
            val encodedPolicy: String = BinaryUtil.toBase64String(binaryData)
            val postSignature: String = ossClient.calculatePostSignature(postPolicy)
            respMap["accessid"] = alicloudProperties.accessKey
            respMap["policy"] = encodedPolicy
            respMap["signature"] = postSignature
            respMap["dir"] = dir
            respMap["host"] = host
            respMap["expire"] = (expireEndTime / 1000).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return respMap
    }
}
