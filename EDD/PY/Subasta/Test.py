from Subasta import Subasta
from Usuario import Usuario


class Test:
    u1 = Usuario("Toni", 100)
    u2 = Usuario("Pep", 150)
    u3 = Usuario("Enric", 300)
    s1 = Subasta("Telèfon Mòbil", u1)
    print(s1.pujar(u2, 100))
    print(s1.info_puja_mayor())
    print(s1.pujar(u3, 50))
    print(s1.pujar(u3, 150))
    print(s1.info_puja_mayor())
    print(s1.finalizar_subasta())
    print(s1.pujar(u3, 200))
    s2 = Subasta("Impresora 3D", u2)
    print(s2.pujaAutomatica(u3))
    print(s2.finalizar_subasta())
    print(s1.getHistorial())
    print(s2.getHistorial())
    print(u1.__str__())
    print(u2.__str__())
    print(u3.__str__())
