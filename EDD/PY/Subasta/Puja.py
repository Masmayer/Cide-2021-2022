from Usuario import Usuario

class Puja:

    # Constructor

   def __init__(self, usuario, cantidad_dinero ):
       self.Usuario = usuario
       self.cDinero = cantidad_dinero

    # Getters & Setters

   def get_usuario(self):
       return self.Usuario

   def get_cantidad_dinero(self):
       return self.cDinero

   def  __str__(self):
        return "\n-----------------------------------------------\n" \
               "Informacion del pujador:" \
               "\nNombre: " + self.get_usuario().get_nombre_usuario() + \
               "\nCantidad pujada: " + str(self.get_cantidad_dinero())+"\n-----------------------------------------------"

