package com.juaracoding.mf.controller;

import cn.apiclub.captcha.Captcha;
import com.juaracoding.mf.dto.ForgetPasswordDTO;
import com.juaracoding.mf.dto.UserDTO;
import com.juaracoding.mf.model.Userz;
import com.juaracoding.mf.utils.CaptchaUtils;
import com.juaracoding.mf.utils.MappingAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/check")
public class CheckPageController {

    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    private MappingAttribute mappingAttribute = new MappingAttribute();

    @GetMapping("/signin")
    public String pageOne(Model model)
    {
        Captcha captcha = CaptchaUtils.createCaptcha(150, 60);
        Userz users = new Userz();
        users.setHidden(captcha.getAnswer());
        users.setCaptcha("");
        users.setImage(CaptchaUtils.encodeBase64(captcha));
        model.addAttribute("usr",users);
        return "authz/authz_signin";
    }

    @GetMapping("/home")
    public String pageHome(Model model, WebRequest request)
    {
        mappingAttribute.setAttribute(model,objectMapper,request);
        return "home";
    }

    @GetMapping("/register")
    public String pageTwo(Model model)
    {
        UserDTO users = new UserDTO();
        model.addAttribute("usr",users);
        return "authz/authz_register";
    }

    @GetMapping("/verify")
    public String pageThree(Model model)
    {
        model.addAttribute("usr",new Userz());
        return "authz/authz_verifikasi";
    }

    @GetMapping("/admin")
    public String pageFour(Model model, WebRequest request)
    {
        mappingAttribute.setAttribute(model,objectMapper,request);
        return "dashboard";
    }
    @GetMapping("/styledPage")
    public String pageFive(Model model)
    {
        model.addAttribute("name", "Paulo");
        return "styledPage";
    }

    @GetMapping("/index")
    public String pageSix()
    {
        return "index";
    }

    @GetMapping("/forgetpwd")
    public String pageSeven(Model model)
    {
        ForgetPasswordDTO forgetPasswordDTO = new ForgetPasswordDTO();
        model.addAttribute("forgetpwd",forgetPasswordDTO);
        return "authz/authz_forget_pwd_email";
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/api/check/signin";
    }

}