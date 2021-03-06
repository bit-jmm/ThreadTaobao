package org.bit.threadtaobao.view;


import org.apache.log4j.Logger;
import org.bit.threadtaobao.codescan.CodeScan;
import org.bit.threadtaobao.location.MyLocation;
import org.bit.threadtaobao.util.ConfigureLog4J;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainForm extends Activity
{
	ListView mainMenu;
	//日志
	private Logger logger; 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ConfigureLog4J.configure();
		logger = Logger.getLogger(MainForm.class);
		mainMenu = (ListView) findViewById(R.id.mainMenu);
		// 为ListView的各列表项的单击事件绑定事件监听器。
		mainMenu.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				Intent intent = null;
				switch ((int) id)
				{
					// 查看个人信息
					case 0:
						// 启动UserProfile Activity
						logger.trace("查看个人信息");
						intent = new Intent(MainForm.this, UserProfile.class);
						startActivity(intent);
						break;
					// 扫一扫
					case 1:
						// 启动TwoDimenCodeScan Activity
						logger.trace("开始扫码");
						intent = new Intent(MainForm.this, CodeScan.class);
						startActivity(intent);
						break;
					// 我的购物车
					case 2:
						// 启动ViewShoppingCart Activity
						logger.trace("进入我的购物车");
						intent = new Intent(MainForm.this, ShoppingCartView.class);
						startActivity(intent);
						break;
					// 查看我的订单
					case 3:
						// 启动ViewOrder Activity
						logger.trace("查看我的订单");
						intent = new Intent(MainForm.this, OrderListView.class);
						startActivity(intent);
						break;
					// 查看我的位置
					case 4:
						// 启动MyLocation Activity
						logger.trace("查看我的位置");
						intent = new Intent(MainForm.this, MyLocation.class);
						startActivity(intent);
						break;
					// 退出登录
					case 5:
						// 启动ChooseKind Activity
						logger.trace("退出登录");
						intent = new Intent(MainForm.this, LoginForm.class);
						startActivity(intent);
						finish();
						break;
				}
			}
		});
	}
}