package com.lorent.vovo.util;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PicScale {

    private static final double WORK_LOAD_FACTOR = 0.265;
    private static final double LANCZOS_SUPPORT = 3;
    private static final double LANCZOS_WINDOW = 3;
    private static final double LANCZOS_SCALE = 1;
    private static final double LANCZOS_BLUR = 1;
    private static final double EPSILON = 1.0e-6;
    private boolean sbool = false;
    private static PicScale instance = new PicScale();
    
    public static PicScale getInstance(){
    	return instance;
    }

    private class ContributionInfo {
        private double weight;
        private int pixel;
    }

    public PicScale() {}

    public PicScale(boolean sbool) {
        this.sbool=sbool;
    }
    
    public static void main(String[] args){
    	PicScale p = new PicScale();
    	String src = "c:/head_3.jpg";
    	String bin = "c:/head_30.jpg";
    	p.Save(src, bin, 60, 60);
    }

    /*
     * 参数1(from),参数2(to),参数3(宽),参数4(高)
     * */
    public boolean Save(String src,String bin,int X,int Y) {
        try {
            ImageIO.write(
                resizeImage(ImageIO.read(new File(src)),X,Y,sbool),
                "PNG",
                new File(bin)
            );
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public BufferedImage resizeImage(BufferedImage src, int X, int Y){
    	return resizeImage(src, X, Y,sbool);
    }

    public BufferedImage resizeImage(BufferedImage src, int X, int Y,boolean bool) {
        double nX=1.0;
        double nY=1.0;

        if (bool) {
            if(X>Y){
                if(src.getWidth()>src.getHeight()){
                    double tmp=Double.parseDouble(String.format("%.5f", src.getWidth()/src.getHeight()/1.00d));
                    double tmps=Double.parseDouble(String.format("%.5f", X / Y / 1.00d));
                    nX = tmp*tmps;
                } else {
                    double tmp=Double.parseDouble(String.format("%.5f", src.getHeight()/src.getWidth()/1.00d));
                    double tmps=Double.parseDouble(String.format("%.5f", X / Y / 1.00d));
                    nY += tmp*tmps;
                }
            } else {
                if(src.getWidth()>src.getHeight()){
                    double tmp=Double.parseDouble(String.format("%.5f", src.getWidth()/src.getHeight()/1.00d));
                    double tmps=Double.parseDouble(String.format("%.5f", Y /X / 1.00d));
                    nX += tmp*tmps;
                } else {
                    double tmp=Double.parseDouble(String.format("%.5f", src.getHeight()/src.getWidth()/1.00d));
                    double tmps=Double.parseDouble(String.format("%.5f", Y /X / 1.00d));
                    nY = tmp*tmps;
                }
            }
        }

        double newX = ( X * nX ) / src.getWidth();
        double newY = ( Y * nY ) / src.getHeight();

        BufferedImage resizeImage = new BufferedImage(X, Y,src.getType());
        BufferedImage filterImage = null;
        if (newX * newY > WORK_LOAD_FACTOR) {
            filterImage = new BufferedImage(X, src.getHeight(),src.getType());
            horizontalFilter(src, filterImage, newX);
            verticalFilter(filterImage, resizeImage, newY);
        } else {
            filterImage = new BufferedImage(src.getWidth(), Y,src.getType());
            verticalFilter(src, filterImage, newY);
            horizontalFilter(filterImage, resizeImage, newX);
        }
        return resizeImage;
    }

    private void verticalFilter(BufferedImage image, BufferedImage resizeImage, double yFactor) {
        double scale = Math.max(1.0 / yFactor, 1.0);
        double support = scale * LANCZOS_SUPPORT;
        if (support < 0.5) {
            support = 0.5;
            scale = 1.0;
        }
        scale = 1.0 / scale;
        for (int y = 0; y < resizeImage.getHeight(); y++) {
            double center = (y + 0.5) / yFactor;
            int start = (int) (Math.max(center - support - EPSILON, 0.0) + 0.5);
            int stop = (int) (Math.min(center + support, image.getHeight()) + 0.5);
            double density = 0.0;
            ContributionInfo[] contribution = new ContributionInfo[stop - start];
            int n;
            for (n = 0; n < (stop - start); n++) {
                contribution[n] = new ContributionInfo();
                contribution[n].pixel = start + n;
                contribution[n].weight = getResizeFilterWeight(scale * (start + n - center + 0.5));
                density += contribution[n].weight;
            }
            if ((density != 0.0) && (density != 1.0)) {
                density = 1.0 / density;
                for (int i = 0; i < n; i++)
                    contribution[i].weight *= density;
            }
            for (int x = 0; x < resizeImage.getWidth(); x++) {
                double red = 0;
                double green = 0;
                double blue = 0;
                for (int i = 0; i < n; i++) {
                    double alpha = contribution[i].weight;
                    int rgb = image.getRGB(x, contribution[i].pixel);
                    red += alpha * ((rgb >> 16) & 0xFF);
                    green += alpha * ((rgb >> 8) & 0xFF);
                    blue += alpha * (rgb & 0xFF);
                }
                int rgb = roundToQuantum(red) << 16    | roundToQuantum(green) << 8 | roundToQuantum(blue);
                resizeImage.setRGB(x, y, rgb);
            }
        }
    }

    private void horizontalFilter(BufferedImage image,BufferedImage resizeImage, double xFactor) {
        double scale = Math.max(1.0 / xFactor, 1.0);
        double support = scale * LANCZOS_SUPPORT;
        if (support < 0.5) {
            support = 0.5;
            scale = 1.0;
        }
        scale = 1.0 / scale;
        for (int x = 0; x < resizeImage.getWidth(); x++) {
            double center = (x + 0.5) / xFactor;
            int start = (int) (Math.max(center - support - EPSILON, 0.0) + 0.5);
            int stop = (int) (Math.min(center + support, image.getWidth()) + 0.5);
            double density = 0.0;
            ContributionInfo[] contribution = new ContributionInfo[stop - start];
            int n;
            for (n = 0; n < (stop - start); n++) {
                contribution[n] = new ContributionInfo();
                contribution[n].pixel = start + n;
                contribution[n].weight = getResizeFilterWeight(scale
                        * (start + n - center + 0.5));
                density += contribution[n].weight;
            }
            if ((density != 0.0) && (density != 1.0)) {
                density = 1.0 / density;
                for (int i = 0; i < n; i++)
                    contribution[i].weight *= density;
            }
            for (int y = 0; y < resizeImage.getHeight(); y++) {
                double red = 0;
                double green = 0;
                double blue = 0;
                for (int i = 0; i < n; i++) {
                    double alpha = contribution[i].weight;
                    int rgb = image.getRGB(contribution[i].pixel, y);
                    red += alpha * ((rgb >> 16) & 0xFF);
                    green += alpha * ((rgb >> 8) & 0xFF);
                    blue += alpha * (rgb & 0xFF);
                }
                int rgb = roundToQuantum(red) << 16    | roundToQuantum(green) << 8 | roundToQuantum(blue);
                resizeImage.setRGB(x, y, rgb);
            }
        }
    }

    private double getResizeFilterWeight(double x) {
        double blur = Math.abs(x) / LANCZOS_BLUR;
        double scale = LANCZOS_SCALE / LANCZOS_WINDOW;
        scale = sinc(blur * scale);
        return scale * sinc(blur);
    }

    private double sinc(double x) {
        if (x == 0.0)
            return 1.0;
        return Math.sin(Math.PI * x) / (Math.PI * x);
    }

    private int roundToQuantum(double value) {
        if (value <= 0.0)
            return 0;
        if (value >= 255)
            return 255;
        return (int) (value + 0.5);
    }
}