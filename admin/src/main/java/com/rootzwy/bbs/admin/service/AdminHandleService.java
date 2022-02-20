package com.rootzwy.bbs.admin.service;

import com.rootzwy.bbs.admin.clientobject.AdminCO;
import com.rootzwy.bbs.admin.context.AdminContext;
import com.rootzwy.bbs.admin.dataobject.AdminLoginDO;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zwy
 * @date 2022/2/19
 */
public interface AdminHandleService {

    Response login(AdminLoginDO adminLoginDO, HttpServletResponse response);

    SingleResponse<AdminCO> getAdminInfo(AdminContext adminContext);
}
