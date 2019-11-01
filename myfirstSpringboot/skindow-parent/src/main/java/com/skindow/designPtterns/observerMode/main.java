package com.skindow.designPtterns.observerMode;

/**
 * Created by skindow on 2019/11/1.
 */
public class main {
    public static void main(String[] args)
    {
        Person_1 person_1 = new Person_1();
        Person_2 person_2 = new Person_2();
        Thermometer thermometer = new Thermometer();
        thermometer.addPerson(person_1);
        thermometer.addPerson(person_2);

        for (int i = 0; i <= 100 ; i ++)
        {
            thermometer.notifyAllPerson(i);
        }
    }
}
