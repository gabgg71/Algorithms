/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Colores;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author mep77
 */
public class colores {
 public Color generarcoloresRandom(Color mix) {
    Random random = new Random();
    int red = random.nextInt(256);
    int green = random.nextInt(256);
    int blue = random.nextInt(256);

    // mix the color
    if (mix != null) {
        red = (red + mix.getRed()) / 2;
        green = (green + mix.getGreen()) / 2;
        blue = (blue + mix.getBlue()) / 2;
    }

    Color color = new Color(red, green, blue);
    return color;
}
}
