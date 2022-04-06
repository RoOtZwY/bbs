package com.rootzwy.bbs.application.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootzwy.bbs.application.mybatis.entity.UserEntity;
import com.rootzwy.bbs.application.mybatis.mapper.UserMapper;
import com.rootzwy.bbs.application.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zwy
 * @date 2022/2/21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
