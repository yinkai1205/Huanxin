package cn.itcast.huanxin.utils;

import java.util.HashMap;

import cn.itcast.huanxin.base.BaseFragment;
import cn.itcast.huanxin.view.fragment.ContactFragment;
import cn.itcast.huanxin.view.fragment.ConversationFragment;
import cn.itcast.huanxin.view.fragment.PluginFragment;

/**
 * Created by Kevin.
 */
public class FragmentFactory {

    private static HashMap<Integer, BaseFragment> mFragmentMap = new HashMap<>();

    public static BaseFragment getFragment(int pos) {

        BaseFragment fragment = mFragmentMap.get(pos);

        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = new ConversationFragment();
                    break;
                case 1:
                    fragment = new ContactFragment();
                    break;
                case 2:
                    fragment = new PluginFragment();
                    break;
            }

            mFragmentMap.put(pos, fragment);
        }

        return fragment;
    }
}
