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
        return "-----------------------------------------------\nInformacion del usuario que ostenta la mayor puja:\n Nombre: " + \
               self.__pujas[-1].getUsuario().getNombreusuario() \
               + "\n Cantidad pujada: " + str(
            self.__pujas[-1].getCantidad_dinero())

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

        message ="hISTORIAL--------------------------------------------------------"
        for historial in self.Historial():
             message += historial
        return message
    def setPujas(self, Puja):
        self.__pujas.append(Puja)

    def estaAbierta(self):
        if self.getEstadosubasta():
            return "La subasta está abierta."
        else:
            return "La subasta está cerrada."

    def dineroEnCartera(self, Usuario, dinero_a_pujar):
        if (Usuario.getCredito() >= dinero_a_pujar):
            return "OK, dinero suficiente"
        else:
            return "Dinero en cartera suficiente"

    def eresPropietario(self, Usuario):
        if Usuario != self.getUsuariopropietario():
            return "Cliente confirmado, puedes continuar..."
        else:
            return "El usuario no puede subir la puja mayor."

    def dineroMayorquePujaMayor(self, dinero):
        if dinero > self.getPujamayor().getCantidad_dinero():
            return "Confirmado, dinero suficiente para superar la puja mayor."

    def dineroSufiParaPujaAutomatica(self, Usuario):
        if Usuario.getCredito() > self.getPujamayor().getCantidad_dinero():
            return "Dinero para puja de 1 euro suficiente."
        else:
            return "Dinero insuficiente para puja de 1 euro"

    # Funcionalitats

    def pujar(self, Usuario, cantidad_pujada):
        if (self.estaAbierta() and self.dineroEnCartera(Usuario,cantidad_pujada)
                and self.eresPropietario(
                    Usuario) and self.dineroMayorquePujaMayor(cantidad_pujada)):

            self.setPujas(Puja(Usuario, cantidad_pujada))
            return True and "-----------------------------------------------\nPuja hecha por " + Usuario.getNombreusuario() + " aceptada"
        else:
            return "-----------------------------------------------\nPuja no aceptada, introduzca una mayor cantidad."

    def pujaAutomatica(self, Usuario):
        if self.estaAbierta() and self.dineroSufiParaPujaAutomatica(
                Usuario) and self.eresPropietario(Usuario):
            if self.getPujamayor() is None:
                self.setPujas(Puja(Usuario, 1))
            else:
                nuevaPuja = self.getPujamayor().getCantidad_dinero() + 1
                self.setPujas(Puja(Usuario, nuevaPuja))
            return "-----------------------------------------------\nPuja automatica hecha por " + Usuario.getNombreusuario() + " aceptada\n-----------------------------------------------"
        else:
            return "-----------------------------------------------\nPuja no aceptada, no tiene la cantidad necesaria de dinero.."

    def finalizar_subasta(self):
        if self.estaAbierta():

            self.getUsuariopropietario().incrementarCredito(
                self.getPujamayor().getCantidad_dinero())
            self.getPujamayor().getUsuario().decrementarCredito(
                self.getPujamayor().getCantidad_dinero())
            self.setEstadosubasta(False)
            return "Subasta finalizada correctamente."
        else:
            return "La subasta ya ha sido cerrada."
