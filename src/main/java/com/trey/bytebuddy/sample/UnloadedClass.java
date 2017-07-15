package com.trey.bytebuddy.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;

/**
 * Created by chenfeng on 2017/7/12.
 */
public class UnloadedClass {

    public static void main(String[] args) throws Throwable {
        TypePool typePool = TypePool.Default.ofClassPath();
        new ByteBuddy()
                .redefine(typePool.describe("com.trey.bytebuddy.sample.SubCl").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofClassPath())
                .defineField("fieldName", String.class) // we learn more about defining fields later
                //.defineMethod("test",String.class,1)
                .make()
                .load(ClassLoader.getSystemClassLoader());
        System.out.println(SubCl.class.getDeclaredField("fieldName"));
    }
}

class SubCl {
    private String field1;
    private int fieldName;

    public String test(){
        return "test1";
    }
}