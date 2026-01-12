package com.tevin.flight_booking_app.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;

/**
 * Service responsible for user registration and authentication
 * using a local XML file.
 */
@Service
public class UserXmlService {

    private static final String FILE_PATH = "users.xml";

    /**
     * Registers a new user in the XML file.
     */
    public void registerUser(String username, String email, String password) {
        try {
            File file = new File(FILE_PATH);

            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(file);

            Element root = doc.getDocumentElement();

            Element user = doc.createElement("user");

            Element u = doc.createElement("username");
            u.setTextContent(username);

            Element e = doc.createElement("email");
            e.setTextContent(email);

            Element p = doc.createElement("password");
            p.setTextContent(password);

            user.appendChild(u);
            user.appendChild(e);
            user.appendChild(p);
            root.appendChild(user);

            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTransformer();

            transformer.transform(new DOMSource(doc), new StreamResult(file));

        } catch (Exception e) {
            throw new RuntimeException("User registration failed", e);
        }
    }

    /**
     * Authenticates a user from the XML file.
     */
    public boolean authenticate(String username, String password) {
        try {
            InputStream is = new ClassPathResource(FILE_PATH).getInputStream();

            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(is);

            NodeList users = doc.getElementsByTagName("user");

            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);

                String u = user
                        .getElementsByTagName("username")
                        .item(0)
                        .getTextContent();

                String p = user
                        .getElementsByTagName("password")
                        .item(0)
                        .getTextContent();

                if (u.equals(username) && p.equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // 👈 REQUIRED while debugging
        }

        return false;
    }
}
