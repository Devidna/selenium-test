package com.juaracoding.listeners;

import com.juaracoding.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class Hook implements IExecutionListener {
    @Override
    public void onExecutionStart() {
        System.err.println("TestNG is commencing execution");
        DriverSingleton.createOrGetDriver();
    }

    @Override
    public void onExecutionFinish() {
        System.err.println("TestNG is finished execution");
        DriverSingleton.quitDriver();
    }
}
