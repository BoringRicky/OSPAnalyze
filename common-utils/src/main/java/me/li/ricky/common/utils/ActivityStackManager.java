package me.li.ricky.common.utils;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

public class ActivityStackManager {

    private Stack<Activity> mActivityStack = new Stack();

    private ActivityStackManager() {
    }

    private static class InstanceHolder {
        private static ActivityStackManager INSTANCE = new ActivityStackManager();
    }

    public static ActivityStackManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void addActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            if (activity != null && mActivityStack.contains(activity)) {
                mActivityStack.remove(activity);
            }
        }
    }

    public void finishActivity(Activity activity) {
        if (mActivityStack != null && mActivityStack.size() > 0) {
            if (activity != null && mActivityStack.contains(activity)) {
                mActivityStack.remove(activity);
                activity.finish();
            }
        }
    }

    public void finishAllActivity() {
        if (notEmpty()) {
            Iterator<Activity> iterator = mActivityStack.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                if (activity != null) {
                    iterator.remove();
                    activity.finish();
                }
            }
        }
    }

    public int size() {
        return notEmpty() ? mActivityStack.size() : 0;

    }

    public Activity getTop() {
        return mActivityStack.lastElement();
    }

    private boolean notEmpty() {
        return mActivityStack != null && !mActivityStack.isEmpty();
    }


}
