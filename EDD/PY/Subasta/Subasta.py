from Usuario import Usuario
from Puja import Puja


class Subasta:
    # Constructor
    def __init__(self, nombre_producto, Usuario):
        self.__nombre_producto = nombre_producto
        self.__usuario_propietario = Usuario
        self.__estado_subasta = True
        self.__pujas = [Puja(Usuario, 0)]

    # Getters & Setters

    def info_puja_mayor(self):
        return "-----------------------------------------------\nInformacion del usuario que ostenta la mayor puja:\n Nombre: "+self.__pujas[-1].getUsuario().getNombreusuario() \
               + "\n Cantidad pujada: " + str(self.__pujas[-1].getCantidad_dinero())
    def getPujamayor(self):
        return self.__pujas[-1]

    def getNombreproducto(self):
        return self.__nombre_producto

    def setNombreproducto(self, nombre):
        self.__nombre_producto = nombre

    def getEstadosubasta(self):
        return self.__estado_subasta

    def setEstadosubasta(self, estado):
        self.__estado_subasta = estado

    def getUsuariopropietario(self):
        return self.__usuario_propietario

    def setUsuariopropietario(self, usuarioP):
        self.__usuario_propietario = usuarioP

    def Historial(self):
        for puja in self.__pujas[1::]:
            yield puja.__str__()
    def getHistorial(self):
        for historial in self.Historial():
            return "-----------------------------------------------\nHistorial de la subasta del producto: "+self.getNombreproducto()+"\n"+historial

    def setPujas(self, Puja):
        self.__pujas.append(Puja)

    # Funcionalitats

    def pujar(self, Usuario, cantidad_pujada):
        if (self.getEstadosubasta() and Usuario.getCredito() >= cantidad_pujada
                and Usuario != self.getUsuariopropietario() and cantidad_pujada > self.getPujamayor().getCantidad_dinero() ):
            self.setPujas(Puja(Usuario, cantidad_pujada))
            return True and "-----------------------------------------------\nPuja hecha por "+Usuario.getNombreusuario() +" aceptada"
        else:
            return "-----------------------------------------------\nPuja no aceptada, introduzca una mayor cantidad."

    def pujaAutomatica(self, Usuario):
        if (self.getEstadosubasta() == True and Usuario.getCredito() > self.getPujamayor().getCantidad_dinero()
                and Usuario != self.getUsuariopropietario()):
            if(self.getPujamayor() is None):
                self.setPujas(Puja(Usuario,1))
            else:
                nuevaPuja = self.getPujamayor().getCantidad_dinero()+1
                self.setPujas(Puja(Usuario, nuevaPuja))
            return "-----------------------------------------------\nPuja automatica hecha por " + Usuario.getNombreusuario() + " aceptada\n-----------------------------------------------"
        else:
            return "-----------------------------------------------\nPuja no aceptada, no tiene la cantidad necesaria de dinero.."
    def finalizar_subasta(self):
        if self.getEstadosubasta():

            self.getUsuariopropietario().incrementarCredito(self.getPujamayor().getCantidad_dinero())
            self.getPujamayor().getUsuario().decrementarCredito(self.getPujamayor().getCantidad_dinero())
            self.setEstadosubasta(False)
            return "Subasta finalizada correctamente."
        else:
            return "La subasta ya ha sido cerrada."