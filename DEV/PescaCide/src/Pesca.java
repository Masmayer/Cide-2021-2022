import java.io.*;
import java.nio.charset.StandardCharsets;

public class Pesca {
    public boolean buscarPez(String pez, String path) throws IOException {
        int diferenciador;
        String comprobador="";
        FileInputStream lector = new FileInputStream(path);
        int contadorLinea=0;
        int contador=0;
        String campo1 = "";
        String campo2="";
        float campo3 = 0;
        int lineadeRegistros=0;

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    contador++;
                    if (contador == 2){
                        campo1 = comprobador;
                        comprobador="";
                    }

                    if (contador == 3){
                        campo2 = comprobador;
                        comprobador="";
                    }

                    if (contador == 4){
                        campo3 = Float.parseFloat(comprobador);
                        comprobador="";
                    }
                }
            }else{
                contador=0;
                if (campo1.equals(pez)){
                    lector.close();
                    return true;
                }
                contadorLinea++;
            }
        }
        lector.close();
        return false;

    }
    public float pesoGetLinea(int linea, String path) throws IOException {
        int diferenciador;
        String comprobador="";
        FileInputStream lector = new FileInputStream(path);
        int contadorLinea=0;
        int contador=0;
        String campo1 = "";
        String campo2="";
        float campo3 = 0;
        int lineadeRegistros=0;

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    contador++;
                    if (contador == 2){
                        campo1 = comprobador;
                        comprobador="";
                    }

                    if (contador == 3){
                        campo2 = comprobador;
                        comprobador="";
                    }

                    if (contador == 4){
                        campo3 = Float.parseFloat(comprobador);
                        comprobador="";
                    }
                }
            }else{
                contador=0;
                if (contadorLinea == linea){
                    lector.close();
                    return campo3;

                }
                contadorLinea++;
            }
        }
        lector.close();
        return -1f;

    }
    public int getLinea(String campo, String path) throws IOException {
        int diferenciador;
        String comprobador="";
        FileInputStream lector = new FileInputStream(path);
        int contadorLinea=0;
        int contador=0;
        String campo1 = "";
        String campo2="";
        float campo3 = 0;
        int lineadeRegistros=0;

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    contador++;
                    if (contador == 2){
                        campo1 = comprobador;
                        comprobador="";
                    }

                    if (contador == 3){
                        campo2 = comprobador;
                        comprobador="";
                    }

                    if (contador == 4){
                        campo3 = Float.parseFloat(comprobador);
                        comprobador="";
                    }
                }
            }else{
                contador=0;
                if (campo1.equals(campo)){
                    lector.close();
                    return contadorLinea;

                }
                contadorLinea++;
            }
        }
        lector.close();
        return -1;
    }

    public void estadisticas() throws IOException {
        File temporal = new File("src/temporal.txt");
        int diferenciador;
        String comprobador="";

        if (temporal.exists()){
            temporal.delete();
        }

        temporal.createNewFile();
        FileInputStream lector= new FileInputStream("src/registres.txt");

        float random = (float) Math.random();
        String campo1 = "";
        String campo2 = "";
        float campo3 = 0;
        int contador = 0;
        String lineadeRegistros = "";

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    contador++;
                    if (contador == 2){
                        campo1 = comprobador;
                        comprobador="";
                    }

                    if (contador == 3){
                        campo2 = comprobador;
                        comprobador="";
                    }

                    if (contador == 4){
                        campo3 = Float.parseFloat(comprobador);
                        comprobador="";
                    }
                }
            } else{
                contador=0;
                lineadeRegistros += "#" + campo1 + "#" + campo2 + "#" + campo3 + "#\n";

                if (!buscarPez(campo1,"src/temporal.txt")){
                    FileOutputStream escritor = new FileOutputStream("src/temporal.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                }else if(buscarPez(campo1, "src/temporal.txt") && pesoGetLinea(getLinea
                        (campo1, "src/temporal.txt"), "src/temporal.txt") < campo3){
                    borrarPez(campo1, "src/temporal.txt");
                    FileOutputStream escritor = new FileOutputStream("src/temporal.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                }
            }
        }

    lector.close();
    }
    public void borrarPez(String campo, String path) throws IOException {
        File temporal2 = new File("src/temporal2.txt");
        temporal2.createNewFile();
        int diferenciador;
        String comprobador="";
        FileInputStream lector = new FileInputStream(path);
        int contadorLinea=0;
        int contador=0;
        String campo1 = "";
        String campo2="";
        float campo3 = 0;
        String  lineadeRegistros="";

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    contador++;
                    if (contador == 2){
                        campo1 = comprobador;
                        comprobador="";
                    }

                    if (contador == 3){
                        campo2 = comprobador;
                        comprobador="";
                    }

                    if (contador == 4){
                        campo3 = Float.parseFloat(comprobador);
                        comprobador="";
                    }
                }
            }else{
                contador=0;
                if (!campo1.equals(campo)){
                    lineadeRegistros += "#" + campo1 + "#" + campo2 + "#" + campo3 + "#\n";
                    FileOutputStream escritor = new FileOutputStream("src/temporal2.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                    System.out.println("Borrar pez: " + campo1);
                }
                contadorLinea++;
            }
        }
        lector.close();
        reemplazarTemporal("src/temporal2.txt", "src/temporal.txt");

    }
    public void reemplazarTemporal(String temporal1, String temporal2) throws IOException {

        int diferenciador = 0;
        File temporal1path = new File(temporal1);
        File temporal2path = new File(temporal2);
        if (!temporal2path.exists()){
            temporal2path.createNewFile();
        }
        InputStream lector = new FileInputStream(temporal1path);
        OutputStream escritor = new FileOutputStream(temporal2path);
        while ((diferenciador = lector.read()) != -1){
            escritor.write(diferenciador);
        }

        lector.close();
        escritor.close();

        temporal1path.delete();
    }
    public void añadirUsuario(String usuario) throws Exception {
        FileOutputStream escritor = new FileOutputStream("src/usuaris.txt", true);

        if (!comprobarUsuario("usuario")){
            usuario = "#" + usuario + "#\n";

            escritor.write(usuario.getBytes());
            System.out.println("Usuario añadido");
            escritor.close();
        }else throw new Exception("Usuario ya registrado.");
    }
    public void borrarUsuario(String usuario) throws IOException {
        try {
            String listaUsuario = "";
            String comprobador = "";
            int diferenciador;
            if (comprobarUsuario(usuario)) {
                FileInputStream lector = new FileInputStream("src/usuaris.txt");
                while ((diferenciador = lector.read()) != -1) {
                    comprobador += (char)diferenciador;

                    if (comprobador.equals("#" + usuario + "#\n")) {
                            comprobador = "";
                        }

                    if (diferenciador == '\n') {
                        listaUsuario+=comprobador;
                        comprobador="";
                    }
                }
                lector.close();
                FileOutputStream escritor = new FileOutputStream("src/usuaris.txt");
                escritor.write(listaUsuario.getBytes());
                escritor.close();
        }else{
                throw new Exception("Usuario no registrado.");
            }
        }catch (Exception a){
            System.out.println(a.getMessage());
        }
    }
    public void Pesquera(String usuario) throws IOException {
        FileOutputStream escritor = new FileOutputStream("src/registres.txt", true);
        FileInputStream lector = new FileInputStream("src/mediterrania.txt");
        int diferenciador;
        String comprobador="";
        float random = (float) Math.random();
        String campo1 = "";
        float campo2 = 0;
        float campo3 = 0;
        float campo4 = 0;
        int contador = 0;

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    contador++;
                    if (contador == 2){
                        campo1 = comprobador;
                        comprobador="";
                    }

                    if (contador == 3){
                        campo2 = Float.parseFloat(comprobador);
                        comprobador="";
                    }

                    if (contador == 4){
                        campo3 = Float.parseFloat(comprobador);
                        comprobador="";
                    }

                    if (contador == 5){
                        campo4 = Float.parseFloat(comprobador);
                        comprobador="";
                    }
                }
            }else{

                if (campo2>random){
                    float peso= (float) (Math.random()*(campo4-campo3)+campo3);
                    lector.close();
                    añadirPez(escritor, campo1, usuario, peso);
                    System.out.println("Has pescado: " + campo1 + " con un peso de: " + peso);
                }
                comprobador="";
                campo1 = "";
                campo2 = 0;
                campo3 = 0;
                campo4 = 0;
            }
                if (contador==5){
                    contador=0;
                }
            }
        escritor.close();
    }
    public void añadirPez(FileOutputStream escritor, String nombrePez, String usuario, float peso)
            throws IOException {
        nombrePez = "#" + nombrePez + "#" + usuario + "#" + peso + "#\n";
        escritor.write(nombrePez.getBytes());
    }
    boolean comprobarUsuario(String usuario) throws IOException {
        // Variables

        boolean encontrado = false;
        // Iniciamos la clase Filereader
        FileInputStream lector= new FileInputStream("src/usuaris.txt");

        /**
         * Inicializamos el booleano que utilizaremos para ver si está el separador, en este caso el '#' y
         * 1    11111321el booleano para ver si se ha encontrado el usuario.
         */
        int diferenciador;
        String comprobador="";

        while ((diferenciador = lector.read()) != -1){
            if (diferenciador != '\n'){
                if (diferenciador != '#' ){
                    comprobador += (char)diferenciador;
                }else{
                    if(comprobador.equals(usuario)){
                        encontrado = true;
                    }
                }
            }else{
                comprobador="";
            }

        }
        System.out.println(encontrado);
        lector.close();
        return encontrado;
     }
}

