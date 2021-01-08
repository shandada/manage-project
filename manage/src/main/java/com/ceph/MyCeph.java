package com.ceph;

/**
 * Description:
 * date: 2020/12/28 11:22
 *
 * @author pengxu
 * @since JDK 1.8
 */
public interface MyCeph {
    void writeFile(String fileName, byte[] file);
    byte[]  readFile(String fileName);
    boolean deleteFile(String fileName);
}
