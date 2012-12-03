
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailSSL {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("username","password");
				}
			});
 
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("test@test.test"));
			message.setSubject("Secret Santa");
			message.setText("Dear name," +
					"\n\n Hello!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

    public static int getNumNames() throws IOException {
//        ArrayList<String> names = null;
        int numNames = 0;
        BufferedReader i = null;
        try {
            i = new BufferedReader(new FileReader("secretsanta.secret"));
        }
        finally {
            i.close();
        }

        //        String name;
//        String email;
        while (i.readLine() != null) {
//            int comma;
//            comma = line.indexOf(",");
//            name = line.substring(0, comma);
//            email = line.substring(comma + 1);
//            names.add(name);
            numNames++;
        }

        return numNames;
    }
}