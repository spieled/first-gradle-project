package com.mgj.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NetshUtil {

	private static List<Passcode> passcodes = new ArrayList<>();

	static class Passcode {
		private int passcode;
		private String validate;

		public Passcode(int passcode, String validate) {
			this.passcode = passcode;
			this.validate = validate;
		}

		public int getPasscode() {
			return passcode;
		}

		public void setPasscode(int passcode) {
			this.passcode = passcode;
		}

		public String getValidate() {
			return validate;
		}

		public void setValidate(String validate) {
			this.validate = validate;
		}
	}

	static {
		passcodes.add(new Passcode(71604, "8a351"));
		passcodes.add(new Passcode(99902, "160fb"));
		passcodes.add(new Passcode(3377, "39acd"));
	}

	public static void main(String[] args) {
		try {
			refreshIpHosts();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void refreshIpHosts() {
		Passcode passcode = passcodes.get(new Random().nextInt(passcodes.size()));
		String url = "https://serve.netsh.org/pub/ipv4-hosts/";
		String s ;
		Map<String, String> headerMap ;

		url = "https://serve.netsh.org/pub/ipv4-hosts/collection/none.php?passcode="+passcode.getPasscode()+"&gs=on&ig=on&wk=on&gh=on&twttr=on&fb=on&flkr=oon&dpbx=on&odrv=on&yt=on&validate="+passcode.getValidate()+"&nolh=on";

		System.out.println(url);
		headerMap = new HashMap<>();
		headerMap.put(":authority", "serve.netsh.org");
		headerMap.put(":method", "GET");
		headerMap.put(":path", "/pub/ipv4-hosts/collection/none.php?passcode="+passcode.getPasscode()+"&gs=on&ig=on&wk=on&gh=on&twttr=on&fb=on&flkr=oon&dpbx=on&odrv=on&yt=on&validate="+passcode.getValidate()+"&nolh=on");
		headerMap.put(":scheme", "https");
		headerMap.put("accept-encoding", "gzip, deflate, sdch, br");
		headerMap.put("accept-language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
		headerMap.put("cookie", "hostspasscode="+passcode.getPasscode()+"; Hm_lvt_e26a7cd6079c926259ded8f19369bf0b=1467251175; Hm_lpvt_e26a7cd6079c926259ded8f19369bf0b=1467252139; _ga=GA1.2.351551186.1467252135; _gat=1");
		headerMap.put("referer", "https://serve.netsh.org/pub/ipv4-hosts/");
		headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
		headerMap.put("x-requested-with", "XMLHttpRequest");
		s = HttpUtil.doGet(url, new HashMap<String, Object>(), headerMap, true);
		String os = System.getenv("OS");
		String hosts;
		if (os.contains("Win")) {
			hosts = "C:\\Windows\\System32\\drivers\\etc\\HOSTS";
		} else {
			hosts = "/etc/hosts";
		}
		try {
			FileUtils.writeStringToFile(new File(hosts), s, "UTF-8", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
