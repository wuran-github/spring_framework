package group.bridge.web.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 可以获取本地网卡ip的方法
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}
