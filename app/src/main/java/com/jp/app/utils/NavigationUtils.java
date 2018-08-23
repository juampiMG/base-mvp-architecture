package com.jp.app.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.jp.app.common.view.BaseFragment;
import com.jp.app.model.SampleView;


public class NavigationUtils {

    public static void navigationToSampleInfoActivity(Activity activity, SampleView sample) {
//        Bundle extras = new Bundle();
//        extras.putParcelable(SampleView.class.getSimpleName(), Parcels.wrap(sample));
//        Intent intent = new Intent(activity, SampleInfoActivity.class);
//        intent.putExtras(extras);
//        activity.startActivity(intent);
    }

    public static void navigateToFragment(Activity activity, Fragment fragment,
                                          int contentFrame, boolean addToBackStack) {
        pushFragment(activity, fragment, contentFrame, addToBackStack);
    }

    private static String getFragmentTag(Fragment fragment) {
        String tag;
        if (fragment instanceof BaseFragment) {
            tag = ((BaseFragment) fragment).getFragmentId();
        } else {
            tag = ((Object) fragment).getClass().getName();
        }
        return tag;
    }

    private static void pushFragment(Activity activity, Fragment fragment, int contentFrame, boolean addToBackStack) {
        if (fragment == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = activity.getFragmentManager()
                .beginTransaction();

        String tag = getFragmentTag(fragment);

        fragmentTransaction.replace(contentFrame, fragment, tag);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }

        fragmentTransaction.commit();
        // Only calling executePendingTransactions() if no nested Fragment call
        if (contentFrame <= 0) {
            activity.getFragmentManager().executePendingTransactions();
        }

    }
}
