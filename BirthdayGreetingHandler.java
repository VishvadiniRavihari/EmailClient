import java.lang.Integer;
import java.io.*;
/*This class is defined to have it's states as email_address and birthday of each recipient and
* contains methods to calculate a specific code from each object's birthday state and
* to send birthday greetings on the relevant day*/
class BirthdayGreetingHandler {
private String email;
private String birthday;
//There are 2 constructors defined under this class.
//This constructor is used to create objects just to use the method of generating the code for birth date
public BirthdayGreetingHandler(String birthday) {
this.birthday = birthday;
}
//This constructor is used to create objects with the capability of using the all 3 methods of the class
public BirthdayGreetingHandler(String email,String birthday) {
this.email = email;
this.birthday = birthday;
}
//getters and setters are not defined since there is no need of accessing then out of the class
//A method s defined to calculate the specific code ( the birthday_codex ) for the each birthday
public int birthdayCode_generator() {
//define an character array to store character elements of the birthday
char [] birthdate_characters = this.birthday.toCharArray();
/*construct the specific code for birthday
* ex : 19994/08/17 -> 817
* ex : 2000/12/26 -> 1226*/
String code = ""+ birthdate_characters[5];
for(int index= 6;index<10;index++) {
String temp =""+birthdate_characters[index];
if (index== 7) {} //reject the element with 7th index, since it contains "/"
else { //otherwise concatenate the element to the main code
code=code.concat(temp);
}
}
//convert the String code to an integer
int birthday_codex = Integer.parseInt(code);
//return the code generated for birthday
return birthday_codex ;
}
//A method is defined to send a birthday greeting mail to a personal recipient
public void personalRecipientGreeting() throws IOException {
String message = "Hugs and love on your birthday!\tVishvadini";
Mail_Composer birthday_greeting = new Mail_Composer(this.email,"Birthday Greeting!",message );
birthday_greeting.sendmail();
//put up a log in the EmailLog.txt about the sent mail
Utilities utilities = new Utilities();
String today = utilities.getToday(); //get the date of today
EmailLogHandler birthday_greet_log = new EmailLogHandler();
birthday_greet_log.emailLogWrite(message,today); //add the log to the EmailLog file
}
//A method is defined to send a birthday greeting mail to a official friend
public void officialFriendGreeting() throws IOException {
String message = "Wish you a Happy Birthday.!\tVishvadini";
Mail_Composer birthday_greeting = new Mail_Composer(this.email,"Birthday Greeting!",message );
birthday_greeting.sendmail();
//put up a log in the EmailLog.txt about the sent mail
Utilities utilities = new Utilities();
String today = utilities.getToday(); //get the date of today
EmailLogHandler birthday_greet_log = new EmailLogHandler();
birthday_greet_log.emailLogWrite(message,today); //add the log to the EmailLog file
}
}