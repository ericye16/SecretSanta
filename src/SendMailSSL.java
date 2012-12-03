import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;
 
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

    //to be implemented by Andrew

    /**
     * Sends email to the <b>givers</b> of the gifts.
     * @param recipients The ith person in the array gives a gift to the recipients[i]th person
     * @param namesAndEmails Hashtable mapping the number of the person to their name and email
     */
    public static void sendEmails(int[] recipients, HashMap<Integer, String[]> namesAndEmails) {

    }
}