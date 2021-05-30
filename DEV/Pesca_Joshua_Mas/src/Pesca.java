import java.io.*;
import java.util.Scanner;

public class Pesca {
    public void mostrarEstadisticas() throws IOException {
        int diferenciador;
        String comprobador="";
        FileInputStream lector = new FileInputStream("src/temporal.txt");
        int contadorLinea=0;
        int contador=0;
        String campo1 = "";
        String campo2="";
        float campo3 = 0;
        String  leerLineadeEstadistica="";

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
                leerLineadeEstadistica = "#" + campo1 + "#" + campo2 + "#" + campo3 + "#";
                System.out.println("------------------------------\nNombre del pez: " + campo1 + " \nLo ha pescado: " +
                        campo2 + "\nCon un peso de: " + campo3 + "\n------------------------------");
                leerLineadeEstadistica="";
                campo1="";
                campo2="";
                campo3=0;
                contadorLinea++;
            }
        }
        lector.close();

    }
    public void estadisticasPorUsuario(String usuario) throws IOException {
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
                lineadeRegistros = "#" + campo1 + "#" + campo2 + "#" + campo3 + "#\n";
                if (campo2.equals(usuario)){
                if (!buscarPez(campo1,"src/temporal.txt")){
                    FileOutputStream escritor = new FileOutputStream("src/temporal.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();

                }else if(buscarPez(campo1, "src/temporal.txt") &&
                        pesoGetLinea(getLinea(campo1, "src/temporal.txt"), "src/temporal.txt") < campo3){

                    borrarPez(campo1, "src/temporal.txt");

                    FileOutputStream escritor = new FileOutputStream("src/temporal.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                }
                }
                campo1="";
                campo2="";
                campo3=0;
            }
        }
        lector.close();
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
                lineadeRegistros = "#" + campo1 + "#" + campo2 + "#" + campo3 + "#\n";
                if (!buscarPez(campo1,"src/temporal.txt")){
                    FileOutputStream escritor = new FileOutputStream("src/temporal.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();

                }else if(buscarPez(campo1, "src/temporal.txt") &&
                        pesoGetLinea(getLinea(campo1, "src/temporal.txt"), "src/temporal.txt") < campo3){

                        borrarPez(campo1, "src/temporal.txt");

                    FileOutputStream escritor = new FileOutputStream("src/temporal.txt", true);
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                }
                campo1="";
                campo2="";
                campo3=0;
            }
        }
    lector.close();
    }
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
                campo1="";
                campo2="";
                campo3=0;
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
                campo1="";
                campo2="";
                campo3=0;
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
                campo1="";
                campo2="";
                campo3=0;
                contadorLinea++;
            }
        }
        lector.close();
        return -1;
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
                if (!campo1.equals(campo)){

                    lineadeRegistros += "#" + campo1 + "#" + campo2 + "#" + campo3 + "#\n";
                    FileOutputStream escritor = new FileOutputStream("src/temporal2.txt");
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                }
                if (contador == 4){
                    contador=0;
                }

                campo1="";
                campo2="";
                campo3 = 0;
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

        if (!comprobarUsuario(usuario)){
            usuario = "#" + usuario + "#\n";
            escritor.write(usuario.getBytes());
            System.out.println("------------------------------\nUsuario añadido\n------------------------------");
            escritor.close();
        }else throw new Exception("Usuario ya registrado.");
    }
    public void borrarUsuario(String usuario) throws Exception {
        if (!comprobarUsuario(usuario)){
            throw new Exception("Usuario no registrado.");
        }
        File temporal2 = new File("src/temporal2.txt");
        temporal2.createNewFile();
        int diferenciador;
        String comprobador="";
        FileInputStream lector = new FileInputStream("src/usuaris.txt");
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
                }
            }else{
                if (!campo1.equals(usuario)){

                    lineadeRegistros += "#" + campo1 + "#\n";
                    FileOutputStream escritor = new FileOutputStream("src/temporal2.txt");
                    escritor.write(lineadeRegistros.getBytes());
                    escritor.close();
                }
                if (contador == 2){
                    contador=0;
                }

                campo1="";
                contadorLinea++;
            }
        }
        lector.close();
        reemplazarTemporal("src/temporal2.txt", "src/usuaris.txt");
        lineadeRegistros="";
    }
    public void pesquera(String path, String usuario) throws Exception {

        FileInputStream lector = new FileInputStream(path);
        int diferenciador;
        String comprobador="";
        float random = (float) Math.random() * 1.25f;
        String campo1 = "";
        float campo2 = 0;
        float campo3 = 0;
        float campo4 = 0;
        int contador = 0;
        String nombrePez = "";
        float pezminPeso = 0;
        float pezmaxPeso = 0;
        int contadorlinea=0;

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
                contadorlinea++;
                if (contadorlinea == 1 && random < campo2){
                    throw new Exception("No has pescado nada");
                }
                if (random>campo2){
                     nombrePez = campo1;
                     pezminPeso = campo3;
                     pezmaxPeso = campo4;
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
        lector.close();

        float peso= (float) (Math.random()*(pezmaxPeso-pezminPeso)+pezminPeso);
        añadirPez(nombrePez, usuario, peso);
        System.out.println("------------------------------\nHas pescado un " +
                nombrePez + " con un peso de " + peso + "\n------------------------------");

        System.out.println(random);
    }
    public void añadirPez(String nombrePez1, String usuario, float peso)
            throws IOException {
        FileOutputStream escritor = new FileOutputStream("src/registres.txt", true);
        nombrePez1 = "#" + nombrePez1 + "#" + usuario + "#" + peso + "#\n";
        escritor.write(nombrePez1.getBytes());
        escritor.close();
        nombrePez1="";

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
        lector.close();
        return encontrado;
     }
    boolean comprobarUsuarioParaPescar(String usuario) throws Exception {
        if (comprobarUsuario(usuario)){
            System.out.println("Usuario aceptado, vas a pescar como el usuario: " + usuario);
            return true;
        } else throw new Exception("El usuario no existe.");
    }
    public void menu() {
        Scanner sc = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("**************************************************");
                System.out.println("* Bienvenidos al programa de pesca.              *");
                System.out.println("* Menú principal                                 *");
                System.out.println("**************************************************");
                System.out.println("* 1) Alta de usuario.                            *");
                System.out.println("* 2) Baja de usuario.                            *");
                System.out.println("* 3) Pescar en una pesquera.                     *");
                System.out.println("* 4) Estadísticas por usuario.                   *");
                System.out.println("* 5) Estadísticas globales.                      *");
                System.out.println("* s) Salir del programa.                         *");
                System.out.println("**************************************************");
                System.out.println("OPCIÓ?");
                String opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        System.out.println("Que usuario quieres añadir?");
                        String usuarioNuevo = sc.nextLine();
                        this.añadirUsuario(usuarioNuevo);
                        this.menu();
                    case "2":
                        System.out.println("Que usuario quieres eliminar?");
                        String usuarioEliminar = sc.nextLine();
                        this.borrarUsuario(usuarioEliminar);
                        System.out.println("Borrado satisfactoriamente!");
                        this.menu();
                        break;
                    case "3":
                        System.out.println("Con que usuario quiere pescar? ");
                        String usuarioaPescar = sc.nextLine();
                        this.comprobarUsuarioParaPescar(usuarioaPescar);
                        this.seleccionarPesquera(usuarioaPescar);
                        this.menu();
                        break;
                    case "4":
                        System.out.println("De que usuario deseas ver las estadisticas? ");
                        String estadisticasDeUsuario = sc.nextLine();
                        if (comprobarUsuario(estadisticasDeUsuario)){
                            this.estadisticasPorUsuario(estadisticasDeUsuario);
                            System.out.println("------------------------------------------------------------\n" +
                                    "ESTADISTICAS DE PESO MÁXIMAS POR EL USUARIO "+ estadisticasDeUsuario +
                                    "\n------------------------------------------------------------");
                            this.mostrarEstadisticas();
                        }else throw new Exception("Usuario no encontrado.");
                        this.menu();
                        break;
                    case "5":
                        this.estadisticas();
                        System.out.println("------------------------------------------------------------\n"
                                +"ESTADISTICAS DE PESO MÁXIMAS GLOBALES "+
                                "\n------------------------------------------------------------");
                        this.mostrarEstadisticas();
                        this.menu();
                        break;
                    case "s":
                        System.out.println("Finalizando el programa de pesca.");
                        salir = true;
                        break;
                    default:
                        throw new Exception("Error, opción no válida!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void seleccionarPesquera(String usuario) {
        try {
            boolean salir2=false;
            Scanner sc = new Scanner(System.in);
                System.out.println("**************************************************");
                System.out.println("* En que mar quieres pescar?                     *");
                System.out.println("**************************************************");
                System.out.println("* 1) Mediterrania.                               *");
                System.out.println("* 2) Florida.                                    *");
                System.out.println("* 3) Asiatico.                                    *");
                System.out.println("* s) Volver al menú.                             *");
                System.out.println("**************************************************");
                String opcion = sc.nextLine();
                    switch (opcion){
                        case "1":
                            String path = "src/mediterrania.txt";
                            this.pesquera(path, usuario);
                            break;
                        case "2":
                            path = "src/florida.txt";
                            this.pesquera(path, usuario);
                            break;
                        case "3":
                            path = "src/asiatico.txt";
                            this.pesquera(path, usuario);
                            break;
                        case "s":
                            break;
                        default:
                            throw new Exception("Opción incorrecta.");
                    }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try{
        Pesca a = new Pesca();
        a.menu();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
