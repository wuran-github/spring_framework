package group.bridge.web.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * @author wuran
 * @Created on 2019/3/4
 */
public class ShiroUtil {
    public static String parseHash(String user,String password,String algorithmName){
//        String algorithmName = "SHA-512";
        ByteSource salt = ByteSource.Util.bytes(user);
        int hashIterations = 3;

        SimpleHash result = new SimpleHash(algorithmName,password,salt,hashIterations);
        return result.toString();
    }
    public static String adminHash(String user,String password){
        return parseHash(user,password,"SHA-256");
    }
    public static String userHash(String user,String password){
        return parseHash(user,password,"SHA-512");
    }

//    public static void main(String[] args) {
//        System.out.println(adminHash("admin","admin"));
//        System.out.println(userHash("user","user"));
//
//    }

    /**
     * shiro提供的方法
     * 我们看到 HashRequest 类专门提供各种加密需要的参数，密码明文，salt, hash算法，迭代次数。
     * 这里有个坑，不要调用DefaultHashService的方法来设置各种加密需要的参数(特别是salt相关的参数)，
     * 而使用专门的类 HashRequest来提供各种参数，
     * 因为使用 DefaultHashService 你是无法设置对 salt 的，也无法获得 salt ，
     * 而最终我们是需要将 salt 存放入数据库的，DefaultHashService只能设置 privateSalt,
     * 它hash时最终使用的salt是privateSlat 和 自动生成的 publicSalt，二者合成得到的，
     * 合成的结果并没有提供方法来使我们获得它。另外DefaultHashService有一个坑：
     * 如果你调用方法hashService.setPrivateSalt(new SimpleByteSource("123"));
     * 设置了privateSalt, 即使你调用了hashService.setGeneratePublicSalt(false);方法，
     * 它还是会随机生成publicSalt的。
     * 另外 HashRequest 中提供的参数会覆盖DefaultHashService设置的相应参数。
     * @return
     */
    public static String hashByShiro(String user,String password){
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
//        hashService.setHashAlgorithmName("SHA-512");
//        hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
//        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
//        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
//        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("SHA-512")
                .setSource(password)
                .setSalt(ByteSource.Util.bytes(user))
                .setIterations(3)
                .build();
        String hex = hashService.computeHash(request).toHex();
        return hex;
    }
}
