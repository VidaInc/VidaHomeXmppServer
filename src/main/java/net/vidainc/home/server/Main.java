package net.vidainc.home.server;

import net.vidainc.home.server.spring.Application;
import net.vidainc.home.server.xmpp.CcsClient;

public final class Main {

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Application.main(args);
			}
		}).start();
		CcsClient.main(args);
	}

}
