package com.solar.utils.qrcode;

import com.alibaba.druid.util.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.solar.utils.UUIDUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by hushaoge on 2016/11/28.
 */
public class QrcodeUtils {

    /**默认是黑色*/
    private static final int QRCOLOR = 0xFF000000;
    /**背景颜色*/
    private static final int BGWHITE = 0xFFFFFFFF;

    public static void createQRCodeToImage(String contents, int width, int height, File file){
        /**二维码的图片格式*/
        String format = "JPEG";
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        /** 内容所使用字符集编码*/
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToFile(bitMatrix, format, file);
        } catch (Exception e) {
        } finally {
        }
    }

    public static void createQRCodeToImage(String contents, int width, int height, OutputStream out){
        try {
            writeToStream(contents, width, height, out);
        } catch (Exception e) {
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param contents 内容
     * @param response
     */
    public static void createQRCode(String contents, int width, int height, HttpServletResponse response) {

        try {
            /**设置相应类型,告诉浏览器输出的内容为图片*/
            response.setContentType("image/jpeg");
            /**设置响应头信息，告诉浏览器不要缓存此内容*/
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);

            writeToStream(contents, width, height, response.getOutputStream());
        }catch (IOException e) {
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeToStream(String contents, int width, int height, OutputStream out){
        writeToStream(contents, width, height, null, null, out);
    }

    public static void writeToStream(String contents, int width, int height,String format,
                               Hashtable<EncodeHintType, String> hints, OutputStream out){
        if(format == null|| format.length()==0){
            /** 二维码的图片格式*/
            format = "JPEG";
        }
        if(hints == null || hints.isEmpty()){
            hints = new Hashtable<>();
            /**内容所使用字符集编码*/
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        }
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, out);
        } catch (Exception e) {
        }
    }


    /**
     * 生成带logo的二维码图片
     * @param qrUrl
     * @param productName
     */
    public static String getLogoQRCode(String qrUrl,String productName) {
//      String filePath = request.getSession().getServletContext().getRealPath("/") + "resources/images/logoImages/llhlogo.png";
        //filePath是二维码logo的路径，但是实际中我们是放在项目的某个路径下面的，所以路径用上面的，把下面的注释就好
        String filePath = "D://1.jpg";
        String content = qrUrl;
        try {
            BufferedImage bim = getQRCODEBufferedImage(content, BarcodeFormat.QR_CODE, 400, 400, getDecodeHintType());
            return addLogoQRCode(bim, new File(filePath), new LogoConfig(), productName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给二维码图片添加Logo
     * @param bim
     * @param logoPic
     * @param logoConfig
     * @param productName
     * @return
     */
    public static String addLogoQRCode(BufferedImage bim, File logoPic, LogoConfig logoConfig, String productName) {
        try {
            /** * 读取二维码图片，并构建绘图对象 */
            BufferedImage image = bim;
            Graphics2D g = image.createGraphics();

            /** * 读取Logo图片 */
            BufferedImage logo = ImageIO.read(logoPic);
            /** * 设置logo的大小,本人设置为二维码图片的20%,因为过大会盖掉二维码 */
            int widthLogo = logo.getWidth(null)>image.getWidth()*3/10?(image.getWidth()*3/10):logo.getWidth(null),
                    heightLogo = logo.getHeight(null)>image.getHeight()*3/10?(image.getHeight()*3/10):logo.getWidth(null);

            /** * logo放在中心 */
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            /** * logo放在右下角 * int x = (image.getWidth() - widthLogo); * int y = (image.getHeight() - heightLogo); */

            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            // g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
            // g.setStroke(new BasicStroke(logoConfig.getBorder()));
            // g.setColor(logoConfig.getBorderColor());
            // g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();

            //把商品名称添加上去，商品名称不要太长哦，这里最多支持两行。太长就会自动截取啦
            if (productName != null && !productName.equals("")) {
                //新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                /**画二维码到新的面板*/
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                /**画文字到新的面板*/
                outg.setColor(Color.BLACK);
                /**字体、字型、字号*/
                outg.setFont(new Font("宋体",Font.BOLD,30));
                int strWidth = outg.getFontMetrics().stringWidth(productName);
                if (strWidth > 399) {
                // //长度过长就截取前面部分
                // outg.drawString(productName, 0, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 5 ); //画文字
                    //长度过长就换行
                    String productName1 = productName.substring(0, productName.length()/2);
                    String productName2 = productName.substring(productName.length()/2, productName.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                    outg.drawString(productName1, 200  - strWidth1/2, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 12 );
                    BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体",Font.BOLD,30)); //字体、字型、字号
                    outg2.drawString(productName2, 200  - strWidth2/2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight())/2 + 5 );
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                }else {
                    outg.drawString(productName, 200  - strWidth/2 , image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 12 ); //画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            logo.flush();
            image.flush();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "png", baos);

            //二维码生成的路径，但是实际项目中，我们是把这生成的二维码显示到界面上的，因此下面的折行代码可以注释掉
            //可以看到这个方法最终返回的是这个二维码的imageBase64字符串
            //前端用 <img src="data:image/png;base64,${imageBase64QRCode}"/> 其中${imageBase64QRCode}对应二维码的imageBase64字符串
            ImageIO.write(image, "png", new File("D://" + UUIDUtils.getUUID() + ".png"));

            String imageBase64QRCode =  Base64.byteArrayToBase64(baos.toByteArray());
            baos.close();
            return imageBase64QRCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码bufferedImage图片
     * @param content 编码内容
     * @param barcodeFormat 编码类型
     * @param width 图片宽度
     * @param height 图片高度
     * @param hints 设置参数
     * @return
     */
    public static BufferedImage getQRCODEBufferedImage(String content, BarcodeFormat barcodeFormat,
                                                       int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码的格式参数
     * @return
     */
    public static Map<EncodeHintType, Object> getDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);

        return hints;
    }
}