from Usuario import Usuario

class Puja:
    # Constructor

   def __init__(self, usuario, cantidad_dinero ):
       self.Usuario = usuario
       self.cDinero = cantidad_dinero

    # Getters & Setters

   def getUsuario(self):
       return self.Usuario

   def getCantidad_dinero(self):
       return self.cDinero