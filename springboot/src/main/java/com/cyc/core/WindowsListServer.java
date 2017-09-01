package com.cyc.core;

/**
 * Created by cyc_e on 2017/9/2.
 */
public class WindowsListServer implements ListServer {

    @Override
    public String showListCmd() {
        return "dir";
    }
}
