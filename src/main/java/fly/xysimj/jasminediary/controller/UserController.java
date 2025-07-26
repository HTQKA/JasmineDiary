package fly.xysimj.jasminediary.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import fly.xysimj.jasminediary.config.annotation.LogPrint;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.entity.VerificationReturn;
import fly.xysimj.jasminediary.mapper.UserMapper;
import fly.xysimj.jasminediary.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


/**
 * @program: JasmineDiary
 * @ClassName UserController
 * @description: 用户控制器
 * @author: 徐杨顺
 * @create: 2021-12-09 13:57
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/fyl/user")
@Slf4j
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session, HttpServletRequest request) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        //User user1 = userService.getUser(username, user.getPassword());
        Result result = userService.userLogin(username, user.getPassword());
        return result;
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody User user,HttpSession session,HttpServletRequest request){
        Result result = userService.loginOut(request.getHeader("adminId"));
        return result;
    }

    @PostMapping("/index")
    public Result index(){
        return null;
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user,HttpSession session,HttpServletRequest request){
        return  userService.addUser(user);
    }
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user,HttpSession session,HttpServletRequest request){
        //校验用户是否是否登录
        userService.updateUser(user);
        return null;
    }


    @PostMapping("/deleteUser")
    public Result deleteUsr(@RequestBody User user,HttpSession session,HttpServletRequest request){
        log.info("注销账号");
        return userService.deleteUser(user);
    }
    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return Result.success(userMapper.getAllUsers());
    }

    @Autowired
    private DefaultKaptcha captchaProducer;


    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerificationCodePhoto")
    public void getVerificationCodePhoto(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.info("获取验证码图片");
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生成验证码
            String verifyCode = captchaProducer.createText();
            //验证码字符串保存到session中
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            //设置写出图片的格式
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @ApiOperation("获取验证码和图片")
    @GetMapping("/getVerificationCodeAndPhoto")
    public Result getVerificationCodeAndPhoto(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.info("获取验证码图片");
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        VerificationReturn verificationReturn = new VerificationReturn();
        try {
            //生成验证码
            String verifyCode = captchaProducer.createText();
            //验证码字符串保存到session中
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            verificationReturn.setCode(verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            //设置写出图片的格式
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return Result.fail(e.getMessage());
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        verificationReturn.setImage(captchaOutputStream);
        // httpServletResponse.setHeader("Cache-Control", "no-store");
        // httpServletResponse.setHeader("Pragma", "no-cache");
        // httpServletResponse.setDateHeader("Expires", 0);
        // // httpServletResponse.setContentType("image/jpeg");
        // ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        // responseOutputStream.write(captchaOutputStream);
        // responseOutputStream.flush();
        // responseOutputStream.close();
        return Result.success(verificationReturn);
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getVerificationCode")
    public Result getVerificationCode(HttpServletRequest request) {
        Result result = new Result();
        String verifyCode = captchaProducer.createText();
        request.getSession().setAttribute("verifyCode", verifyCode);
        result.setData(verifyCode);
        return result;

    }


    @CrossOrigin
    @PostMapping("/getToken")
    @ResponseBody
    @LogPrint
    public Result getToken(@RequestBody String token) {
        userService.checkToken(token);
        return Result.success(token);
    }
}
