package com.tevin.flight_booking_app.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Handles user registration and authentication using a filesystem XML file.
 */
@Service
public class UserXmlService {

    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = DATA_DIR + "/users.xml";

    public UserXmlService() {
        ensureFileExists();
    }

    /**
     * Register a new user.
     */
    public void registerUser(String username, String email, String password) {
        try {
            Document doc = loadDocument();
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
            saveDocument(doc);

        } catch (Exception ex) {
            throw new RuntimeException("User registration failed", ex);
        }
    }

    /**
     * Authenticate an existing user.
     */
    public boolean authenticate(String username, String password) {
        try {
            Document doc = loadDocument();
            NodeList users = doc.getElementsByTagName("user");

            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);

                String u = user.getElementsByTagName("username").item(0).getTextContent();
                String p = user.getElementsByTagName("password").item(0).getTextContent();

                if (u.equals(username) && p.equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ------------------ helpers ------------------

    private Document loadDocument() throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(new File(FILE_PATH));
    }

    private void saveDocument(Document doc) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(FILE_PATH)));
    }

    private void ensureFileExists() {
        try {
            File dir = new File(DATA_DIR);
            if (!dir.exists()) dir.mkdir();

            File file = new File(FILE_PATH);
            if (!file.exists()) {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.newDocument();

                Element root = doc.createElement("users");
                doc.appendChild(root);

                saveDocument(doc);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize users.xml", e);
        }
    }
}