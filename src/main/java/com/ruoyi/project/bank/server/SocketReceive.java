package com.ruoyi.project.bank.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.project.bank.TransOfABC;

public class SocketReceive implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(SocketReceive.class);
	private Socket socket = null;

	protected SocketReceive(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			InputStream inputStream = this.socket.getInputStream();
			OutputStream outputStream = this.socket.getOutputStream();
			byte[] buf = new byte[1024];
			inputStream.read(buf);
			String backMsg = new String(buf, "UTF-8");
			logger.info("银行发起报文:{},报文长度{}",backMsg,backMsg.length());
			if (backMsg != null && backMsg.length() > 0) {
				String txcode = backMsg.substring(4, 8);
				logger.info("银行发起的交易码banktxcode:{}",txcode);
				byte[] bckMsg;

				logger.info("SocketReceive银行发起查询3031交易\n,报文:{},报文中编号:{}.",backMsg,new String(buf, 13, 20));
				bckMsg = new String(buf).getBytes();
				outputStream.write(bckMsg, 0, bckMsg.length);


			}

			inputStream.close();
			outputStream.close();
			this.socket.close();
		} catch (Exception e) {
			logger.error("接收银行报文出错",e);
		} finally {
			try {
				if (this.socket != null) {
					this.socket.close();
				}
			} catch (IOException ioe) {
				logger.error("接收银行报文关闭通讯",ioe);
			}

		}

	}
}