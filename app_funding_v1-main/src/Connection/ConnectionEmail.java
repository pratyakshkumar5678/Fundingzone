
package Connection;

import GUI.Loading;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;


public class ConnectionEmail {
    private static final Properties PROPS = new Properties();

    static {
        try {
            PROPS.load(ConnectionEmail.class.getClassLoader().getResourceAsStream("Config/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static boolean checkConnection() {
        final String username = PROPS.getProperty("email.smtp.username");
        final String password = PROPS.getProperty("email.smtp.password");
        final String host = PROPS.getProperty("email.smtp.host");
        final String port = PROPS.getProperty("email.smtp.port");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port); // Port 587

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Loading loadingScreen = new Loading();
        loadingScreen.setVisible(true);

        try {
            Transport transport;
            transport = session.getTransport("smtp");
            transport.connect(host, Integer.parseInt(port), username, password);
            transport.close();
            System.out.println("Connection to SMTP server established successfully!");
            loadingScreen.dispose();
            return true;
        } catch (MessagingException e) {
            System.err.println("Failed to connect to SMTP server. Error: " + e.getMessage());
            loadingScreen.dispose();
            return false;
        }
    }
    
    // Send Email
    public static boolean sendEmail(String recipient, String subject, String body, List<String> attachmentPaths) {
        final String username = PROPS.getProperty("email.smtp.username");
        final String password = PROPS.getProperty("email.smtp.password");
        final String host = PROPS.getProperty("email.smtp.host");
        final String port = PROPS.getProperty("email.smtp.port");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port); // Port 587

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Loading loadingScreen = new Loading();
        loadingScreen.setVisible(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

           
            if (attachmentPaths != null && !attachmentPaths.isEmpty()) {
                for (String attachmentPath : attachmentPaths) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.attachFile(attachmentPath);
                    multipart.addBodyPart(attachmentPart);
                }
            }

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully!");
            JOptionPane.showMessageDialog(null, "Email Terkirim!");
            loadingScreen.dispose();
            return true;
        } catch (MessagingException | IOException e) {
            JOptionPane.showMessageDialog(null, "Kesalahan terjadi saat mengirim email");
            System.err.println("Failed to send email. Error: " + e.getMessage());
            loadingScreen.dispose();
            return false;
        }
    }
}
