package com.rootzwy.bbs.application.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rootzwy.bbs.application.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zwy
 * @date 2022/2/21
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
