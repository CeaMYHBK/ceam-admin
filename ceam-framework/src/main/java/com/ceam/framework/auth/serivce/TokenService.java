package com.ceam.framework.auth.serivce;

import com.ceam.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import cn.hutool.core.util.IdUtil;
import com.ceam.common.annotations.RedisEvict;
import com.ceam.common.constants.Constants;
import com.ceam.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: CeaM
 * @Date: 2020/10/8 21:48
 */
@Service
public class TokenService {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 12小时后过期
     */

    private final static long   EXPIRE        = 12 * 60 * 60;

    private final static String ACCESS_TOKEN  = Constants.ACCESS_TOKEN;

    private final static String ACCESS_USERID = Constants.ACCESS_USERID;

    public SysUser queryByToken(String token)
    {
        return redisUtils.get(ACCESS_TOKEN + token, SysUser.class);
    }

    @RedisEvict(key = "user_perms", fieldKey = "#ceamUser.userId")
    public Map<String, Object> createToken(SysUser sysUser)
    {
        //生成token
        String token = IdUtil.fastSimpleUUID();
        //保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", sysUser.getUserId());
        map.put("token", token);
        map.put("expire", EXPIRE);
        //设置token过期时间
        redisUtils.set(ACCESS_TOKEN + token, sysUser, EXPIRE);
        redisUtils.set(ACCESS_USERID  + sysUser.getUserId(), token, EXPIRE);
        return map;
    }

    public void expireToken(long userId)
    {
        String token = redisUtils.get(ACCESS_USERID + userId);
        if (StringUtils.isNoneBlank(token))
        {
            redisUtils.delete(ACCESS_USERID + userId);
            redisUtils.delete(ACCESS_TOKEN  + token);
        }
    }

}
