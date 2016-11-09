/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chi.cuadrada;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Angelo
 */
public class Test {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        String[][] table = new String[10][4];//crea matriz de 10 renglones y 4 columnas

//
//        final JFrame frame = new JFrame("Open File Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//        JButton openButton = new JButton("Open");
//        openButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser chooser = new JFileChooser();
//                if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
//                    setPath(chooser.getSelectedFile().getAbsolutePath());
//                }
//            }
//        });
//        frame.add(openButton);
//        frame.pack();
//        frame.setLocationByPlatform(true);
//        frame.setVisible(true);
        String line;
        int n = 0;
        /*OBTEN N*/
         try (
                FileInputStream fis = new FileInputStream("C:\\Users\\Angelo\\Downloads\\Escuela\\QUINTO SEMESTRE\\Lenguajes y automatas 1\\Chi Cuadrada\\src\\NumbersChiCuadrada.dat");
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);) {
            while ((line = br.readLine()) != null) {
                n++;
            }
        }
         /*CREA ARREGLO*/
         float[] number = new float[n];//arreglo para guardar cada numero
         /*LLENA ARREGLO CON NUMEROS*/
         n = 0;//reinicia contador
         try (
                FileInputStream fis = new FileInputStream("C:\\Users\\Angelo\\Downloads\\Escuela\\QUINTO SEMESTRE\\Lenguajes y automatas 1\\Chi Cuadrada\\src\\NumbersChiCuadrada.dat");
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);) {
            while ((line = br.readLine()) != null) {
                number[n] = Float.parseFloat(line);
                n++;
            }
        }
         
        /*GENERA LOS INTERVALOS*/
        float[] interval = new float[10];//intervalos para Oi
        float sumInter = 0;//sum to 0.1+0.1+0.1 asignar a cada intervalo
        for (int i = 0; i < interval.length; i++) {
            interval[i] = sumInter;
            sumInter += 0.10f;
        }
        /*GUARDA LA CANTIDAD DE NUMEROS DENTRO DE LOS INTERVALOS*/
        int[] Oi = new int[10];
        int k = 0;
        /*COMPARA CADA NUMERO*/
        for (int i = 0; i < number.length; i++) {
            /*CON EL INTERVALO J - J+1*/
            for (int j = 0; j < interval.length; j++) {
                /*SI ES MENOR AL LARGO ENTONCES NO HA LLEGADO AL LIMITE (1)*/
                if (j + 1 < interval.length) {
                    if (number[i] >= interval[j] && number[i] <= interval[j + 1]) {
                        table[j][0] = interval[j] + "-" + interval[j + 1];
                        Oi[j] += 1;
                        table[j][1] = Oi[j] + "";
                    }
                /*SI LLEGO AL LIMITE J ENTONCES VA DESDE EL INTERVALO[9] AL 1 (0.9-1)*/
                } else if (number[i] >= interval[j] && number[i] <= 1) {
                    table[j][0] = interval[j] + "- 1.000";
                    Oi[j] += 1;
                    table[j][1] = Oi[j] + "";
                }
            }
        }
        
        /*LLENA LA TERCERA COLUMNA (NO. 2 EN NOTACION ARRAY) (Ei)*/
        float Ei = (float) (number.length / Math.sqrt(number.length));//n/m donde m = sqrt(n)
        for (int i = 0; i < table.length; i++) {
            table[i][2] = Ei+"";
        }
        /*CALCULA EI CUADRADA Y SUMA LA ULTIMA COLUMNA Xo^2*/
        float sum = 0;//sumatoria de Xo^2
        for (int i = 0; i < table.length; i++) {
            table[i][3] = Math//la ultima columna [i][3] Xo^2
                    .pow(//eleva al cuadrado (ei-oi)^2
                            Float.parseFloat(table[i][2])-Float.parseFloat(table[i][1])
                            ,
                            2
                    )
                    /
                    Float.parseFloat(table[i][2])+"";//dividelo entre ei
            sum+= Float.parseFloat(table[i][3]);
        }
        
        /*IMPRIME MATRIZ*/
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("|\t\t\t\t" + table[i][j] + "\t\t\t\t|");
            }
            System.out.println();
        }
        System.out.println("SUMATORIA DE Xo^2 = "+sum);

    }
}
