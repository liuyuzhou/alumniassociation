package com.alumniassociation.api.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.DigestUtils;

/**
 * AES加密解密工具类
 * @author lewp
 *
 */
public class AESUtil {
	private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";//默认的加密算法
    private static final byte[] HEAD = {83, 97, 108, 116, 101, 100, 95, 95, -76, -101, -8, 37, 123, -11, -48, 95}; //固定头部内容
    private static int keySize = 256;
    private static int ivSize = 128;
    public static void main(String[] args) throws Exception{
    	//U2FsdGVkX1+0m/gle/XQX1shjnpveUrl1fO3oOlurPMlTks6+oQlEPfOrucihzEz
        //09240bbf12969e17
        String message = MessageFormat.format("appId={0}&nonce={1}&signType=MD5&timestamp={2}", "7950067a709a77c8c45fc3deb31890d6", "23333333333", "855875555");
        String secretKey = DigestUtils.md5DigestAsHex(message.getBytes()).substring(0,16).toLowerCase();
        String data = "{\"code\":\"200\",\"msg\":\"OK\"," +
                "\"data\":[{\"commentId\":\"289389291\",\"newsId\":\"DSQ8CB7G0001875P\",\"content\":\"呵呵呵，还有约炮约出来打牌的，\",\"author\":\"湿人小冰\",\"releaseTime\":1538125092000,\"url\":\"https://news.163.com/18/0928/16/DSQ8CB7G0001875P.html\",\"sourceId\":8446,\"params\":\"a2869674571f77b5a0867c3d71db5856\",\"vote\":66},{\"commentId\":\"289485529\",\"newsId\":\"DSQ8CB7G0001875P\",\"content\":\"想起我表弟，一个在浙江做门的回到家里结婚生子，回家创业做门生意，老实巴交的人，父母也很贫穷，我们都为他这个儿子感到高兴。 好好的生意不去做，去给赌场放哨，一天几百块 后来负载26万跑路，亲戚10多万，赌场高利贷10多万。妻离子散，父母都是泪\",\"author\":\"用心做好产品振兴中国制造\",\"releaseTime\":1538127470000,\"url\":\"https://news.163.com/18/0928/16/DSQ8CB7G0001875P.html\",\"sourceId\":8446,\"params\":\"a2869674571f77b5a0867c3d71db5856\",\"vote\":27},{\"commentId\":\"290271813\",\"newsId\":\"DSQ8CB7G0001875P\",\"content\":\"什么意思？读不懂\",\"author\":\"王氏s1jy\",\"releaseTime\":1538188295000,\"url\":\"https://news.163.com/18/0928/16/DSQ8CB7G0001875P.html\",\"sourceId\":8446,\"params\":\"a2869674571f77b5a0867c3d71db5856\",\"vote\":2}]" +
                ",\"currentPage\":1,\"commentNum\":5}";
        System.out.println(encrypt(secretKey, data));
        //System.out.println(decrypt("70163e3642b2427", "U2FsdGVkX187UmUbphjI18bKgkVfOCYtUMQf3DvpgsUghV/h36kvosfNNn0VnP2f"));
    }
    
    /**
     * 解密
     *
     * @param secretKey
     *            密钥：加密的规则 16位
     * @param cipherText
     *            密文：加密后的内容，即需要解密的内容
     * @return plainText
     * 		  	      明文：解密后的内容即加密前的内容，如有异常返回空串：""
     */
    public static String decrypt(String secretKey, String cipherText){
    	try{
    		byte[] ctBytes = Base64.getDecoder().decode(cipherText.getBytes("UTF-8"));
            byte[] saltBytes = Arrays.copyOfRange(ctBytes, 8, 16);
            byte[] ciphertextBytes = Arrays.copyOfRange(ctBytes, 16, ctBytes.length);

            byte[] key = new byte[keySize/8];
            byte[] iv = new byte[ivSize/8];
            EvpKDF(secretKey.getBytes("UTF-8"), keySize, ivSize, saltBytes, key, iv);

            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, KEY_ALGORITHM), new IvParameterSpec(iv));
            byte[] recoveredPlaintextBytes = cipher.doFinal(ciphertextBytes);
            String recoveredPlaintext = new String(recoveredPlaintextBytes);
            return recoveredPlaintext;
    	}catch(Exception e){
			throw new RuntimeException("传输数据异常！错误内容：" + e.getMessage());
		}
    }
    
    /**
     * 加密
     *
     * @param secretKey
     *            密钥：加密的规则 16位
     * @param plainText
     *            明文：要加密的内容
     * @return cipherText
     * 			     密文：加密后的内容，如有异常返回空串：""
     */
    public static String encrypt(String secretKey, String plainText) throws Exception {
    	 byte[] key = new byte[keySize/8];
         byte[] iv = new byte[ivSize/8];
         byte[] saltBytes = Arrays.copyOfRange(HEAD, 8, 16);
         /**
          * Encrypt Test
          */
         Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
         EvpKDF(secretKey.getBytes("UTF-8"), keySize, ivSize, saltBytes, key, iv);
         cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, KEY_ALGORITHM), new IvParameterSpec(iv));
         
         byte[] encrypted_bytes = cipher.doFinal(plainText.getBytes("UTF-8"));
         
         byte[] enc_bytes_with_salt = new byte[HEAD.length + encrypted_bytes.length];
         System.arraycopy(HEAD, 0, enc_bytes_with_salt, 0, HEAD.length);
         System.arraycopy(encrypted_bytes, 0, enc_bytes_with_salt, HEAD.length, encrypted_bytes.length);
         return Base64.getEncoder().encodeToString(enc_bytes_with_salt);
    }
    
    public static byte[] EvpKDF(byte[] password, int keySize, int ivSize, byte[] salt, byte[] resultKey, byte[] resultIv) throws NoSuchAlgorithmException {
        return EvpKDF(password, keySize, ivSize, salt, 1, "MD5", resultKey, resultIv);
    }

    public static byte[] EvpKDF(byte[] password, int keySize, int ivSize, byte[] salt, int iterations, String hashAlgorithm, byte[] resultKey, byte[] resultIv) throws NoSuchAlgorithmException {
        keySize = keySize / 32;
        ivSize = ivSize / 32;
        int targetKeySize = keySize + ivSize;
        byte[] derivedBytes = new byte[targetKeySize * 4];
        int numberOfDerivedWords = 0;
        byte[] block = null;
        MessageDigest hasher = MessageDigest.getInstance(hashAlgorithm);
        while (numberOfDerivedWords < targetKeySize) {
            if (block != null) {
                hasher.update(block);
            }
            hasher.update(password);
            block = hasher.digest(salt);
            hasher.reset();

            // Iterations
            for (int i = 1; i < iterations; i++) {
                block = hasher.digest(block);
                hasher.reset();
            }

            System.arraycopy(block, 0, derivedBytes, numberOfDerivedWords * 4,
                    Math.min(block.length, (targetKeySize - numberOfDerivedWords) * 4));

            numberOfDerivedWords += block.length/4;
        }
        System.arraycopy(derivedBytes, 0, resultKey, 0, keySize * 4);
        System.arraycopy(derivedBytes, keySize * 4, resultIv, 0, ivSize * 4);
        return derivedBytes;
    }

}
