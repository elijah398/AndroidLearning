package com.elijah.androidlearning.aidl;

import android.os.RemoteException;

import com.elijah.androidlearning.IPersonAidlInterface;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/4/19
 */

public class IPersonImpl extends IPersonAidlInterface.Stub {
    private String name;
    private int age;

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void setAge(int age) throws RemoteException {
        this.age = age;
    }

    @Override
    public String getInfo() throws RemoteException {
        return "My name is "+name+", age is "+age+"!";
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}

