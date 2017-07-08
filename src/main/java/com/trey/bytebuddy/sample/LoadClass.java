package com.trey.bytebuddy.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

/**
 * Created by chenfeng on 2017/7/8.
 */
public class LoadClass {
    public static void main(String[] args) {
        Class<?> type = new ByteBuddy()
                .subclass(Object.class)
                .make()
                .load(SampleHelper.INSTACE.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        System.out.println(type.getName());
    }
}
