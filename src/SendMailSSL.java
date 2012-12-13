import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;


public class SendMailSSL {

    private static String email_template = "Hello %s! Thanks for participating in secret santa.\nThe lucky devil to receive your gift is: %s.\n\nHappy shopping!";
	protected static void sendEmail(String message_to,String message_body){
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
                        return new PasswordAuthentication("secretsantatops2014@gmail.com", pw);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("secretsantatops2014@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(message_to));
            message.setSubject("Secret Santa");
            message.setText(message_body);

            Transport.send(message);

            System.err.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private static String pw;
    public static void sendEmails(int[] recipients, HashMap<Integer, String[]> namesAndEmails) {
        String message_body;
        String message_to;
        int giver=0;

        pw = getPasswordFromUser();

        for(int i:recipients){
            //System.out.println(namesAndEmails.get(i));
            message_to=namesAndEmails.get(giver)[1];
            message_body=String.format(email_template, namesAndEmails.get(giver)[0], namesAndEmails.get(i)[0]);
            giver++;
            System.err.println("TO: "+message_to);
            System.err.println("MESSAGE: "+message_body);
            sendEmail(message_to, message_body);
        }
    }

    private static String getPasswordFromUser() {
        System.out.print("Enter your password: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.err.println("Failed to read password.");
            return null;
        }
    }

    public static void sendChangeEmail(HashMap<Integer, String[]> namesAndEmails,
                                       AtomicInteger oldChang, AtomicInteger newRec) {
        pw = getPasswordFromUser();
        String message_to;
        String message_body;

         //send to the newest person
        String[] giver;
        giver = namesAndEmails.get(namesAndEmails.size() - 1);
        message_to=giver[1];
        message_body=String.format(email_template, giver[0], namesAndEmails.get(newRec.get())[0]);
        System.err.println("TO: "+message_to);
        System.err.println("MESSAGE: "+message_body);
        sendEmail(message_to, message_body);

        //send to the old person
        giver = namesAndEmails.get(oldChang.get());
        message_to=giver[1];
        String change_email_template = "Hi %s! \nSorry for the inconvenice, but due to last-minute changes, your recipient has been changed to %s.\n\nHappy shopping!";
        message_body=String.format(change_email_template, giver[0], namesAndEmails.get(namesAndEmails.size() - 1)[0]);
        System.err.println("TO: "+message_to);
        System.err.println("MESSAGE: "+message_body);
        sendEmail(message_to, message_body);

    }

}