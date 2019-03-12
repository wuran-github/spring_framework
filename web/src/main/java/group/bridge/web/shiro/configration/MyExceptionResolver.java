package group.bridge.web.shiro.configration;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理shiro无授权异常跳转
 * @author wuran
 * @Created on 2019/3/8
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {

        System.out.println("==============异常开始=============");
        ModelAndView view = null;
        if(e instanceof UnauthorizedException){
             view = new ModelAndView("redirect:/permission/403");
        }
        else{
            e.printStackTrace();
        }


        System.out.println("==============异常结束=============");
        return view;
    }
}
