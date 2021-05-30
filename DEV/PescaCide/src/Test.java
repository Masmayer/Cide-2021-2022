import java.io.IOException;

class Test{
        public static void main(String[] args) throws IOException {
                Pesca p = new Pesca();
                try {
                        /*p.comprobarUsuario("juan");
                        p.añadirUsuario("59");
                        p.añadirUsuario("18");
                        p.borrarUsuario("patrick");
                        p.pesquera("src/florida.txt", "po");
                        p.estadisticas();*/
                        p.menu();

                    /*    p.estadisticasPorUsuario("po");
                        p.mostrarEstadisticas();*/
                        //p.borrarPez("Doncella", "src/registres.txt");
                        /*p.buscarPez("Serra", "src/registres.txt");
                        p.buscarPez("Aranya de cap negre", "src/registres.txt");*/
                }catch (Exception e){

                        System.out.println("Excepcion: " + e.getMessage());
                }
        }
}