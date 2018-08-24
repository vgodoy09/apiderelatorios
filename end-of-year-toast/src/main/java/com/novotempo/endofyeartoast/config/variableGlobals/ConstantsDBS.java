package com.novotempo.endofyeartoast.config.variableGlobals;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConstantsDBS {

	//ip do servidor oficial
	public static final String OFICIAL_IP = "10.21.0.23018"; 

	//usuário e senha corporativo
	private static final String CORPORATE_SYS_USER_TEST = "corporatesysuser";
	private static final String CORPORATE_PASSWORD_TEST = "cos104mg2";

	private static final String CORPORATE_SYS_USER_OFICIAL = "corporatesysuser";
	private static final String CORPORATE_PASSWORD_OFICIAL = "cos104mg2";

	//usuário e senha atendimento
	private static final String ATTENDANCE_SYS_USER_TEST = "attendancesysuser";
	private static final String ATTENDANCE_PASSWORD_TEST = "br34d14a69";

	private static final String ATTENDANCE_SYS_USER_OFICIAL = "attendancesysuser";
	private static final String ATTENDANCE_PASSWORD_OFICIAL = "br34d14a69";
	
	//usuário e senha stock
	private static final String STOCK_SYS_USER_OFICIAL = "stocksysuser";
	private static final String STOCK_PASSWORD_OFICIAL = "stb3b3ok57";
	
	private static final String STOCK_SYS_USER_TEST = "stocksysuser";
	private static final String STOCK_PASSWORD_TEST = "stb3b3ok57";
	
	// DNE
	private static final String DNE_SYS_USER_TEST = "dnesysuser";
	private static final String DNE_PASSWORD_TEST = "iEIBGMsPZOj8b70ko5yC";
	
	private static final String DNE_SYS_USER_OFICIAL = "dnesysuser";
	private static final String DNE_PASSWORD_OFICIAL = "iEIBGMsPZOj8b70ko5yC";


	//corporate
	public static String getCorporateSysUser() { 
		return isOficial() ? CORPORATE_SYS_USER_OFICIAL : CORPORATE_SYS_USER_TEST;
	}

	public static String getCorporatePassword() {
		return isOficial() ? CORPORATE_PASSWORD_OFICIAL : CORPORATE_PASSWORD_TEST;
	}

	//attendance
	public static String getAttendanceSysUser() { 
		return isOficial() ? ATTENDANCE_SYS_USER_OFICIAL : ATTENDANCE_SYS_USER_TEST;
	}

	public static String getAttendancePassword() {
		return isOficial() ? ATTENDANCE_PASSWORD_OFICIAL : ATTENDANCE_PASSWORD_TEST;
	}
	
	//stock
	public static String getStockSysUser() { 
		return isOficial() ? STOCK_SYS_USER_OFICIAL : STOCK_SYS_USER_TEST;
	}

	public static String getStockPassword() {
		return isOficial() ? STOCK_PASSWORD_OFICIAL : STOCK_PASSWORD_TEST;
	}
	
	// DNE
	public static String getDneSysUser() {
		return isOficial() ? DNE_SYS_USER_OFICIAL : DNE_SYS_USER_TEST;
	}

	public static String getDnePassword() {
		return isOficial() ? DNE_PASSWORD_OFICIAL : DNE_PASSWORD_TEST;
	}

	/**
	 * Pega ip do banco para configuração
	 * @return
	 */
	public static String getIpDb() {
		return isOficial() ? "10.21.0.200" : "10.21.0.25";
	}

	/**
	 * Verifica se está no servidor oficial
	 * @return
	 */
	private static boolean isOficial() {
		return getIP() != null && getIP().equals(OFICIAL_IP);
	}
	/**
	 * Pega IP da máquina corrente
	 * @return
	 */
	public static String getIP() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostAddress();
		} catch (UnknownHostException e) {}
		return null;
	}
	
	public static String getHostname() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostName();
		} catch (UnknownHostException e) {}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getIP());
	}
}
