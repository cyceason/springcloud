package com.cyc.core;

/**
 * Created by cyc_e on 2017/9/2.
 */
public class LinuxListServer implements ListServer {

    @Override
    public String showListCmd() {
        return "ls";
    }
}
