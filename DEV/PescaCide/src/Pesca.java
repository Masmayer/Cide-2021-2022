import java.io.*;
import java.util.Scanner;
import java.util.zip.InflaterOutputStream;

public class Pesca {
    int cont = 1;
    String campo1="";
    float campo2=0;
    float campo3=0;
    float campo4=0;





    private void altaUsuario() throws IOException {

            FileReader fr= new FileReader("src/usuaris");
            int leedor = fr.read();
    }


    boolean comprobarUsuario(String usuario) throws IOException {
        // Variables
        String conversor = "";

        // Iniciamos la clase Filereader
        FileReader fr=new FileReader("src/usuaris");
        int leedor = fr.read();

        /**
         * Inicializamos el booleano que utilizaremos para ver si está el separador, en este caso el '#' y
         * 1    11111321el booleano para ver si se ha encontrado el usuario.
         */
        boolean estaSeparado = true;
        boolean estaAqui = false;
        int contadorHash = 0;
        int contadorLinea = 0;
        while (leedor!=-1 ){
            while (leedor!='\n' || leedor!='\r'){
                while(estaSeparado){
                    if(leedor!='#'){
                        conversor = conversor + (char)leedor;

                    }else {
                        if (conversor.equals(usuario)){
                            return true;
                        }
                        estaSeparado = false;
                        contadorHash++
                    }
                    leedor = fr.read();
                }
                contadorLinea++;
                estaSeparado = true;
                conversor="";
            }
        }
            fr.close();
            return false;
    }

    public void read() throws IOException {

    }
}
