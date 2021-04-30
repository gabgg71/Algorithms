/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imagenreal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author mep77
 */
public class Imagen {

     public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
    
     try{
         
         
         InputStream Input = new FileInputStream("images.png");
         
         ImageInputStream ImageInput= ImageIO.createImageInputStream(Input);
         
         BufferedImage ImagenL= ImageIO.read(ImageInput);
         
         
         int alto = ImagenL.getHeight();
         int ancho= ImagenL.getWidth();
         
         
     
         
         for (int y = 0; y < ImagenL.getHeight(); y++) {
             for (int x = 0; x < ImagenL.getWidth(); x++) {
                 
                 int srcPixel= ImagenL.getRGB(x, y);
                 
                 Color c = new Color(srcPixel);
                 int valR= c.getRed();
                 int valG= c.getGreen();
                 int valB= c.getBlue();
                 
                 
                 System.out.println("r"+valR+"g"+valG+"b"+valB);
             }
             System.out.println("");
         }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     }catch(IOException e){
         
     } 
          
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     }
     
    
}
