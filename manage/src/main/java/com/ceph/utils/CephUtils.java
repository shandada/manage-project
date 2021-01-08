package com.ceph.utils;

import com.ceph.MyCeph;
import com.ceph.rados.IoCTX;
import com.ceph.rados.Rados;
import com.ceph.rados.exceptions.RadosException;
import com.ceph.rbd.Rbd;
import com.ceph.rbd.RbdException;
import com.ceph.rbd.RbdImage;
import com.ceph.rbd.jna.RbdImageInfo;
import com.ceph.rbd.jna.RbdSnapInfo;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * Description:
 * date: 2020/12/28 11:22
 *
 * @author pengxu
 * @since JDK 1.8
 */
public class CephUtils implements MyCeph {
    private static Rados rados;
    private static IoCTX ioctx;
    private static Rbd rbd;

    /**
     * 连接ceph集群
     * @param mon_host monitor节点ip
     * @param key 秘钥
     */
    public CephUtils(String clusterName,String mon_host, String key) {
        try {
            rados = new Rados(clusterName);
            rados.confSet("mon_host", mon_host);
            rados.confSet("key", key);
            rados.connect();
            ioctx = rados.ioCtxCreate("testPool");
            System.out.println("cpeh-connect success");
            rbd = new Rbd(ioctx);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    /**
     * ceph文件写入        上传到ceph服务器中
     * @param file
     * @param fileName 文件key  由 供应商id-文件名-版本号-文件拓展名  组成
     */
    public void writeFile(String fileName,byte[] file)  {
        try {
            ioctx.write(fileName,file);
            rados.ioCtxDestroy(ioctx);
        } catch (RadosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件读取
     * @param fileName
     * @return
     */
    public byte[] readFile(String fileName)  {
        try {
            FileOutputStream out;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len=1024;
            byte[]buf=new byte[len];
            //定义读取字节的数量
            long count=0;
            while (ioctx.read(fileName,len,count,buf)>0){
                try {
                    bos.write(buf);
                    count+=len;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rados.ioCtxDestroy(ioctx);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除 ceph 某个文件
     * @param fileName 文件名字
     * @return
     */
    public boolean deleteFile(String fileName){
        try {
            ioctx.remove(fileName);
            return true;
        } catch (RadosException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 展示映像的细节
     * @param imageName
     */
    public static RbdImage showDetailOfImage(String imageName){
        RbdImage image;
        try {
            image = rbd.open(imageName);
            RbdImageInfo info = image.stat();
            System.out.println("=================================================================");
            System.out.println("imageName:    "+imageName);
            System.out.println("imageSize:    "+info.size);
            System.out.println("order:   "+info.order);
            rbd.close(image);
            return image;
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 返回所有的image,并展示其详细信息
     * @return
     */
    public static List<String> imageList(){
        List<String> imageList=null;
        try {
            imageList = Arrays.asList(rbd.list());
            for(String s:imageList){
                showDetailOfImage(s);
            }
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return imageList;
    }
    /**
     * 创建映像的快照
     * @param imageName 映像名称
     * @param snapName 快照名称
     */
    public static void createSnap(String imageName,String snapName){
        try {
            RbdImage image = rbd.open(imageName);
            //创建快照
            image.snapCreate(snapName);
            //保护快照可以防止快照被删除
            image.snapProtect(snapName);
            //返回一个image的所有快照
            List<RbdSnapInfo> snaps = image.snapList();
            for(RbdSnapInfo rbds:snaps){
                System.out.println("快照名称："+rbds.name);
                System.out.println("快照大小："+rbds.size);
            }
        } catch (RbdException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 创建image
     * @param imageName 名称
     * @param imageSize　大小
     */
    public static RbdImage createRbd_format(String imageName, long imageSize){
        try {
            rbd.create(imageName, imageSize);
            RbdImage image = rbd.open(imageName);
            boolean oldFormat = image.isOldFormat();
            System.out.println("imageFormat:==========================="+oldFormat);
            rbd.close(image);
            return image;
        } catch (RbdException e) {
            System.out.println(e.getMessage() + ": " + e.getReturnValue());
            return null;
        }
    }
    /**
     * 删除某一个image
     * @param r
     * @param io
     * @param imageName
     * @throws RadosException
     * @throws RbdException
     */
   /* public static void cleanupImage(Rados r, IoCTX io, String imageName) {
        try {
            if (r != null) {
                if (io != null) {
                    Rbd rbd = new Rbd(ioctx);
                    RbdImage image = rbd.open(imageName);
                    rbd.close(image);
                    rbd.remove(imageName);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }*/

}
