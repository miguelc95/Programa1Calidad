import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author miguelcuellar
 */
//&p-Totales
 class Totales{
   public int iCantArchivos = 0;
   public int iCantLineasBlancas = 0;
   public int iCantLineasContenido = 0;
//&i
  public Totales(){
     iCantArchivos = 0;
     iCantLineasBlancas = 0;
     iCantLineasContenido = 0;
   }
   //&i
   public void agregarBlancas(int b){
     iCantLineasBlancas += b;
   }
   //&i
   public void agregarContenido(int c){
     iCantLineasContenido += c;
   }
   //&i
   public void agregarArchivo(){
     iCantArchivos++;
   }
   //&i
   public int getNumArchivos(){
     return iCantArchivos;
   }
   //&i
   public int getLineasBlancas(){
     return iCantLineasBlancas;
   }
   //&i
   public int getLineasContenido(){
     return iCantLineasContenido;
   }
 }
//&p-DatosArchivo
 class DatosArchivo{
   public String sNombre = " ";
   public int iLineasBlanco = 0;
   public int iLineasContenido = 0;
//&i
   public DatosArchivo(){
     sNombre = " ";
     iLineasBlanco = 0;
     iLineasContenido = 0;
   }
//&i
   public DatosArchivo(String n){
     sNombre = n;
     iLineasBlanco = 0;
     iLineasContenido = 0;
   }
//&i
   public void setNombre(String n){
     sNombre = n;
   }
//&i
   public String getNombre(){
     return sNombre;
   }
//&i
   public int getLineasBlanco(){
     return iLineasBlanco;
   }
//&i
   public int getLineasContenido(){
     return iLineasContenido;
   }
//&i
   public boolean Contar(){
     try {
            BufferedReader in = new BufferedReader(new FileReader(sNombre));
          String linea;
          while((linea = in.readLine()) != null) {
            if(linea.trim().equals("") || linea.trim().equals("\t") || linea.trim().equals(" ")) {
              iLineasBlanco++;
            }
            else {
              iLineasContenido++;
            }
          }
          in.close();
          return true;
        }
        catch(IOException e){
          System.out.println("ERROR - El archivo " + sNombre + " no existe.");
          return false;
        }
   }


 }
 //&p-Programa2Calidad
public class Programa2Calidad {
//&i
    public static void main(String[] args) {

        ArrayList<DatosArchivo> Archivos = new ArrayList<DatosArchivo>();
        Totales total = new Totales();
        System.out.println("Ingrese los nombres de los archivos separados por espacios");
        Scanner nomArchivos = new Scanner(System.in);
        String  inputNombres = nomArchivos.nextLine();
        String[] nombres = inputNombres.split("\\s+");

        for (int ix = 0; ix<nombres.length; ix++) {
          DatosArchivo arch = new DatosArchivo();
          arch.setNombre(nombres[ix]);
          Archivos.add(arch);
        }
        for (int iy = 0; iy<Archivos.size(); iy++) {
          if (Archivos.get(iy).Contar()) {
            System.out.println("Nombre del archivo: " + Archivos.get(iy).getNombre());
            System.out.println("Lineas en blanco: " + Archivos.get(iy).getLineasBlanco());
            System.out.println("Lineas con contenido: " + Archivos.get(iy).getLineasContenido());
            System.out.println("---------------------------------------------");
            total.agregarBlancas(Archivos.get(iy).getLineasBlanco());
            total.agregarContenido(Archivos.get(iy).getLineasContenido());
            total.agregarArchivo();


          }else{
            System.out.println("El archivo con nombre: "+Archivos.get(iy).getNombre()+"No existe o esta vacío.");
            Archivos.remove(iy);
          }

        }
        System.out.println("TOTALES:");
      System.out.println("Número de archivos analizados: " + total.getNumArchivos());
      System.out.println("Lineas en blanco: " + total.getLineasBlancas());
      System.out.println("Lineas con contenido: " + total.getLineasContenido());
    }

}
