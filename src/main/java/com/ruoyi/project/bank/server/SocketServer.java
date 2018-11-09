package com.ruoyi.project.bank.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ruoyi.common.utils.SysConfigUtil;



public class SocketServer {
	public static int POOL_SIZE = 1;
	ExecutorService pool = Executors.newCachedThreadPool();
	private static SocketServer instance;

	private SocketServer() {
      this.pool.execute(new Runnable() {
		
		@Override
		public void run() {
			startup();
		}
	});
   }

	public static SocketServer getInstance() {
		if (instance == null) {
			instance = new SocketServer();
		}
		return instance;
	}

	private void startup() {
		System.out.println("监听线程启动......");

		try {
			int port = Integer.parseInt(SysConfigUtil.getNodeValue("system.server.port"));
			ServerSocket server = new ServerSocket(port);
			while (true) {
				Socket connection = server.accept();
				SocketReceive sr = new SocketReceive(connection);
				this.pool.execute(sr);
			}
		} catch (IOException e) {
			System.err.println("Unexpected IOException: " + e);
		} finally {
			this.pool.shutdown();
		}

	}

	public static void main(String[] args) {
		getInstance();
	}
}