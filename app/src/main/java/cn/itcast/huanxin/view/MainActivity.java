package cn.itcast.huanxin.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.itcast.huanxin.R;
import cn.itcast.huanxin.adapter.OnTabSelectedListenerAdapter;
import cn.itcast.huanxin.base.BaseActivity;
import cn.itcast.huanxin.base.BaseFragment;
import cn.itcast.huanxin.utils.FragmentFactory;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initFragment();
        initBottomBar();
    }

    private void initFragment() {
        BaseFragment fragment = FragmentFactory.getFragment(0);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, fragment, "0").commit();
    }

    private void initBottomBar() {
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setText("5");
        bottomNavigationBar.setActiveColor("#00ACFF").setInActiveColor("#A9ADB9")
                .addItem(new BottomNavigationItem(R.mipmap.conversation_selected_2, "消息").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.contact_selected_2, "联系人"))
                .addItem(new BottomNavigationItem(R.mipmap.plugin_selected_2, "动态"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new OnTabSelectedListenerAdapter() {
            @Override
            public void onTabSelected(int position) {
                System.out.println("当前页面:" + position);

                //隐藏其他fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.hide(FragmentFactory.getFragment(0));
                transaction.hide(FragmentFactory.getFragment(1));
                transaction.hide(FragmentFactory.getFragment(2));

                //显示当前fragment
                BaseFragment fragment = FragmentFactory.getFragment(position);
                if (!fragment.isAdded()) {
                    transaction.add(R.id.fl_content, fragment, position + "");
                }
                transaction.show(fragment);

                transaction.commit();
            }
        });
    }
}
