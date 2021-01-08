package com.util;

import com.ceph.MyCeph;
import com.ceph.utils.CephUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// 文件下载工具类
public class FileUtil {
    public static void download(String fileName, HttpServletResponse response, String fileKey) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename   "a.docx"/a.docx
        System.out.println("FileUtil下载的文件名: " + fileName);
        System.out.println("拼接的文件名: " + fileKey);

        ////        //TODO  文件下载 // void writeFile(String fileName, byte[] file);
        MyCeph cephUtils = new CephUtils("admin", "192.168.1.13:6789", "198.162.1.1.2");
        //下载的字节 bytes
        byte[] bytes = cephUtils.readFile(fileKey);
//        outputStream.write(bytes);
        //文件下载的本地文件路径
        bis = new BufferedInputStream(new FileInputStream(new File("D:\\a.docx")));

        int i = bis.read(buff);
        while (i != -1) {
            // buff 替换 bytes
            outputStream.write(buff, 0, buff.length);
            //name 文件名, NewType .文件类型 例如: .docx  .pdf .png
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("D:\\work\\fuma\\code\\springboot-test\\fiel\\" +"a.docx").getBytes("utf-8"), "iso-8859-1"));
            outputStream.flush();
            i = bis.read(buff);
        }
    }
}
