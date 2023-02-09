import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*This class is defined to handle the Email logs.
*There are 2 methods defined within the class to write the sent mail log to the
* Emaillog.txt and read the logs in the file under the input date*/
class EmailLogHandler {
//For the easiness in reference, the path to the email log file is assigned to a String variable
String EmailLogPath = "EmailLog.txt";
public EmailLogHandler(){} //constructor of the class
/*This method is used to write the input details of the email in the format of
* <reciever's email_address>,<subject>,<content> under the given date*/
public void emailLogWrite(String EmailDetails, String date) throws IOException {
//open the streams of file writing and buffered writing and set the parameters for appending to the file
FileWriter filewriter_emaillog = new FileWriter( EmailLogPath,true);
BufferedWriter writer_emaillog = new BufferedWriter(filewriter_emaillog);
//append the details in the format of <date>+":"+<Emaildetails>
writer_emaillog.write("\n");
writer_emaillog.write(date+":"+EmailDetails);
//close the stream, once done
writer_emaillog.close();
}
/*This method is used to return the email logs stored in the file under the given date */
public void emailLogReturn(String input_date) throws IOException {
//create a FileReader object,a BufferedReader object and a StringBuffer object for the reading purpose
FileReader filereader=new FileReader(EmailLogPath);
BufferedReader bufferedreader=new BufferedReader(filereader); /*creates a buffering character input
stream*/
StringBuffer stringbuffer=new StringBuffer(); //constructs a string buffer with no characters
/*define a separate String variable to temporally store each email log record in file when the
* file is read iteratively line by line*/
String log_record;
//iteratively the read the EmailLog.txt file line by line
while((log_record=bufferedreader.readLine())!=null) {
stringbuffer.append(log_record); //appends line to string buffer
stringbuffer.append("\n"); //append a line feed to the file
}
//closes the stream and release the resources , once done
filereader.close();
//convert the content in buffer to a string and split it at each new line character "\n" and add to an array
String log_content = stringbuffer.toString();
String[] EmailLog_list = log_content.split("\n");
//initialize an integer variable to store the count of email logs in the array
//this count variable in about to only get to used within this EmaillogHandler class
int email_count = 0;
//print the header to the display
System.out.println("Email logs on "+input_date);
/*iterate over the EmailLog_list and check the date component in the each record
*if it matches with the input date, print the log.
*if there is no mail log on the input date, print it as a message to the display*/
for(int record_index=0;(record_index<EmailLog_list.length);record_index++) {
//split a log by ":' delimiter and store two components in a list structure temporally
String[] a_log = EmailLog_list[record_index].split(":");
//if the log's date is the same as input date,print the log and increment the email_count
if (input_date.equals(a_log[0])) {
email_count += 1;
System.out.println(a_log[1]);
}
}
//once the loop finishes, if there was no mail on the given input date, print it to the display
if (email_count == 0){
System.out.println("There was no mail sent on "+input_date);
}
}
}