import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
/*This is the class defined to handle co-related utility activities with the main functions expected
* There is no state variables defined in this class.
* This Utilities class contains methods
* - to get the date of today
* - to convert the given date to the specific numeric code <month_number +date_number> for
* the comparison purpose
* - to write in the "clientList.txt" file
* - to read from "clientList.txt" file
* - to overwrite "clientList.txt file */
class Utilities {
String clientList_filePath = "clientList.txt";
public Utilities() {} //the constructor of the Utilities class
/*The method is defined to get the date of today in the format of "yyyy/MM/dd" */
public String getToday() {
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
LocalDate now = LocalDate.now();
String today = dtf.format(now); /*Assign the formatted date of today to the String variable
today and return it*/
return today;
}
/*The method is defined to get the specific numeric code for the given date which is used for comparison
purpose */
public int todayCode(String date) {
//convert the given date to a character array
char [] birthdate_characters = date.toCharArray();
//define String variable called birthday_code with the first number of the month assigned
String birthday_code = ""+ birthdate_characters[5];
/*iteratively concatenate each number to get the code in the form of
<"month_number"+"date_number">*/
for(int index= 6; (index<10) ;index++) {
String temp =""+birthdate_characters[index];
if (index== 7) {} //to overcome "/" symbol
else {birthday_code = birthday_code.concat(temp);}
}
//convert birthday_code to an integer and assign it to integer variable called date_codex
int date_codex = Integer.parseInt(birthday_code);
//return date_codex value
return date_codex;
}
/*The method is defined to write data in "clientList.txt" */
public void fileWriting(String clientList_filePath,String recipient_details ) throws IOException {
//create a FileWriter object with parameters set to append to the file and a BufferedWriter object
FileWriter fw = new FileWriter(clientList_filePath,true);
BufferedWriter writer = new BufferedWriter(fw);
//append the input content to the file starting from a newline
writer.write("\n");
writer.write(recipient_details);
//once done, close the writer object
writer.close();
}
/*The method is defined to read data in "clientList.txt" */
public String[] fileReading() throws IOException {
//create a FileReader object,a BufferedReader object and a StringBuffer object for the reading purpose
FileReader fr=new FileReader(clientList_filePath);
BufferedReader br=new BufferedReader(fr); //creates a buffering character input stream
StringBuffer sb=new StringBuffer(); //constructs a string buffer with no characters
//define a variable to store a recipient_record at a time
String recipient_record;
//iteratively read the clientList.txt file in line by line manner and append each record to the string buffer
while((recipient_record=br.readLine())!=null){
sb.append(recipient_record); //appends arecipient_record to the string buffer
sb.append("\n"); //append a line feed to the string buffer
}
//once done, close the stream
fr.close();
/*convert string buffer into a String variable and then split it by "\n" new line character and append to
RecipientsDetails list*/
String recipient_record_content = sb.toString();
String[] RecipientsDetailsList = recipient_record_content.split("\n");
//return the recipientsDetails list
return RecipientsDetailsList;
}
/*The method is defined to overwrite data to the "clientList.txt"
* and is used as an alternative to object serialization technique.
* The whole purpose would be to save the states of recipient objects in the
* clientsList file at the end of the simulation process */
public void fileOverwriting(String clientList_filePath,String recipient_details) throws IOException {
/*Create a fileWriter and a BufferedWriter object for the writing purpose with the parameters set to
overwrite the file*/
FileWriter fow = new FileWriter(clientList_filePath,false);
BufferedWriter overwriter = new BufferedWriter(fow);
//write the content to the file
overwriter.write(recipient_details);
//once done, close the stream
overwriter.close();
}
}
