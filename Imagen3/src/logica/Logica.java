
package logica;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import ventana.Ventana;

public class Logica {
    public Ventana v;
    BufferedImage Imagen;
    BufferedImage ImagenAModificar;
    public Cromosoma real = new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList());
    public ArrayList<Cromosoma> generados = new ArrayList();
    public ArrayList<Cromosoma> dis = new ArrayList();
    ArrayList <Necesario> n = new ArrayList(), n2 = new ArrayList(), n3 = new ArrayList();
    int v1;
    String ur;

    public void setVentana(Ventana v) {
        this.v = v;
    }

    public Cromosoma imagen(String url) throws FileNotFoundException, IOException {
        try {
            InputStream Input;
            if (url == "") {
                ur = "src/pelusis.png";
                Input = new FileInputStream("src/pelusis.png");
            } else {
                ur = url;
                Input = new FileInputStream(url);
            }

            ImageInputStream ImageInput = ImageIO.createImageInputStream(Input);
            BufferedImage ImagenL = ImageIO.read(ImageInput);
            this.Imagen = ImagenL;
            this.ImagenAModificar = ImagenL;
            this.v.d.url = url;
            this.v.d.image = ImagenL;

            for (int y = 0; y < ImagenL.getHeight(); y++) {
                for (int x = 0; x < ImagenL.getWidth(); x++) {
                    int srcPixel = ImagenL.getRGB(x, y);
                    Color c = new Color(srcPixel);
                    real.red.add(aBinario(c.getRed()));
                    real.green.add(aBinario(c.getGreen()));
                    real.blue.add(aBinario(c.getBlue()));
                }
            }

            System.out.println("Ya guarde todo");
        } catch (IOException e) {
            System.out.println("Hay un error al cargar los pixeles de la imagen");
        }

        return this.real;
    }

    public ArrayList<Cromosoma> generarcoloresRandom(Cromosoma real) {
        Random random = new Random();
        generados.add(new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList()));
        int red, green, blue;
        for (int i = 0; i < real.red.size(); i++) {
            red = random.nextInt(256);
            green = random.nextInt(256);
            blue = random.nextInt(256);
            generados.get(0).red.add(aBinario(red));
            generados.get(0).green.add(aBinario(green));
            generados.get(0).blue.add(aBinario(blue));
        }
        //guarda la primera solucion aleatoria
        return this.generados;
    }
   
    /*Metodo que realiza la seleccion por ruleta, recibe un plano de color, y 
     las distancias ya halladas para respectivoplano*/
    public void ruleta(ArrayList<String> generados, ArrayList<String> distancia, int op) {
        for (int i = 0; i < generados.size(); i++) {
            String hijo;
            int parejaAleatoria = (int) (Math.random() * (generados.size() - 2));
            hijo = generados.get(i).substring(0, 4) + generados.get(parejaAleatoria).substring(4, 8);
            if(op == 1){
                generados.set(n.get(n.size()-1).getPosicion(), hijo);
            }
            if(op==2){
                generados.set(n2.get(n.size()-1).getPosicion(), hijo);
            }else{
                generados.set(n3.get(n.size()-1).getPosicion(), hijo);
            } 
        }
    }
    
    
    public void agregaN(){
        for(int i=0; i < dis.get(0).getRed().size(); i++){
            n.add(new Necesario(decimal(Long.valueOf(dis.get(0).getRed().get(i))), i));
            Collections.sort(n, Necesario.ferom);
        }
        for(int i=0; i < dis.get(0).getGreen().size(); i++){
            n2.add(new Necesario(decimal(Long.valueOf(dis.get(0).getGreen().get(i))), i));
            Collections.sort(n2, Necesario.ferom);
        }
        for(int i=0; i < dis.get(0).getBlue().size(); i++){
            n3.add(new Necesario(decimal(Long.valueOf(dis.get(0).getBlue().get(i))), i));
            Collections.sort(n3, Necesario.ferom);
        }
  
    }

    public void mutaciones() {
        int xPixel, salida;
        String mutacion;
        for (int i = 0; i < generados.get(0).red.size() * 0.25; i++) {
            xPixel = (int) (Math.random() * (generados.size() - 2));
            salida = (int) (Math.random() * (250));
            mutacion = aBinario(salida);
            generados.get(0).getRed().set(xPixel, mutacion);
        }
        for (int i = 0; i < generados.get(0).green.size() * 0.25; i++) {
            xPixel = (int) (Math.random() * (generados.size() - 2));
            salida = (int) (Math.random() * (250));
            mutacion = aBinario(salida);
            generados.get(0).getGreen().set(xPixel, mutacion);
        }
        for (int i = 0; i < generados.get(0).blue.size() * 0.25; i++) {
            xPixel = (int) (Math.random() * (generados.size() - 2));
            salida = (int) (Math.random() * (250));
            mutacion = aBinario(salida);
            generados.get(0).getBlue().set(xPixel, mutacion);
        }
    }

    /*Este metodo halla las distancias entre los pixeles de un plano real con el generado
     recibe el plano real de color ejm: realRed, el generado ejm: generatedRed y el 
     arraylist en el que guardara las distancias ejm: disRed*/
    public void hallarDistancia(int pos) {
        dis.add(new Cromosoma(new ArrayList(), new ArrayList(), new ArrayList()));
        for (int i = 0; i < real.red.size(); i++) {
            dis.get(pos).red.add(aBinario(Math.abs(decimal(Long.valueOf(real.red.get(i))) - decimal(Long.valueOf(generados.get(pos).red.get(i))))));
            if(n.size()>0){
               n.get(busca(i)).setDistancia(decimal(Long.valueOf(dis.get(pos).red.get(i)))); 
            Collections.sort(n, Necesario.ferom);
            }
            
            dis.get(pos).green.add(aBinario(Math.abs(decimal(Long.valueOf(real.green.get(i))) - decimal(Long.valueOf(generados.get(pos).green.get(i))))));
            if(n2.size()>0){
            n2.get(busca(i)).setDistancia(decimal(Long.valueOf(dis.get(pos).green.get(i))));
            Collections.sort(n2, Necesario.ferom);
            }
            dis.get(pos).blue.add(aBinario(Math.abs(decimal(Long.valueOf(real.blue.get(i))) - decimal(Long.valueOf(generados.get(pos).blue.get(i))))));
            if(n3.size()>0){
            n3.get(busca(i)).setDistancia(decimal(Long.valueOf(dis.get(pos).blue.get(i))));
            Collections.sort(n3, Necesario.ferom);
            }
            dis.get(pos).setPosicion(i); 
        }
    }
    
    public int busca(int k){
        for(int i=0; i<n.size();i++){
            if(n.get(i).posicion == k){
                return i; 
            }
        }
        return -1;
    }

    /*Nos dice el desempeño en general de toda la poblacion de pixeles*/
    public void fitnessGeneral(int pos) {
        int coincidencias = 0;
        for (int i = 0; i < dis.get(pos).red.size(); i++) {
            if (decimal(Long.valueOf(dis.get(pos).red.get(i))) < 20) {
                coincidencias = 1 + coincidencias;
            }
        }
        for (int i = 0; i < dis.get(pos).green.size(); i++) {
            if (decimal(Long.valueOf(dis.get(pos).green.get(i))) < 20) {
                coincidencias = 1 + coincidencias;
            }
        }
        for (int i = 0; i < dis.get(pos).blue.size(); i++) {
            if (decimal(Long.valueOf(dis.get(pos).blue.get(i))) < 20) {
                coincidencias = 1 + coincidencias;
            }
        }
        generados.get(pos).setFitness((coincidencias / 3f) / generados.get(pos).red.size());
        v.resulFitness.setText("" +generados.get(pos).getFitness()+ "");
        v.coincidencia.setText("Hay "+(coincidencias / 3)+" pixeles aceptables de los "+generados.get(pos).red.size()+" posibles");
    }

    public ArrayList<Cromosoma> imagenModificada(BufferedImage Imagen, int pos) {
        this.ImagenAModificar = Imagen;
        int contador = 0;
        while (contador < ImagenAModificar.getHeight() * ImagenAModificar.getWidth()) {
            for (int y = 0; y < ImagenAModificar.getHeight(); y++) {
                for (int x = 0; x < ImagenAModificar.getWidth(); x++) {
                    int pixel = ImagenAModificar.getRGB(x, y);
                    Color color = new Color(pixel, true);
                    color = new Color(decimal(Long.valueOf(generados.get(pos).red.get(contador))), decimal(Long.valueOf(generados.get(pos).green.get(contador))), decimal(Long.valueOf(generados.get(pos).blue.get(contador))));
                    ImagenAModificar.setRGB(x, y, color.getRGB());
                    contador++;
                }
            }
        }
        generados.get(0).setImagen(ImagenAModificar);
        return generados;
    }

    public void graficar(int pos) {
        //this.v.d.image = Imagen;
        this.v.d.url = ur;
        this.v.d.imagenModificada = generados.get(pos).imagen;
        this.v.d.repaint();
    }

    public void llamartodo(Cromosoma r, ArrayList<Cromosoma> g) {
        dis.clear();
        this.real = r;
        this.generados = g;
        hallarDistancia(0);
        agregaN();
        fitnessGeneral(0);
        graficar(0);
        ruleta(generados.get(0).getRed(), dis.get(0).getRed(),1);
        ruleta(generados.get(0).getGreen(), dis.get(0).getGreen(),2);
        ruleta(generados.get(0).getBlue(), dis.get(0).getBlue(),3);
        mutaciones();
        imagenModificada(this.Imagen, 0);
        v1 = Integer.parseInt(v.iteraciones.getText().trim()) + 1;
        v.iteraciones.setText("" + v1 + "");
    }


    public String aBinario(int num) {
        String binario = "";
        if (num > 0) {
            while (num > 0) {
                if (num % 2 == 0) {
                    binario = "0" + binario;
                } else {
                    binario = "1" + binario;
                }
                num = (int) num / 2;
            }
        } else if (num == 0) {
            binario = "0";
        }
        if (binario.length() < 8) {
            int falta = (8 - binario.length());
            for (int j = 0; j < falta; j++) {
                binario = "0" + binario;  //para que el valor binario sea de 8 bits
            }
        }
        return binario;
    }

    public int decimal(long binario) {
        int decimal = 0;
        int digito;
        final long DIVISOR = 10;
        for (long i = binario, j = 0; i > 0; i /= DIVISOR, j++) {
            digito = (int) (i % DIVISOR);
            if (digito != 0 && digito != 1) {
                return -1;
            }
            decimal += digito * Math.pow(2, j);
        }
        return decimal;
    }
}
