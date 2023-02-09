import java.util.*;
import java.io.*;
import java.lang.*;
import java.time.*;
/*This is the main class to control the Email_client simulation*/
class Demo {
public static void main(String[] args) throws FileNotFoundException, IOException {
/*Create objects from Utilities class and Recipient_Handler class to use their functionality */
Utilities utilities = new Utilities();
Recipient_Handler recipient_handler = new Recipient_Handler();
/*Read the clientsList.txt file and get containing each record and
* categorize the each record into recipient objects and add them to relevant ArrayLists */
String[] data_list = utilities.fileReading();
recipient_handler.recipient_categorizing(data_list);
/*The following 2 methods are called to check the date today and send birthday greetings
* on the day. Sent mail logs will be maintained in EmailLog.txt*/
recipient_handler.personalrecipients_to_BirthdayGreetinghandler();
recipient_handler.officialfriends_to_BirthdayGreetinghandler();
/*Create EmailLog Handler object use it's methods in mail log writing and retrieving*/
EmailLogHandler emailloghandler = new EmailLogHandler();
//Print the selection list to the display and get the user requirement
Scanner scanner = new Scanner(System.in);
System.out.println("Enter option type: \n"
+ "1 - Adding a new recipient\n"
+ "2 - Sending an email\n"
+ "3 - Printing out all the recipients who have birthdays\n"
+ "4 - Printing out details of all the emails sent\n"
+ "5 - Printing out the number of recipient objects in the application\n"
+ "6 - Updating an existing Recipient Details");
//get the option hat user selects
int option = scanner.nextInt();
switch(option){
case 1:
/*Add a new recipient to the contacts database*/
Scanner scRecipient = new Scanner(System.in);
System.out.println("Enter Recipient Details: ");
System.out.println("Please Use the format :\nOfficial_Recipient : <name>,<email>,<designation>\n"
+ "Official_Friend : <name>,<email>,<designation>,<birthday>\n"
+ "Personal_Recipient : <name>,<nickname>,<email>,<birthday>");
//get the input string to a String and then to a one element list
String new_recipientDetails = scRecipient.nextLine();
String[] newrecipientInAList = new String[1];
newrecipientInAList[0] = new_recipientDetails;
String clientList_filePath = "clientList.txt";
//Recipient details will be written to the file
utilities.fileWriting(clientList_filePath, new_recipientDetails);
System.out.println("New Recipient Record was written to the database successfully!");
//new recipient will be categorized and convert into an object by the recipient_handler
recipient_handler.recipient_categorizing(newrecipientInAList);
break;
case 2:
/*code to send a mail */
//get the email details in the format of <email>,<subject>,<content>
Scanner scSendMail = new Scanner(System.in);
System.out.println("Enter Email Details: ");
System.out.println("Please Use the format :<email>,<subject>,<content>");
String EmailDetails = scSendMail.nextLine();
//input string will will get partitioned to ","
String[] EmailDetails_Partitioned = EmailDetails.split("[,]", 0);
//components of the email will get partitioned to a list for easy handling
String email_address = EmailDetails_Partitioned[0];
String subject = EmailDetails_Partitioned[1];
String content = EmailDetails_Partitioned[2];
//A mail composer object is created to send the mail
Mail_Composer mailComposer = new Mail_Composer(email_address,subject,content);
mailComposer.sendmail();
//sent mail log will be added to the EmailLog.txt file
String today = utilities.getToday();
emailloghandler.emailLogWrite(EmailDetails,today);
break;
case 3:
/*To get the recipients who have birthdays on given date*/
Scanner scDate = new Scanner(System.in);
System.out.println("Enter the date:");
System.out.println("Follow the input Format : yyyy/MM/dd");
String date = scDate.nextLine();
//BirthdayGreeting handler object will get created to get the specific code for the today's date
BirthdayGreetingHandler birthday_greeting_handler = new BirthdayGreetingHandler(date);
int datecodex = birthday_greeting_handler.birthdayCode_generator();
//print recipients who have birthdays on the given date by traversing the Birthday log array list
System.out.println("Recipients who have birthday on "+ date+":");
recipient_handler.birthdaylogTraverse(datecodex);
break;
case 4:
/* print the details of all the emails sent on the input date*/
Scanner scDate1 = new Scanner(System.in);
System.out.println("Enter the date:");
System.out.println("Follow the input Format : yyyy/MM/dd");
String date1 = scDate1.nextLine();
//By the EmailLogHandler object created, return the mails sent on the given date
emailloghandler.emailLogReturn(date1);
break;
case 5:
/*print the number of recipient objects in the application*/
recipient_handler.recipient_count();
break;
case 6:
/*code to update the details of the recipient*/
//for official_recipient :change in name ,email and designation is possible
//for official_friend : change in name, email,designation and birthday is possible
//for personal_recipient : change in name, nickname, email and birthday is possible
Scanner screcordtype = new Scanner(System.in);
System.out.println("Choose the type of friend you want to change the record:");
System.out.println("1 : Official Recipient\n2 : Official Friend\n3 : Personal Recipient");
String type_toUpdate = screcordtype.nextLine();
Scanner screcordname = new Scanner(System.in);
System.out.println("Please type the name of the recipient :");
String name_toUpdate = screcordname.nextLine();
Scanner screcordcomponent = new Scanner(System.in);
System.out.println("Please type the component to change :");
System.out.println("1 : Name\n2 : Email\n3 : Designation\n4 :Birthday\n5 : Nickname");
String component_toUpdate = screcordcomponent.nextLine();
Scanner scnewvalue = new Scanner(System.in);
System.out.println("Please enter the new value :");
String value_toUpdate = scnewvalue.nextLine();
//call the record_updater method to update the details of the given recipient under his/her name
recipient_handler.record_updater(type_toUpdate,name_toUpdate,component_toUpdate,value_toUpdate);
}
/*Record the all Recipients objects created back in the clientsList.txt to save their states for further use*/
recipient_handler.Record_Merger();
//Get the local time and print timelog details on leaving the simulation
LocalDateTime timelog = LocalDateTime.now();
System.out.println("All changes saved successfully at "+ timelog);
}
}