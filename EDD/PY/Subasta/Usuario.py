class Usuario:

    # Constructor

    def __init__(self, nombreUsuario, credito):
        self.__nombreUsuario = nombreUsuario
        self.Credito = credito

    # Getters & Setters
    def get_nombre_usuario(self):
        return self.__nombreUsuario

    def set_credito(self, int):
        self.Credito = int

    def get_credito(self):
        return self.Credito

    def incrementar_credito(self, suma):
        self.Credito= self.get_credito() + suma

    def decrementar_credito(self, resta):
        self.Credito = self.get_credito() - resta


    # Metodo to String

    def __str__(self):
        return "Nombre " + self.get_nombre_usuario() + " Cantidad pujada: " + str(self.get_credito())