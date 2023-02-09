import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/*This class is used to compose a mail.
*The states defined here are email_address, subject and content
*No getters or setters are defined since there is no need of explicitly
* get the state or set it to a new value
*A method called sendMail() is defined to send the mail when email_address,
* subject and content are given*/
class Mail_Composer {
private String email_address;
private String subject;
private String content;
//Within the constructor, initial states for email_address, subject and content are assigned.
public Mail_Composer(String email_address,String subject,String content) {
this.email_address = email_address;
this.subject = subject;
this.content = content;
}
/*This method is defined to send the mail*/
public void sendmail(){
// Recipient's email ID needs to be mentioned.
String receiver = this.email_address;
//Sender's email address(my email address) is assigned to a variable separately.
String sender = "vishvadinirk4@gmail.com";
// Since the mail is sent from local host, it is assigned to a variable separately.
String host = "localhost";
// Get the system properties
Properties properties = System.getProperties();
// Setup mail server to the local host
properties.setProperty("mail.smtp.host", host);
// Get the default Session object.
Session session = Session.getDefaultInstance(properties);
/*Function of sending the mail using a MimeMessage object is included within a try catch block
* to handle exceptions. If any MessagingException occurs, it will print the stack of the error*/
try {
// MimeMessage object is created for the email
MimeMessage mail = new MimeMessage(session);
// set the sender of the mail
mail.setFrom(new InternetAddress(sender));
// set the receiver of the mail
mail.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
// set the subject field of the mail
mail.setSubject(subject);
// set the content of the mail
mail.setText(content);
// send the mail
Transport.send(mail);
System.out.println("Sent message successfully....");
}
//catch block to handle any MessagingException occurs
catch (MessagingException messaging_exception) {
messaging_exception.printStackTrace();
}
}
}