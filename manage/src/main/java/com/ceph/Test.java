package com.ceph;

import com.ceph.utils.CephUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description:
 * date: 2020/12/28 17:02
 *
 * @author pengxu
 * @since JDK 1.8
 */
public class Test {
    public static void main(String[] args){

//    {file   gongyongshang ming   filename.tar  file tag
        ///static/1.txt
        //src/main/resources/1.txt
        File file = new File("/static/1.txt");
        System.out.println("文件内容： ");
        byte[] bytes = fileToBetyArray(file);
        MyCeph myCeph=new CephUtils("admin","192.168.1.13","AQArI9hfvp36IRAAFhB7U6t6ltcLSfHciZiy0A==");
        System.out.println("开始写入ceph.....");
        //
        myCeph.writeFile("id-filename-tag-tar",bytes);
        System.out.println("文件写入完毕.....开始读取刚才写入的文件....");
        byte[] bytes1 = myCeph.readFile("1.txt");
        System.out.println("读取文件的内容；");
        for (int i = 0; i < bytes1.length; i++) {
            System.out.print((char) bytes1[i]);
        }
    }
    public static byte[] fileToBetyArray(File file)
    {
        FileInputStream fileInputStream = null;
        byte[] bFile = null;
        try {
            bFile = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            for (int i = 0; i < bFile.length; i++) {
                System.out.print((char) bFile[i]);
            }
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                bFile.clone();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bFile;
    }

}
