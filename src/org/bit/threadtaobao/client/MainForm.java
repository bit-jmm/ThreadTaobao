package org.bit.threadtaobao.client;


import org.bit.threadtaobao.client.location.MyLocation;
import org.bit.threadtaobao.twodimencodescan.TwoDimenCodeScan;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class MainForm extends Activity
{
	ListView mainMenu;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainMenu = (ListView) findViewById(R.id.mainMenu);
		// ΪListView�ĸ��б���ĵ����¼����¼���������
		mainMenu.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				Intent intent = null;
				switch ((int) id)
				{
					// �鿴������Ϣ
					case 0:
						// ����UserProfile Activity
						intent = new Intent(MainForm.this, UserProfile.class);
						startActivity(intent);
						break;
					// ɨһɨ
					case 1:
						// ����TwoDimenCodeScan Activity
						intent = new Intent(MainForm.this, TwoDimenCodeScan.class);
						startActivity(intent);
						break;
					// �鿴���ﳵ
					case 2:
						// ����ViewShoppingCart Activity
						intent = new Intent(MainForm.this, ShoppingCartView.class);
						startActivity(intent);
						break;
					// �鿴�ҵĶ���
					case 3:
						// ����ViewOrder Activity
						intent = new Intent(MainForm.this, OrderListView.class);
						startActivity(intent);
						break;
					case 4:
						// ����MyLocation Activity
						intent = new Intent(MainForm.this, MyLocation.class);
						startActivity(intent);
						break;
					// �˳���¼
					case 5:
						// ����ChooseKind Activity
						intent = new Intent(MainForm.this, LoginForm.class);
						startActivity(intent);
						finish();
						break;
				}
			}
		});
	}
}