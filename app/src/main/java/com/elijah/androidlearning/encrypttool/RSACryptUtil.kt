//package com.elijah.androidlearning.encrypttool
//
//import org.apache.commons.codec.binary.Base64
//import java.io.ByteArrayOutputStream
//import java.nio.charset.Charset
//import java.security.*
//import java.security.spec.PKCS8EncodedKeySpec
//import java.security.spec.X509EncodedKeySpec
//import javax.crypto.Cipher
//import javax.crypto.IllegalBlockSizeException
//
///**
// * @Description : RSA加密工具类。请谨记 公钥加密就用私钥解密，私钥加密就用公钥加密。工具类并没有进行异常捕获
// * @Classname   : RSACryptUtil
// */
//object RSACryptUtil {
//
//    private const val RSA_ALGORITHM  = "RSA"
//    private const val PUBLICKEYSTR = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMFOy6kVbOfiRqr6YyL7lK9TlblD/J/ESajcYnu4R0jskuLTRCPkxWV21TJJFz1VtmYFxjUUiUp53vYzuoXpYvUCAwEAAQ=="
//    private const val PRIVATEKEYSTR = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAwU7LqRVs5+JGqvpjIvuUr1OVuUP8n8RJqNxie7hHSOyS4tNEI+TFZXbVMkkXPVW2ZgXGNRSJSnne9jO6heli9QIDAQABAkBbSkD3+vxRjhTdjh3fWXxgSFL/CVJ2iRZYPqvrzZ0snGqWkVtFFK8TgDssYfbb4M9mmTQls1M2B5w2oxTTMSdBAiEA5OvqMEKoBcjfr0fMwW0+lbVdMKdBGdsoBCgsXOOJjGkCIQDYLHGGK6Wx3PKblHQi4Pu444oKPVEBFUU7BAfEaZmArQIgQHIyAdgiGU7NujjKagHpRphCXfoYF8Fc6J6uro/YH1ECIAZwTHFSm3zQt+kqYssnKFg5bsMfUGpX19gKJrES9B+BAiAryi5UC37OEyXTu7WtuqIOdznxk2/RNDm8wLmBo7cgJQ=="
//    private const val ENCRYPT_MAX_SIZE = 50
//    private const val DECRYPT_MAX_SIZE = 50
//
//    private val keyFactory: KeyFactory = KeyFactory.getInstance(RSA_ALGORITHM)
//    // RAS对加密有要求，加密的数据不能操作117个字节，所以要使用分段加密
//
//    /**
//     * 公钥加密，使用私钥解密，不传入公钥就表示使用当前公钥加密
//     */
//    fun encryByPublicKey(input: String): String {
//        return encryByPublicKey(input, PUBLICKEYSTR)
//    }
//
//    /**
//     * 公钥加密，使用私钥解密
//     */
//    fun encryByPublicKey(input: String, publickey: String): String {
//        return Base64.encodeBase64String(
//            with(Cipher.getInstance(RSA_ALGORITHM)) {
//                init(Cipher.ENCRYPT_MODE, generatePublicKey(publickey))
//                sectionEncryptAndDecrypt(this, input.toByteArray(), ENCRYPT_MAX_SIZE)
//            })
//    }
//
//    /**
//     * 生成公钥，传入公钥字符串数据，
//     */
//    private fun generatePublicKey(publicKeyStr: String): PublicKey? {
//        return keyFactory.generatePublic(X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr)))
//    }
//
//    /**
//     * 生成私钥，传入私钥字符串
//     */
//    private fun generatePrivateKey(privateKeyStr: String): PrivateKey? {
//        return keyFactory.generatePrivate(PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr)))
//    }
//
//    /**
//     * 分段加密解密，不允许外部调用
//     */
//    private fun sectionEncryptAndDecrypt(cipher: Cipher, toByteArray: ByteArray, max: Int): ByteArray {
//        var buff: ByteArray?
//        var offset = 0
//        val bos = ByteArrayOutputStream()
//
//        try {
//            //进行分段加密
//            while (toByteArray.size - offset > 0) {
//                //每次最大加密117个字节
//                if (toByteArray.size - offset >= max) {
//                    //剩余部分大于117
//                    //加密完整117
//                    buff = cipher.doFinal(toByteArray, offset, max)
//                    //重新计算偏移位置
//                    offset += max
//                } else {
//                    buff = cipher.doFinal(toByteArray, offset, toByteArray.size - offset)
//                    // 重新计算偏移位置
//                    offset = toByteArray.size
//                }
//                // 存储到临时缓冲区
//                bos.write(buff)
//            }
//        } catch (e: IllegalBlockSizeException) {
//            e.printStackTrace()
//        } finally {
//            bos.close()
//        }
//
//        return bos.toByteArray()
//    }
//
//    /**
//     * 私钥解密 ，解密公钥加密之后的数据，不传入私钥就表示使用当前私钥解密
//     */
//    fun decryByPrivateKey(input: String): String {
//        return decryByPrivateKey(input, PRIVATEKEYSTR)
//    }
//
//    /**
//     * 私钥解密 ，解密公钥加密之后的数据
//     */
//    private fun decryByPrivateKey(input: String, privatekey: String): String {
//        return String(
//            with(Cipher.getInstance(RSA_ALGORITHM)) {
//                init(Cipher.DECRYPT_MODE, generatePrivateKey(privatekey))
//                sectionEncryptAndDecrypt(this, Base64.decodeBase64(input), DECRYPT_MAX_SIZE)
//            }, Charset.defaultCharset()
//        )
//
//    }
//}