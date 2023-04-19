// IPersonAidlInterface.aidl
package com.elijah.androidlearning;

// Declare any non-default types here with import statements

interface IPersonAidlInterface {
    //具体的业务
    void setName(String name);
    void setAge(int age);
    String getInfo();

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}