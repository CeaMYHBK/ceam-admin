package com.ceam.system.entity;

import com.anji.captcha.model.vo.CaptchaVO;
import lombok.Data;

/**
 * @Description:
 * @Author: CeaM
 * @Date: 2020/10/8 21:07
 */
@Data
public class LoginForm {

    private String    username;

    private String    password;

    // 滑块验证码二次验证参数
    private CaptchaVO captchaVO;
}
