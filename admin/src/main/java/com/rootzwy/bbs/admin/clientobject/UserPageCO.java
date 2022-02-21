package com.rootzwy.bbs.admin.clientobject;

import lombok.Data;

/**
 * @author zwy
 * @date 2022/2/21
 */
@Data
public class UserPageCO {

    private Long id;

    private String account;

    private String email;

    private String nickname;

    private Integer gender;

    private Integer status;

    private String avatar;

    private Integer articleNum;

}
