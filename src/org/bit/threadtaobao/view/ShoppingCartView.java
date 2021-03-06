package org.bit.threadtaobao.view;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.bit.threadtaobao.globalEntity.GlobalObjects;
import org.bit.threadtaobao.mainobjects.Goods;
import org.bit.threadtaobao.util.ConfigureLog4J;
import org.bit.threadtaobao.util.DialogUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ShoppingCartView extends Activity {

	private ListView shoppingcartListView = null;
	private ArrayList<Goods> goodsList;
	private EditText allAmount;
	private Button generateOrderBtn;
	//日志
	private Logger logger; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcart);
		ConfigureLog4J.configure();
		logger = Logger.getLogger(ShoppingCartView.class);
		init();
	}

	// 初始化界面
	public void init() {
		shoppingcartListView = (ListView) findViewById(R.id.shoppingcart_listView);
		goodsList = GlobalObjects.shoppingCart.getGoodsList();

		GoodsAdapter adapter = new GoodsAdapter(this, goodsList);
		shoppingcartListView.setAdapter(adapter);

		shoppingcartListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				logger.trace("查看商品详情");
				viewItemDetail(position);
			}
		});

		allAmount = (EditText) findViewById(R.id.all_amount);
		allAmount.setText("共："
				+ String.valueOf(GlobalObjects.shoppingCart.getAllGoodsNum())
				+ "件商品 ，  "
				+ " 总价： " + String.valueOf(GlobalObjects.shoppingCart.getTotalAmount()) + "元");
		generateOrderBtn = (Button) findViewById(R.id.generateOrderBtn);
		generateOrderBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (GlobalObjects.shoppingCart.getAllGoodsNum() == 0) {
					AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartView.this);
					builder.setTitle("错误").setMessage("购物车没有商品，请先扫码！")
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
								}
							}).show();
					return;
				}
				if(GlobalObjects.shoppingCart.generateOrder()){
					logger.trace("生成订单成功");
					Intent intent = new Intent(ShoppingCartView.this, OrderListView.class);
					startActivity(intent);
				} else {
					logger.trace("生成订单出错");
					AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartView.this);
					builder.setTitle("错误").setMessage("生成订单出错！")
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
								}
							}).show();
				}
			}
		});
	}

	public void viewItemDetail(int position) {
		View detailView = getLayoutInflater().inflate(R.layout.goods_detail,
				null);
		Goods goods = (Goods) (shoppingcartListView.getAdapter()
				.getItem(position));

		EditText goodsName = (EditText) detailView
				.findViewById(R.id.goods_name);
		goodsName.setText(goods.getGoodsName());

		EditText goodsBrand = (EditText) detailView
				.findViewById(R.id.goods_brand);
		goodsBrand.setText(goods.getGoodsBrand());

		EditText goodsPrice = (EditText) detailView
				.findViewById(R.id.goods_price);
		goodsPrice.setText(String.valueOf(goods.getGoodsPrice()) + "元");

		EditText supermarket = (EditText) detailView
				.findViewById(R.id.supermarket);
		supermarket.setText(goods.getSupermarket().getName());

		EditText discount = (EditText) detailView.findViewById(R.id.discount);
		if (goods.getDiscount() == null) {
			discount.setText("无");
		} else {
			discount.setText(String.valueOf(goods.getDiscount().getValue()) + "折");
		}
		
		DialogUtil.showDialog(ShoppingCartView.this, detailView);
	}
}
