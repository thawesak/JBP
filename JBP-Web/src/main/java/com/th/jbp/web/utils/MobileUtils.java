package com.th.jbp.web.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class MobileUtils {
	private MobileUtils() {
	}

	public static boolean sendSMS(String number, String text) {
		String url = "https://secure.thaibulksms.com/sms_api.php?";

		try {
			// Construct data
			String user = "username=" + "0863981666"; // "";
			String hash = "&password=" + "001569";
			String message = "&message=" + text;
			String sender = "&sender=" + "SMS";
			String numbers = "&msisdn=" + number;
			String data = user + hash + numbers + message + sender;

			// Send data
			HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();

			conn.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});

			System.out.println(url);
			System.out.println(data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println(stringBuffer.toString());
			return true;
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {
		sendSMS("0955856446", "OTP=009898");
	}

}
