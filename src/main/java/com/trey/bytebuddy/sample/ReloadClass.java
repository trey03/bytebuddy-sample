package com.trey.bytebuddy.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

/**
 * Created by chenfeng on 2017/7/8.
 */
public class ReloadClass {
    public static void main(String[] args) {
        ByteBuddyAgent.install();
        Foo foo = new Foo();
        new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        System.out.println(foo.m());
    }
}
class Foo {
    String m() { return "foo"; }
}

class Bar {
    String m() { return "bar"; }
}
