package com.magic;

import java.util.ArrayList;
import java.util.List;

import com.magic.mail.MailService;

//启动类
public class Simulator {

	public static void main(String[] args) {

		// 先启一般服务
		ApiService apiService = new ApiService("10086");// 预留了接上托盘软件的可能性

		List<String> defaultFolders = new ArrayList<String>();
		defaultFolders.add("inbox");

		List<String> qqFolders = new ArrayList<String>();
		qqFolders.add("inbox");
		qqFolders.add("其他文件夹/apple");

		// 启动 邮件服务
		MailService mailHelper = new MailService(apiService);

		// qq邮箱
		mailHelper.addMailListener(MailService.QQ, "xxxx@qq.com", "授权码", qqFolders);
		// 企业qq邮箱
		mailHelper.addMailListener(MailService.QQEX, "企业邮箱", "密码", defaultFolders);
		// 网易163 这个有问题 不能实时监听
		mailHelper.addMailListener(MailService.WY163, "xxxx@163.com", "授权码", defaultFolders);
		// 新浪 这个有问题 不能实时监听
		mailHelper.addMailListener(MailService.SINA, "xxxxx@sina.com", "账号密码", defaultFolders);

		// 开始绘图
		ChatRobotGUI gui = new ChatRobotGUI(apiService, mailHelper);

		// 给窗口浮动开一个独立线程 不用和邮箱监听 在一个线程池里
		new Thread(gui).start();
	}

}