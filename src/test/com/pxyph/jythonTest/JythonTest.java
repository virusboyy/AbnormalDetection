package com.pxyph.jythonTest;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JythonTest {
    /**
     * 调用机器学习算法进行异常行为检测，并返回异常信息对象
     */
    @Test
    public void anomalyDetectionModel() throws IOException, InterruptedException {
        /**
         * 机器学习算法，进行异常行为检测
         */
        String exe = "python3";
        String command = "model/Demo.py";
        String argv1 = "weights_L1L2.mat";
        String argv2 = "model.json";
        String[] cmdArr = new String[]{exe, command, argv1, argv2};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        str = str.substring(1,str.length()-1);
        process.waitFor();
        String[] strs = str.split(",");
        for(String s:strs){
            System.out.println(s);
        }
    }
}
