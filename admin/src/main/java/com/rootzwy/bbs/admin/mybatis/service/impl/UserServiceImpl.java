package com.rootzwy.bbs.admin.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootzwy.bbs.admin.mybatis.entity.UserEntity;
import com.rootzwy.bbs.admin.mybatis.mapper.UserMapper;
import com.rootzwy.bbs.admin.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zwy
 * @date 2022/2/21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
