package com.trey.bytebuddy.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.io.IOException;

/**
 * Created by chenfeng on 2017/7/8.
 */
public class CreateClass {

    private static String createNewClass() throws IOException{

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .make();

        String createClassName = dynamicType.getTypeDescription().getName();

        dynamicType.load(SampleHelper.INSTACE.getClassLoader());

        dynamicType.saveIn(SampleHelper.INSTACE.getSaveDir());

        return createClassName;
    }

    private static void createNewClassByName(String name) throws IOException{
        //The specified class name.
        DynamicType.Unloaded<?> dynamicType =  new ByteBuddy()
                .subclass(Object.class)
                .name(name)
                .make();

        dynamicType.load(SampleHelper.INSTACE.getClassLoader());
        dynamicType.saveIn(SampleHelper.INSTACE.getSaveDir());
    }

    private static void createNewClassUseNamingStrategy() throws IOException{
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    public String name(TypeDescription superClass) {
                        return "i.love.ByteBuddy." + superClass.getSimpleName();
                    }
                })
                .subclass(Object.class)
                .make();
        dynamicType.saveIn(SampleHelper.INSTACE.getSaveDir());


        dynamicType = new ByteBuddy()
                .with(new NamingStrategy.SuffixingRandom("MySuf"))
                .subclass(Object.class)
                .make();

        dynamicType.saveIn(SampleHelper.INSTACE.getSaveDir());
    }


    private static Object instace(String cls) throws  Throwable{
        Class<?> cType = Class.forName(cls);

        Object instObj = cType.newInstance();

        return instObj;
    }

    public static void main(String[] args) throws Throwable {

        String newCls1 = createNewClass();
        instace(newCls1);

        String newCls2 = "example.Type";
        createNewClassByName(newCls2);
        instace(newCls2);

        createNewClassUseNamingStrategy();
    }
}
