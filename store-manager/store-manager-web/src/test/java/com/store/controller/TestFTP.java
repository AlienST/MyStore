package com.store.controller;

import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class TestFTP {
	@Test
	public void testFtpClient() throws Exception{
		//创建FTPClient(ftp客户端)对象，commons.net.ftp包提供
		FTPClient ftpClient=new FTPClient();
		ftpClient.connect("120.78.182.217", 22); //创建FTP连接，默认是21端口
		ftpClient.login("ftpuser", "HHHhk205");  //ftp账户及密码
		FileInputStream inputStream=new FileInputStream("F:/linux.jpg"); //传入流
		if(inputStream!=null){
			System.out.println("input不为空");
		}else {
			System.out.println("input为空");
		}
		//设置上传文件格式为2进制，防止乱码
		//ftpClient.setFileType( FTP.BINARY_FILE_TYPE);
		//设置上传服务器里的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www");
		//上传文件，第一个参数：上传到服务器端后的文档名，第二个参数：本地文件流
		ftpClient.storeFile("test.jpg", inputStream);
		//关闭连接
		ftpClient.logout();
	}
}
