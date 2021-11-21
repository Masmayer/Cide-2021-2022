import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

public class Examen {
    private DocumentBuilderFactory docFactor;
    private DocumentBuilder docBuilder;
    private Document doc;

    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        Examen e1 = new Examen();
        e1.menuPrincipal();
    }
    public void crearXMLControlado() throws ParserConfigurationException, TransformerException {
        File xml = new File ("src/alumne.xml");


        if (xml.exists()){
                System.out.println("El archivo ya existe, desea crear y sobreescribir el archivo? (s/n)");
                String sc = new Scanner(System.in).nextLine();
                if (sc.equals("s")){
                    crearXML(xml);
                }else{
                    System.out.println("Volviendo al menu...");
                }
        }else{
                crearXML(xml);
        }

        }
    public void crearXML(File xml) throws ParserConfigurationException, TransformerException {
            docFactor = DocumentBuilderFactory.newInstance();
            docBuilder = docFactor.newDocumentBuilder();
            DOMImplementation imp = docBuilder.getDOMImplementation();
            doc = imp.createDocument(null, "registre_alumne", null);
            doc.setXmlVersion("1.0");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Source source = new DOMSource(doc);
            Result result = new StreamResult(xml);
            transformer.transform(source, result);

}
    public void añadirDatosXML() throws IOException, SAXException, ParserConfigurationException, TransformerException, XPathExpressionException {
        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");

        Element alumnes = doc.createElement("alumnes");
        doc.getFirstChild().appendChild(alumnes);
        String sc;
        Element nom_alumne = doc.createElement("nom_alumne");
        Element curs = doc.createElement("curs");
        Element any_naixement = doc.createElement("any_naixement");
        Element colegi = doc.createElement("colegi");

        alumnes.appendChild(nom_alumne);
        alumnes.appendChild(curs);
        alumnes.appendChild(any_naixement);
        alumnes.appendChild(colegi);

        System.out.println("Codigo del alumno: ");
        sc = new Scanner(System.in).nextLine();

        NodeList nodes = doc.getFirstChild().getChildNodes();

        for (int i = 0; i < nodes.getLength()-1; i++) {
            System.out.println((nodes.item(i).getAttributes().getNamedItem("codi_alumne")));
            if (nodes.item(i).getAttributes().getNamedItem("codi_alumne").getNodeValue().equals(sc)){
                System.out.println("Codigo no disponible, volviendo al menu");
                menuPrincipal();
            }
        }

        alumnes.setAttribute("codi_alumne", sc);

        System.out.println("Nombre: ");
        sc = new Scanner(System.in).nextLine();
        nom_alumne.setTextContent(sc);

        System.out.println("Curso: ");
        sc = new Scanner(System.in).nextLine();
        curs.setTextContent(sc);

        System.out.println("Año de nacimiento: ");
        sc = new Scanner(System.in).nextLine();
        any_naixement.setTextContent(sc);

        System.out.println("Colegi: ");
        sc = new Scanner(System.in).nextLine();
        colegi.setTextContent(sc);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(doc);
        Result result = new StreamResult("src/alumne.xml");
        transformer.transform(source, result);

    }
    public void mostrarXML() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {

        StringWriter sw = new StringWriter();

        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes2 = (NodeList) xpath.evaluate("//alumnes", doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes2.getLength(); i++) {
            Element alumne = (Element) nodes2.item(i);
            String c1 = (String) xpath.evaluate("nom_alumne", alumne, XPathConstants.STRING);
            String c2 = (String) xpath.evaluate("curs", alumne, XPathConstants.STRING);
            String c3 = (String) xpath.evaluate("any_naixement", alumne, XPathConstants.STRING);
            String c4 = (String) xpath.evaluate("colegi", alumne, XPathConstants.STRING);
            System.out.println("Nombre= " + c1);
            System.out.println("Curso= " + c2);
            System.out.println("Año de nacimiento= " + c3);
            System.out.println("Colegio= " + c4);
            System.out.println("----------");
        }
    }
    public void consultaNombre() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {

        StringWriter sw = new StringWriter();

        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes2 = (NodeList) xpath.evaluate("//alumnes", doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes2.getLength(); i++) {
            Element alumne = (Element) nodes2.item(i);
            String c1 = (String) xpath.evaluate("nom_alumne", alumne, XPathConstants.STRING);
            System.out.println("Nombre= " + c1);
            System.out.println("----------");
        }
    }
    public void consultaAlumnosCide() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {

        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes2 = (NodeList) xpath.evaluate("//alumnes[colegi='cide']", doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes2.getLength(); i++) {
            Element alumne = (Element) nodes2.item(i);
            String c1 = (String) xpath.evaluate("nom_alumne", alumne, XPathConstants.STRING);
            String c2 = (String) xpath.evaluate("curs", alumne, XPathConstants.STRING);
            String c3 = (String) xpath.evaluate("any_naixement", alumne, XPathConstants.STRING);
            String c4 = (String) xpath.evaluate("colegi", alumne, XPathConstants.STRING);
            System.out.println("Nombre= " + c1);
            System.out.println("Curso= " + c2);
            System.out.println("Año de nacimiento= " + c3);
            System.out.println("Colegio= " + c4);
            System.out.println("----------");
        }
    }
    public void consultarAlumnoCodigo3() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {

        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes2 = (NodeList) xpath.evaluate("//alumnes[@codi_alumne='3']", doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes2.getLength(); i++) {
            Element alumne = (Element) nodes2.item(i);
            String c1 = (String) xpath.evaluate("nom_alumne", alumne, XPathConstants.STRING);
            System.out.println("Nombre= " + c1);
            System.out.println("----------");
        }
    }
    public void consultarAlumnosNacidosAntesdel1990() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {

        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes2 = (NodeList) xpath.evaluate("//alumnes[any_naixement<1990]", doc, XPathConstants.NODESET);

        for (int i = 0; i < nodes2.getLength(); i++) {
            Element alumne = (Element) nodes2.item(i);
            String c1 = (String) xpath.evaluate("nom_alumne", alumne, XPathConstants.STRING);
            String c2 = (String) xpath.evaluate("curs", alumne, XPathConstants.STRING);
            String c3 = (String) xpath.evaluate("any_naixement", alumne, XPathConstants.STRING);
            String c4 = (String) xpath.evaluate("colegi", alumne, XPathConstants.STRING);
            System.out.println("Nombre= " + c1);
            System.out.println("Curso= " + c2);
            System.out.println("Año de nacimiento= " + c3);
            System.out.println("Colegio= " + c4);
            System.out.println("----------");
        }
    }
    public void modificarXML() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");
        NodeList nodes = doc.getFirstChild().getChildNodes();
        System.out.println("Codigo del alumno: ");
        String sc = new Scanner(System.in).nextLine();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getAttributes().getNamedItem("codi_alumne").getNodeValue().equals(sc)){
                NodeList node2 = nodes.item(i).getChildNodes();
                String tag = menuModificar();
                for (int j = 0; j < node2.getLength(); j++) {
                    if(node2.item(j).getNodeName().equals(tag)){
                        System.out.println("Datos a introducir: ");
                        String sc2 = new Scanner(System.in).nextLine();
                        node2.item(j).setTextContent(sc2);
                    }
                }
            }
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(doc);
        Result result = new StreamResult("src/alumne.xml");
        transformer.transform(source, result);
    }
    public void eliminarXML() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        docFactor = DocumentBuilderFactory.newInstance();
        docBuilder = docFactor.newDocumentBuilder();
        doc = docBuilder.parse("src/alumne.xml");
        NodeList nodes = doc.getFirstChild().getChildNodes();
        System.out.println("Codigo del alumno: ");
        String sc = new Scanner(System.in).nextLine();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getAttributes().getNamedItem("codi_alumne").getNodeValue().equals(sc)){
                nodes.item(i).getParentNode().removeChild(nodes.item(i));

                }
            }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(doc);
        Result result = new StreamResult("src/alumne.xml");
        transformer.transform(source, result);
        }
    public String menuModificar() throws IOException{
        boolean bandera = true;
        String res = null;
        while (bandera){
            System.out.println("**************************************************");
            System.out.println("* Que dato desea modificar? *");
            System.out.println("**************************************************");
            System.out.println("* 1) Nombre *");
            System.out.println("* 2) Curso * ");
            System.out.println("* 3) Año de nacimiento *");
            System.out.println("* 4) Colegio *");
            System.out.println("**************************************************\n");
            Scanner sc = new Scanner(System.in);
            String opcionElegida = sc.nextLine();
            switch (opcionElegida){
                case "1":
                    res = "nom_alumne";
                    bandera = false;
                    break;
                case "2":
                    res = "curs";
                    bandera = false;
                    break;
                case "3":
                    res = "any_naixement";
                    bandera = false;
                    break;
                case "4":
                    res = "colegi";
                    bandera = false;
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }
        return res;
    }
    public void menuPrincipal() throws IOException, TransformerException, ParserConfigurationException, SAXException, XPathExpressionException {
        boolean bandera = true;
        String res = null;
        while (bandera){
            System.out.println("**************************************************");
            System.out.println("* Que desea hacer? *");
            System.out.println("**************************************************");
            System.out.println("* 1) Crear el fichero XML*");
            System.out.println("* 2) Introducir datos en el fichero XML *");
            System.out.println("* 3) Mostrar el contenido del fichero XML *");
            System.out.println("* 4) Modificar datos *");
            System.out.println("* 5) Consultas *");
            System.out.println("* 6) Eliminar un registro *");
            System.out.println("* 0) Salir *");
            System.out.println("**************************************************\n");
            Scanner sc = new Scanner(System.in);
            String opcionElegida = sc.nextLine();
            switch (opcionElegida){
                case "1":
                    crearXMLControlado();
                    break;
                case "2":
                    añadirDatosXML();
                    break;
                case "3":
                    mostrarXML();
                    break;
                case "4":
                    modificarXML();
                    break;
                case "5":
                    menuConsultas();
                    break;
                case "6":
                    eliminarXML();
                    break;
                case "0":
                    bandera = false;
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }
    public void menuConsultas() throws IOException, TransformerException, ParserConfigurationException, SAXException, XPathExpressionException {
        boolean bandera = true;
        String res = null;
        while (bandera){
            System.out.println("**************************************************");
            System.out.println("* Que desea consultar? *");
            System.out.println("**************************************************");
            System.out.println("* 1) Consultar todos los nombres de los alumnos *");
            System.out.println("* 2) Consultar los alumnos que vayan al colegio Cide *");
            System.out.println("* 3) Consultar el nombre del alumno con codigo 3 *");
            System.out.println("* 4) Consultar los alumnos nacidos antes de 1990 *");
            System.out.println("* 0) Salir al menu principal*");
            System.out.println("**************************************************\n");
            Scanner sc = new Scanner(System.in);
            String opcionElegida = sc.nextLine();
            switch (opcionElegida){
                case "1":
                    consultaNombre();
                    break;
                case "2":
                    consultaAlumnosCide();
                    break;
                case "3":
                    consultarAlumnoCodigo3();
                    break;
                case "4":
                    consultarAlumnosNacidosAntesdel1990();
                    break;
                case "0":
                    bandera = false;
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }

    }

