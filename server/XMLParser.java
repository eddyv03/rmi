import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class XMLParser {
    public void parse() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RMI", "root", "edvincent123*");

            File inputFile = new File("C:\\Users\\A C E R - P C\\Desktop\\RMI\\server\\student.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(inputFile);

            Element root = doc.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());

            NodeList nodeList = doc.getElementsByTagName("student");
            String extract = "INSERT INTO students (id, name, age, address, contact) VALUES (?,?,?,?,?)";
            PreparedStatement prep = con.prepareStatement(extract);
            con.createStatement();

            System.out.println("----------------------------");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nCurrent Element: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Student ID: " + element.getAttribute("id"));
                    System.out.println("Name: " + element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Age: " + element.getElementsByTagName("age").item(0).getTextContent());
                    System.out.println("Address: " + element.getElementsByTagName("address").item(0).getTextContent());
                    System.out.println("Contact Number: " + element.getElementsByTagName("contact").item(0).getTextContent());

                    prep.setString(1,element.getElementsByTagName("id").item(0).getTextContent());
                    prep.setString(2,element.getElementsByTagName("name").item(0).getTextContent());
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    prep.setInt(3, age);
                    prep.setString(4,element.getElementsByTagName("address").item(0).getTextContent());
                    prep.setString(5,element.getElementsByTagName("contact").item(0).getTextContent());

                    int rowsAffected = prep.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully.");
                    } else {
                        System.out.println("Data insertion failed.");
                    }
                            }
                        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        XMLParser p = new XMLParser();
        p.parse();
    }
}