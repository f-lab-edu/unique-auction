package com.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {

	private static final String LOGIN_MEMBER = "MEMBER_USER";
	private static final String LOGIN_ADMIN = "ADMIN_USER";

	/**
	 * 로그인한 유저의 아이디를 세션에서 꺼낸다.
	 *
	 * @param session 사용자의 세션
	 * @return 로그인한 고객의 id 또는 null
	 */
	public static String getLoginMemberId(HttpSession session) {
		System.out.println("session:" + session);
		System.out.println("session:" + session.getAttribute(LOGIN_MEMBER));
		return String.valueOf(session.getAttribute(LOGIN_MEMBER));
	}

	/**
	 * 로그인 한 유저의 id를 세션에 저장한다.
	 *
	 * @param session 사용자의 session
	 * @param id      로그인한 고객의 id
	 */
	public static void setLoginMemberId(HttpSession session, Long id) {
		session.setAttribute(LOGIN_MEMBER, id);
	}

	/**
	 * 로그인한 관리자 id를 세션에서 꺼낸다.
	 * 로그인 하지 않았다면 null이 반환된다
	 *
	 * @param session 사용자의 세션
	 * @return 로그인한 사장님 id 또는 null
	 */
	public static String getLoginAdminId(HttpSession session) {
		return (String)session.getAttribute(LOGIN_ADMIN);
	}

	/**
	 * 로그인한 관리자 id를 세션에 저장한다.
	 *
	 * @param session 사용자의 세션
	 * @param id      로그인한 사장님 id
	 */
	public static void setLoginAdminId(HttpSession session, Long id) {
		session.setAttribute(LOGIN_ADMIN, id);
	}

	/**
	 * 해당 세션의 정보를 모두 삭제한다.
	 *
	 * @param session 사용자의 세션
	 */
	public static void clear(HttpSession session) {
		session.invalidate();
	}
}
