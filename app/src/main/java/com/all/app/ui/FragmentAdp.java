package com.all.app.ui;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;



import java.util.List;

public class FragmentAdp {
	private List<Fragment> fragments; // 一个tab页面对应一个Fragment
	private FragmentActivity fActivity; // Fragment所属的Activity
	private int fContentId; // Activity中所要被替换的区域的id
	private int currentTab;

	public FragmentAdp(FragmentActivity aty, List<Fragment> list, int contentId) {
		this.fActivity = aty;
		this.fragments = list;
		this.fContentId = contentId;
		// 默认显示第一页
		FragmentTransaction ft = fActivity.getSupportFragmentManager().beginTransaction();
		ft.add(fContentId, fragments.get(0));
		ft.commit();
		currentTab = 0;
	}


	public void CheckedIndex(int index) {
		Fragment fragment = fragments.get(index);
		FragmentTransaction ft = obtainFragmentTransaction(index);
		getCurrentFragment().onPause(); // 暂停当前tab
		if (fragment.isAdded()) {
			fragment.onResume(); // 启动目标tab的onResume()
		} else {
			ft.add(fContentId, fragment);
		}
		showTab(index); // 显示目标tab
		ft.commit();

	}

	public Fragment getCurrentFragment() {
		return fragments.get(currentTab);
	}

	/**
	 * 切换tab
	 * @param idx
	 */
	private void showTab(int idx) {
		for (int i = 0; i < fragments.size(); i++) {
			Fragment fragment = fragments.get(i);
			FragmentTransaction ft = obtainFragmentTransaction(idx);

			if (idx == i) {
				ft.show(fragment);
			} else {
				ft.hide(fragment);
			}
			ft.commit();
		}
		currentTab = idx; // 更新目标tab为当前tab
	}

	/**
	 * 获取一个带动画的FragmentTransaction
	 * @param index
	 * @return
	 */
	private FragmentTransaction obtainFragmentTransaction(int index) {
		FragmentTransaction ft = fActivity.getSupportFragmentManager().beginTransaction();
		// 设置切换动画
		return ft;
	}

	public int getCurrentTab() {
		return currentTab;
	}
}
