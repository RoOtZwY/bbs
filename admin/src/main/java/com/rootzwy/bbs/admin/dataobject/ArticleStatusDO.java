package com.rootzwy.bbs.admin.dataobject;

import lombok.Data;

import java.util.List;

/**
 * @author zwy
 * @date 2022/2/20
 */
@Data
public class ArticleStatusDO {

    private List<Long> idList;

    private Integer status;

}
