from Usuario import Usuario
from Puja import Puja


class Subhasta:
    # Constructor
    def __init__(self, nombre_producto, Usuario):
        self.__nombre_producto = nombre_producto
        self.__usuario_propietario = Usuario
        self.__estado_subasta = True
        self.__pujas = []
        self.__puja_mayor

    # Getters & Setters

    def getPujamayor(self):
        pujas_length = len(self.__pujas)
        self.__puja_mayor = self.__pujas[pujas_length - 1]
        return self.__puja_mayor

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

    def getPujas(self):
        return self.__pujas

    def setPujas(self, Puja):
        self.__pujas.append(Puja)

    # Funcionalitats

    def pujar(self, Usuario, cantidad_pujada):
        if (Subhasta.getEstadosubasta() == True and Usuario.getCredito() >= cantidad_pujada()
                and Usuario != self.getUsuariopropietario() and cantidad_pujada > self.getPujamayor().getCantidad_dinero()):

            self.setPujas(Puja(Usuario, cantidad_pujada))
            return True
        else:
            print("Puja no aceptada")

    def pujaAutomatica(self, Usuario):
        if (Subhasta.getEstadosubasta() == True and Usuario.getCredito() > self.getPujamayor().get
                and Usuario != self.getUsuariopropietario()):
            if(self.getPujamayor() is None):
                self.setPujas(Puja(Usuario,1))
            else:
                nuevaPuja = self.getPujamayor().getCantidad_dinero()+1
                self.setPujas(Puja(Usuario, nuevaPuja))
        return True

    def finalizar_subasta(self):
        if (self.getEstadosubasta() == True):
            self.setEstadosubasta(False)
            self.getUsuariopropietario().incrementarCredito(self.getPujamayor().getCantidad_dinero())
            self.getPujamayor().getUsuario().decrementarCredito(self.getPujamayor().getCantidad_dinero())
        else:
            print("Subasta ya terminada")