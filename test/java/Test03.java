import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhangling
 * @date 2022/12/7 7:36 PM
 */
public class Test03 {
    public static void main(String[] args) throws IOException {
        // URL url = new URL("http://xz.holytax.com/pdf/d/OWEuiMsVJAauEKjyLBqOKhFmautyM8lQpieMUfOR4jHJAqJ2GPvRnblDWSfQzp72%5EbEifFEcgHg/v2.0");
        // InputStream inputStream = url.openStream();
        // int available = inputStream.available();
        // System.out.println("available = " + available);
        // System.out.println("inputStream.available() = " + inputStream.available());

        // 随便一个网络图片地址
        String urlpath = "http://xz.holytax.com/pdf/d/OWEuiMsVJAauEKjyLBqOKhFmautyM8lQpieMUfOR4jHJAqJ2GPvRnblDWSfQzp72%5EbEifFEcgHg/v2.0";
        // 得到数据
        byte[] imageFromURL =getImageFromURL(urlpath);
        // 转换
        String byte2kb = bytes2kb(imageFromURL.length);
        //输出
        System.out.println("图片的字节数："+imageFromURL.length+"图片的大小："+byte2kb);
    }

    /**
     * 测试
     * @param args
     */
    public static void test(String[] args) {
        // 随便一个网络图片地址
        String urlpath = "https://teny-img.oss-cn-hongkong.aliyuncs.com/images/goods/5d42961629c65468.jpg";
        // 得到数据
        byte[] imageFromURL =getImageFromURL(urlpath);
        // 转换
        String byte2kb = bytes2kb(imageFromURL.length);
        //输出
        System.out.println("图片的字节数："+imageFromURL.length+"图片的大小："+byte2kb);
    }
    /**
     * 根据图片地址获取图片信息
     * @param urlPath   网络图片地址
     * @return
     */
    public static byte[] getImageFromURL(String urlPath) {
        // 字节数组
        byte[] data = null;
        // 输入流
        InputStream is = null;
        // Http连接对象
        HttpURLConnection conn = null;
        try {
            // Url对象
            URL url = new URL(urlPath);
            // 打开连接
            conn = (HttpURLConnection) url.openConnection();
            // 打开读取 写入是setDoOutput
//	        conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置请求方式
            conn.setRequestMethod("GET");
            // 设置超时时间
            conn.setConnectTimeout(6000);
            // 得到访问的数据流
            is = conn.getInputStream();
            // 验证访问状态是否是200 正常
            if (conn.getResponseCode() == 200) {
                data = readInputStream(is);
            } else{
                data=null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null){
                    // 关闭流
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 关闭连接
            conn.disconnect();
        }
        return data;
    }

    /**
     * 将流转换为字节
     * @param is
     * @return
     */
    public static byte[] readInputStream(InputStream is) {
        /**
         * 捕获内存缓冲区的数据，转换成字节数组
         * ByteArrayOutputStream类是在创建它的实例时，程序内部创建一个byte型别数组的缓冲区，然后利用ByteArrayOutputStream和ByteArrayInputStream的实例向数组中写入或读出byte型数据。
         * 在网络传输中我们往往要传输很多变量，我们可以利用ByteArrayOutputStream把所有的变量收集到一起，然后一次性把数据发送出去。
         */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 创建字节数组 1024 = 1M
        byte[] buffer = new byte[1024];
        // 防止无限循环
        int length = -1;
        try {
            // 循环写入数据到字节数组
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            // 强制刷新，扫尾工作，主要是为了，让数据流在管道的传输工程中全部传输过去，防止丢失数据
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 字节流转换字节数组
        byte[] data = baos.toByteArray();
        try {
            // 关闭读取流
            is.close();
            // 关闭写入流
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    /**
     * 获取本地图片的字节数
     * @param imgPath
     * @return
     */
    public static String pathSize(String imgPath) {
        File file = new File(imgPath);
        FileInputStream fis;
        int fileLen = 0;
        try {
            fis = new FileInputStream(file);
            fileLen = fis.available();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return bytes2kb(fileLen);
    }

    /**
     * 将获取到的字节数转换为KB，MB模式
     * @param bytes
     * @return
     */
    public static String bytes2kb(long bytes){
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if(returnValue > 1)
            return (returnValue + "MB");
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return (returnValue + "KB");
    }
}
