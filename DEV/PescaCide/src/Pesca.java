import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.InflaterOutputStream;

public class Pesca {
    int cont = 1;
    String campo1="";
    float campo2=0;
    float campo3=0;
    float campo4=0;





    private void altaUsuario(){
        System.out.println("Indique Nombre de Usuario: ");
        Scanner sc = new Scanner(System.in);
        String usuario = sc.nextLine();
        try {
            FileWriter fw = new FileWriter("src\\archivos\\usuarios.txt", true);
            if (!comprobarUsuario(usuario)){
                fw.write("#" + usuario + "#\n");
                fw.close();
            }else{
                throw new Exception("L'Usuari ja està registrat!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    boolean comprobarUsuario(String usuario) throws IOException {
        // Variables
        String conversor = "";

        // Iniciamos la clase Filereader
        FileReader fr=new FileReader("src/usuaris");
        int leedor = fr.read();

        // Inicializamos el booleano que utilizaremos para ver si está el separador, en este caso el '#'
        boolean estaSeparado = true;

        while (leedor!=-1){
            while (leedor!="\n"){
                while (estaSeparado){
                    if (leedor == '#')
                    conversor += (char)leedor;


                }

            }
            while(estaSeparado){
                if(leedor!='#' && leedor!='\n'){
                    conversor = conversor + (char)leedor;

                }else {
                    estaSeparado = false;
                }
                leedor = fr.read();
            }
            estaSeparado = true;
            conversor="";
        }
        fr.close();
        return false;
    }

    public void read() throws IOException {

    }
}
