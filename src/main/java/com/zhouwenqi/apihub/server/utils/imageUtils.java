package com.zhouwenqi.apihub.server.utils;

import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.math.BigDecimal;

/**
 * Created by zhouwenqi on 2018/7/11.
 */
public class imageUtils {
    /**
     * 根据上传的图片字节重新裁切图片大小
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param fileImage 图片字节数组
     * @param width 指定裁切宽度
     * @param height 指定裁切高度
     * @param imageFormat 裁切后的图片格式
     * @return
     */
    public static boolean resize(String filePath, String fileName, byte[] fileImage, int width, int height, String imageFormat) throws Exception{

        // BufferedImage bufferedImage = ImageIO.read(fileStream);
        /**
         * 处理透明32位透明png转换后alpha通道丢失的问题
         */
        int[] RGB_MASKS={0xFF0000, 0xFF00, 0xFF};
        ColorModel colorModel = new DirectColorModel(32,RGB_MASKS[0],RGB_MASKS[1],RGB_MASKS[2]);
        Image image = Toolkit.getDefaultToolkit().createImage(fileImage);
        PixelGrabber pixelGrabber = new PixelGrabber(image,0,0,-1,-1,true);
        pixelGrabber.grabPixels();
        int pWidth = pixelGrabber.getWidth();
        int pHeight = pixelGrabber.getHeight();
        DataBuffer dataBuffer = new DataBufferInt((int[])pixelGrabber.getPixels(), pixelGrabber.getWidth()*pixelGrabber.getHeight());
        WritableRaster raster = Raster.createPackedRaster(dataBuffer,pWidth,pHeight,pWidth,RGB_MASKS,null);
        BufferedImage bufferedImage = new BufferedImage(colorModel,raster,false,null);

        // 开始定义裁切尺寸
        int w = 0;
        int h = 0;
        int imgWidth = bufferedImage.getWidth();
        int imgHeight = bufferedImage.getHeight();
        if(bufferedImage.getWidth()<width){
            w = bufferedImage.getWidth();
        }
        if(bufferedImage.getHeight()<height){
            h = bufferedImage.getHeight();
        }
        if(imgWidth>width || imgHeight>height){
            if(imgWidth>imgHeight){
                float reduce = new BigDecimal(imgWidth).divide(new BigDecimal(width),2,BigDecimal.ROUND_HALF_UP).floatValue();
                w = (int)(imgWidth/reduce);
                h = (int)(imgHeight/reduce);
            }else{
                float reduce = new BigDecimal(imgHeight).divide(new BigDecimal(height),2,BigDecimal.ROUND_HALF_UP).floatValue();
                w = (int)(imgWidth/reduce);
                h = (int)(imgHeight/reduce);
            }
        }
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }

        // 保存图片
        File outFile = new File(filePath+fileName);
        ResampleOp resampleOp = new ResampleOp(w,h);
        BufferedImage rescaledImage = resampleOp.filter(bufferedImage,null);
        ImageIO.write(rescaledImage, imageFormat, outFile);
        return true;
    }

    /**
     * 返回一个随机图片uri
     * @return
     */
    public static String getRandomImage(){
        int index= (int)(Math.random()*6);
        return "/images/face/"+index+".png";
    }
}
