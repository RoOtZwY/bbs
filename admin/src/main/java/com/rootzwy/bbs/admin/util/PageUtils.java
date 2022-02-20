package com.rootzwy.bbs.admin.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 分页参数转换工具类
 * @author zwy
 * @date 2021/12/25
 */
public class PageUtils {

    /**
     * 目前用于 DO Page 参数转换为 CO Page
     * @param sourcePage DO page
     * @param targetClass 目标 CO 的 Class
     * @param <T> 目标 CO
     * @return CO Page
     */
    public static <T> IPage<T> convertPage(IPage<?> sourcePage, Class<T> targetClass) {
        List<T> targetList = BeanUtil.copyToList(sourcePage.getRecords(), targetClass);
        IPage<T> targetPage = new Page<>();
        BeanUtil.copyProperties(sourcePage, targetPage, "records");
        targetPage.setRecords(targetList);
        return targetPage;
    }

    /**
     * 目前用于 DO Page 参数转换为 CO Page
     * @param sourcePage DO page
     * @param targetClass 目标 CO 的 Class
     * @param <T> 目标 CO
     * @return CO Page
     */
    public static <T> Page<T> convertPage(Page<?> sourcePage, Class<T> targetClass) {
        List<T> targetList = BeanUtil.copyToList(sourcePage.getRecords(), targetClass);
        Page<T> targetPage = new Page<>();
        BeanUtil.copyProperties(sourcePage, targetPage, "records");
        targetPage.setRecords(targetList);
        return targetPage;
    }

}
