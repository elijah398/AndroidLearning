package com.elijah.androidlearning.annotation;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/3/28
 */
public class BindProcessor {
    public static void bind(Activity activity) {
        injectLayout(activity);
        bindView(activity);
        bindOnClick(activity);
    }

    public static void injectLayout(Activity activity) {
        Class<?> activityClazz = activity.getClass();
        Log.d("DYJ", "injectLayout activityClazz is " + activityClazz.getSimpleName());
        if (activityClazz.isAnnotationPresent(InjectLayout.class)) {
            InjectLayout injectLayout = activityClazz.getAnnotation(InjectLayout.class);
            Log.d("DYJ", "injectLayout injectLayout is " + injectLayout.toString());
            activity.setContentView(injectLayout.value());
        }
    }

    public static void bindView(Activity activity) {
        Class<?> activityClazz = activity.getClass();
        Field[] declaredFields = activityClazz.getDeclaredFields();
        Log.d("DYJ", "bindView activityClazz is " + activityClazz.getSimpleName());
        for (Field field: declaredFields) {
            Log.d("DYJ", "bindView declaredFields is " + field.getName());
            if ( field.isAnnotationPresent(BindView.class)) {
                BindView bindView = field.getAnnotation(BindView.class);
                Log.d("DYJ", "bindView bindView is " + bindView.toString());
                try {
                    View view = activity.findViewById(bindView.value());
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void bindOnClick(Activity activity) {
        Class<?> activityClazz = activity.getClass();
        Log.d("DYJ", "bindOnClick activityClazz is " + activityClazz.getSimpleName());
        Method[] methods = activityClazz.getMethods();
        for (Method method: methods) {
            Log.d("DYJ", "bindOnClick methods is " + method.getName());
            if (method.isAnnotationPresent(OnClick.class)) {
                OnClick mOnClick = method.getAnnotation(OnClick.class);
                Log.d("DYJ", "mOnClick mOnClick is " + mOnClick.toString());
                int[] ids = mOnClick.value();
                for (int id: ids) {
                    Log.d("DYJ", "mOnClick ids is " + id);
                    final View view = activity.findViewById(id);
                    if(view == null) continue;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.setAccessible(true);
                                method.invoke(activity, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }
}
