package com.uniqueAuction.domain.user.entity;

import com.utils.SessionUtil;

import javax.servlet.http.HttpSession;

public enum Role {
    CUSTOMER,
    ADMIN;


    public static void setSession(HttpSession session, User user) {
        if (user.getRole() == ADMIN) {
            SessionUtil.setLoginAdminId(session, user.getId());
        } else {
            SessionUtil.setLoginMemberId(session, user.getId());
        }
    }
}
