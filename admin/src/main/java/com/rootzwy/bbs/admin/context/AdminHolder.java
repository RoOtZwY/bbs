package com.rootzwy.bbs.admin.context;

/**
 * @author zwy
 */
public class AdminHolder {

    private static final ThreadLocal<AdminContext> LOCAL = new InheritableThreadLocal<>();

    private static final AdminContext EMPTY_ADMIN = new AdminContext();

    public static AdminContext get() {
        return LOCAL.get();
    }

    public static AdminContext getOrEmpty() {
        return LOCAL.get() == null ? LOCAL.get() : EMPTY_ADMIN;
    }

    public synchronized static AdminContext set(AdminContext adminContext) {
        LOCAL.set(adminContext);
        return adminContext;
    }

    public static void remove() {
        LOCAL.remove();
    }

}
