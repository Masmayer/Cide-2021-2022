import java.io.IOException;

class Test{
        public static void main(String[] args) throws IOException {
                Pesca p = new Pesca();
                try {
                        //p.comprobarUsuario("juan");
                       // p.añadirUsuario("59");
                        //p.añadirUsuario("18");
                       //p.borrarUsuario("patrick");
                        //p.Pesquera("juanma");
                        p.estadisticas();
                }catch (Exception e){
                        System.out.println(e.getMessage());
                }
        }
}