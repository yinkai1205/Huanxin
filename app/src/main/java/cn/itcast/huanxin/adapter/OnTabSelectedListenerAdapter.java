package cn.itcast.huanxin.adapter;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

/**
 * Created by Kevin.
 */
public abstract class OnTabSelectedListenerAdapter implements BottomNavigationBar.OnTabSelectedListener {
    @Override
    public abstract void onTabSelected(int position);

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
