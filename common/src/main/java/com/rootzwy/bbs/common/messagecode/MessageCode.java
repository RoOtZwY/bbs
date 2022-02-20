package com.rootzwy.bbs.common.messagecode;

import java.io.Serializable;

/**
 * @author zwy
 * @date 2022/1/30
 */
public interface MessageCode<R> extends Serializable {

    long serialVersionUID = 1L;

    R getCode();

    String getDescription();

}
