package com.lk.syxl.customview.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    private Person person;

    public ProxyTest(Object proxyed){
        person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.e("proxy","before invoke method"+method.getName());
                method.invoke(proxyed,args);
                Log.e("proxy","after invoke method"+method.getName());
                return null;
            }
        });


//
    }

    public String getName(){
        return person.getName();
    }

    public void setName(String name){
        person.setName("aaa");
    }
}
