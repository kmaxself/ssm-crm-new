package ssm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
 
import javax.imageio.ImageIO;
 
public class Demo {
 /**
  * 读取一张图片的RGB值
  * @throws Exception
  * the local branch
  */
 public void getImagePixel(String image) throws Exception {
  int[] rgb = new int[3];
  File file = new File(image);
  BufferedImage bi = null;
  try {
   bi = ImageIO.read(file);
  } catch (Exception e) {
   e.printStackTrace();
  }
  int width = bi.getWidth();
  int height = bi.getHeight();
  int minx = bi.getMinX();
  int miny = bi.getMinY();
  System.out.println("width=" + width + ",height=" + height + ".");
  System.out.println("minx=" + minx + ",miniy=" + miny + ".");
  for (int i = minx; i < width; i++) {
   for (int j = miny; j < height; j++) {
    int pixel = bi.getRGB(i, j); //下面三行代码将一个数字转换为RGB数字
    rgb[0] = (pixel & 0xff0000) >> 16;
    rgb[1] = (pixel & 0xff00) >> 8;
    rgb[2] = (pixel & 0xff);
     System.out.println("i="+i+",j="+j+":("+rgb[0]+","+rgb[1]+","+rgb[2]+")");
   }
  }
 }
 
 /**
  * 返回屏幕色彩值
  * 
 * @param x
  * @param y
  * @return
  * @throws AWTException
  */
 public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
  Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
  rb = new Robot();
  Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
  Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
  System.out.println(di.width);
  System.out.println(di.height);
  Rectangle rec = new Rectangle(0, 0, di.width, di.height);
  BufferedImage bi = rb.createScreenCapture(rec);
  int pixelColor = bi.getRGB(x, y);
 
  return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
 }
 
 /**
  * @param args
  */
 public static void main(String[] args) throws Exception {
  int x = 0;
  Demo rc = new Demo();
  x = rc.getScreenPixel(100, 345);
  System.out.println(x + " - ");
  rc.getImagePixel("D:/untitled.png");
		/*String nameString="山东大学";
		String nameGetBytes;
		String nameURLEncoder="";
		try {
			nameGetBytes = new String(nameString.getBytes("UTF-8"));
			nameURLEncoder = URLEncoder.encode(nameGetBytes, "UTF-8");  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		
		System.out.println("utf-8 编码：" + nameURLEncoder) ; */


	}

}
