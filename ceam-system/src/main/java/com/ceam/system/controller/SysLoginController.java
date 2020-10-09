package com.ceam.system.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.service.CaptchaService;
import com.ceam.common.core.domain.R;
import com.ceam.common.core.domain.entity.SysUser;
import com.ceam.framework.auth.serivce.SysLoginService;
import com.ceam.framework.auth.serivce.TokenService;
import com.ceam.system.entity.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: CeaM
 * @Date: 2020/10/8 21:03
 */
public class SysLoginController
{
    @Autowired
    private TokenService    tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    CaptchaService           captchaService;

    @PostMapping("login")
    public R login(@RequestBody LoginForm form)
    {
        SysUser user = sysLoginService.login(form.getUsername(), form.getPassword());
        return R.ok(tokenService.createToken(user));
    }

    @PostMapping("login/slide")
    public R loginSilde(@RequestBody LoginForm form)
    {
        ResponseModel response = captchaService.verification(form.getCaptchaVO());
        if (response.isSuccess())
        {
            // 用户登录
            SysUser user = sysLoginService.login(form.getUsername(), form.getPassword());
            // 获取登录token
            return R.ok(tokenService.createToken(user));
        }
        return R.error().put("repCode", response.getRepCode());
    }

    @PostMapping("logout")
    public R logout(HttpServletRequest request)
    {
        String token=request.getHeader("token");
        SysUser user=tokenService.queryByToken(token);
        if (null != user)
        {
            sysLoginService.logout(user.getLoginName());
            tokenService.expireToken(user.getUserId());
        }
        return R.ok();
    }
}
