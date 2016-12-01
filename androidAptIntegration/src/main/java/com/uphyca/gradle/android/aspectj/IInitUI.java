package com.uphyca.gradle.android.aspectj;

import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author bruce.zhang
 *
 */
public interface IInitUI {

	/**
	 * 初始化Intent或者savedInstanceState中的数据
	 * if(savedInstanceState != null)
	 * {
	 *		xxx = savedInstanceState.getSerializable("bbs_share_param");
	 *  }
	 * else
	 * {
	 *		handleIntent(getIntent());
	 *  }
	 * @param savedInstanceState
     */
	void initData(Bundle savedInstanceState);

	int getLayoutId();

	void initView(View view);

	/**
	 * 请求数据
	 */
	void requestData();

}
