from Subasta import Subasta
from Usuario import Usuario


class Test:
    u1 = Usuario("Toni", 100)
    u2 = Usuario("Pep", 150)
    u3 = Usuario("Enric", 300)
    s1 = Subasta("Telèfon Mòbil", u1)
    print(s1.puja_con_cantidad(u2, 100))
    print(s1.info_puja_mayor())
    print(s1.puja_con_cantidad(u3, 50))
    print(s1.puja_con_cantidad(u3, 150))
    print(s1.info_puja_mayor())
    print(s1.get_historial())
    print(s1.info_puja_mayor())
    print(s1.finalizar_subasta())
    print(s1.puja_con_cantidad(u3, 200))
    s2 = Subasta("Impresora 3D", u2)
    print(s2.puja_automatica(u3))
    print(s2.finalizar_subasta())
    print(s1.get_historial())
    print(s2.get_historial())
    print(u1.__str__())
    print(u2.__str__())
    print(u3.__str__())