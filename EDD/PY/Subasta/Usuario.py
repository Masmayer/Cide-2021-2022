class Usuario:

    # Constructor

    def __init__(self, nombreUsuario, credito):
        self.__nombreUsuario = nombreUsuario
        self.Credito = credito

    # Getters & Setters
    def getNombreusuario(self):
        return self.__nombreUsuario

    def setCredito(self, int):
        self.Credito = int

    def getCredito(self):
        return self.Credito

    def incrementarCredito(self, suma):
        self.Credito= self.getCredito() + suma

    def decrementarCredito(self, resta):
        self.Credito = self.getCredito() - resta


    # Metodo to String

    def __str__(self):
        return "Nombre " + self.getNombreusuario() + " Cantidad pujada: " + str(self.getCredito())