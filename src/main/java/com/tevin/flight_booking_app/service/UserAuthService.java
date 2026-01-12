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
 * Service for XML-based user authentication and registration.
 * Used for login and registration in the flight booking app.
 */
@Service
public class UserAuthService {

    private static final String USER_FILE = "users.xml";

    /**
     * Authenticates a user against the XML file.
     *
     * @param username user's username
     * @param password user's password
     * @return true if credentials match, false otherwise
     */
    public boolean authenticate(String username, String password) {
        try {
            Document doc = loadOrCreateUserFile();
            NodeList users = doc.getElementsByTagName("user");

            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);

                String u = user.getElementsByTagName("username")
                        .item(0).getTextContent();
                String p = user.getElementsByTagName("password")
                        .item(0).getTextContent();

                if (u.equals(username) && p.equals(password)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    /**
     * Registers a new user in the XML file.
     *
     * @param username new username
     * @param password new password
     */
    public void register(String username, String password) {
        try {
            Document doc = loadOrCreateUserFile();
            Element root = doc.getDocumentElement();

            // Check for duplicate username
            NodeList users = doc.getElementsByTagName("user");
            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);
                String existingUsername = user
                        .getElementsByTagName("username")
                        .item(0).getTextContent();

                if (existingUsername.equals(username)) {
                    throw new RuntimeException("Username already exists");
                }
            }

            // Create <user> element
            Element user = doc.createElement("user");

            Element u = doc.createElement("username");
            u.setTextContent(username);

            Element p = doc.createElement("password");
            p.setTextContent(password);

            user.appendChild(u);
            user.appendChild(p);
            root.appendChild(user);

            saveDocument(doc);

        } catch (Exception e) {
            throw new RuntimeException("User registration failed", e);
        }
    }

    /**
     * Loads the users.xml file or creates it if it does not exist.
     */
    private Document loadOrCreateUserFile() throws Exception {
        File file = new File(USER_FILE);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (!file.exists()) {
            Document doc = builder.newDocument();
            Element root = doc.createElement("users");
            doc.appendChild(root);
            saveDocument(doc);
            return doc;
        }

        return builder.parse(file);
    }

    /**
     * Saves the XML document to disk.
     */
    private void saveDocument(Document doc) throws TransformerException {
        Transformer transformer = TransformerFactory
                .newInstance()
                .newTransformer();

        transformer.setOutputProperty(
                OutputKeys.INDENT, "yes");

        transformer.transform(
                new DOMSource(doc),
                new StreamResult(new File(USER_FILE)));
    }
}
