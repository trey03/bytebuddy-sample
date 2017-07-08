package com.trey.bytebuddy.sample;

import java.io.File;

/**
 * Created by chenfeng on 2017/7/8.
 */
public enum  SampleHelper {
    INSTACE;

    public ClassLoader getClassLoader(){
        return SampleHelper.class.getClassLoader();
    }

    public File getSaveDir(){
        String clsFile = CreateClass.class.getResource("/").getFile()+"tmp/";
        File file = new File(clsFile);
        System.out.println(clsFile);
        return file;
    }
}
