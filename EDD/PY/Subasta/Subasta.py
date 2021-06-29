from Usuario import Usuario
from Puja import Puja


class Subasta:
    #print(prueba commit)
    # Constructor
    print("HOLA23asdasd3srfasdasdasdasdasasdasdasddasdasdasdasdsasdasdr2132132")
    def __init__(self, nombre_producto, Usuario):
        self.__nombre_producto = nombre_producto
        self.__usuario_propietario = Usuario
        self.__estado_subasta = True
        self.__pujas = [Puja(Usuario, 0)]

    # Getters & Setters

    def info_puja_mayor(self):
        return "-----------------------------------------------\nInformacion del usuario que ostenta la mayor puja:\n Nombre: " + \
               self.__pujas[-1].get_usuario().get_nombre_usuario() \
               + "\n Cantidad pujada: " + str(
            self.__pujas[-1].get_cantidad_dinero())

    def get_puja_mayor(self):
        return self.__pujas[-1]

    def get_nombre_producto(self):
        return self.__nombre_producto

    def set_nombre_producto(self, nombre):
        self.__nombre_producto = nombre

    def get_estado_subasta(self):
        return self.__estado_subasta

    def set_estado_subasta(self, estado):
        self.__estado_subasta = estado

    def get_usuario_propietario(self):
        return self.__usuario_propietario

    def set_usuario_propietario(self, usuarioP):
        self.__usuario_propietario = usuarioP

    def historial(self):
        for puja in self.__pujas[1::]:
            yield puja.__str__()

    def get_historial(self):

        message ="-----------------------------------------------\nHistorial de pujadores del objeto " + \
                 self.get_nombre_producto()
        for historial in self.historial():
             message += historial
        return message
    def set_pujas(self, Puja):
        self.__pujas.append(Puja)

    def esta_abierta(self):
        if self.get_estado_subasta():
            return "La subasta está abierta."
        else:
            return "La subasta está cerrada."

    def dinero_en_cartera(self, Usuario, dinero_a_pujar):
        if (Usuario.get_credito() >= dinero_a_pujar):
            return "OK, dinero suficiente"
        else:
            return "Dinero en cartera suficiente"

    def eres_propietario(self, Usuario):
        if Usuario != self.get_usuario_propietario():
            return "Cliente confirmado, puedes continuar..."
        else:
            return "El usuario no puede subir la puja mayor."

    def dinero_mayor_que_puja_mayor(self, dinero):
        if dinero > self.get_puja_mayor().get_cantidad_dinero():
            return "Confirmado, dinero suficiente para superar la puja mayor."

    def dinero_sufi_para_puja_automatica(self, Usuario):
        if Usuario.get_credito() > self.get_puja_mayor().get_cantidad_dinero():
            return "Dinero para puja de 1 euro suficiente."
        else:
            return "Dinero insuficiente para puja de 1 euro"

    # Funcionalitats

    def puja_con_cantidad(self, Usuario, cantidad_pujada):
        if (self.esta_abierta() and self.dinero_en_cartera(Usuario,cantidad_pujada)
                and self.eres_propietario(Usuario) and self.dinero_mayor_que_puja_mayor(cantidad_pujada)):

            self.set_pujas(Puja(Usuario, cantidad_pujada))
            return True and "-----------------------------------------------\nPuja hecha por " + \
                   Usuario.get_nombre_usuario() + " aceptada"
        else:
            return "-----------------------------------------------\nPuja no aceptada, introduzca una mayor cantidad."

    def puja_automatica(self, Usuario):
        if self.esta_abierta() and self.dinero_sufi_para_puja_automatica(
                Usuario) and self.eres_propietario(Usuario):
            if self.get_puja_mayor() is None:
                self.set_pujas(Puja(Usuario, 1))
            else:
                nueva_puja = self.get_puja_mayor().get_cantidad_dinero() + 1
                self.set_pujas(Puja(Usuario, nueva_puja))
            return "-----------------------------------------------\nPuja automatica hecha por " + \
                   Usuario.get_nombre_usuario() + " aceptada\n-----------------------------------------------"
        else:
            return "-----------------------------------------------\nPuja no aceptada, no tiene la cantidad necesaria "\
                   "de dinero.. "

    def finalizar_subasta(self):
        if self.esta_abierta():

            self.get_usuario_propietario().incrementar_credito(self.get_puja_mayor().get_cantidad_dinero())
            self.get_puja_mayor().get_usuario().decrementar_credito(self.get_puja_mayor().get_cantidad_dinero())
            self.set_estado_subasta(False)
            return "-----------------------------------------------\nSubasta finalizada correctamente."
        else:
            return "-----------------------------------------------\nLa subasta ya ha sido cerrada."