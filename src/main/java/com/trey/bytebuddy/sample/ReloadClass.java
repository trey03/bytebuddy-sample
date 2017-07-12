package com.trey.bytebuddy.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

/**
 * Created by chenfeng on 2017/7/8.
 */
public class ReloadClass {
    public static void main(String[] args) throws Throwable {
        ByteBuddyAgent.install();

        Foo foo = new Foo();
        System.out.println(foo.m());
        System.out.println(foo.m("test"));
        System.out.println(foo.m1());

        DynamicType.Loaded loaded = new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        loaded.saveIn(SampleHelper.INSTACE.getSaveDir());

        System.out.println(foo.m());
        System.out.println(foo.m("test..."));
        System.out.println(foo.m1());
    }
}

/*
* Foo and Bar the methods must same.
* */
class Foo {
    String m() { return "foo."; }
    String m(String a) {return "foo "+a ; }
    String m1() {return "foo m1."; }
}

class Bar {
    String m() { return "bar."; }
    String m(String a) {return "bar "+a ; }
    String m1() {return "bar m1."; }
}
