package com.novotempo.endofyeartoast.config.variableGlobals;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConstantsDB {

	//ip do servidor oficial
	public static final String OFICIAL_IP = "10.21.0.23018"; 

	//usuário e senha donation
	private static final String DONATION_SYS_USER_TEST = "donationsysuser";
	private static final String DONATION_PASSWORD_TEST = "****";

	private static final String DONATION_SYS_USER_OFICIAL = "donationsysuser";
	private static final String DONATION_PASSWORD_OFICIAL = "*****";

	


	//donation
	public static String getDonationSysUser() { 
		return isOficial() ? DONATION_SYS_USER_OFICIAL : DONATION_SYS_USER_TEST;
	}

	public static String getDonationPassword() {
		return isOficial() ? DONATION_PASSWORD_OFICIAL : DONATION_PASSWORD_TEST;
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
