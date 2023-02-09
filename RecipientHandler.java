import java.io.*;
import java.util.*;
/*This class is used to - categorize the recipients into it's categories by considering the data fields in the record
* - assign their objects to seperate recipients' array lists
* - handle seperately sending birthday wishes on the the date of today for official friends and
personal recipients
* - update the records of existing recipients
* - get the total number of recipients in the clientList.txt file
* - save the changed states of recipients by overwriting the clientsList.txt file with updated
recipient object states */
class Recipient_Handler {
String clientList_filePath = "clientList.txt";
//a static integer variable id initialized to store the count of recipient objects in the application
static int total_recipients;
/*Separate ArrayList objects were defined to store objects of each class specified in each.*/
// Create an ArrayList object to store Official_Recipients' objects
static List<Official_Recipient> Officialrecipients = new ArrayList<Official_Recipient>();
// Create an ArrayList object to store Official_Friends' objects
static List<Official_Friend> Officialfriends = new ArrayList<Official_Friend>();
// Create an ArrayList object to store personal_Recipients' objects
static List<Personal_Recipient> Personalrecipients = new ArrayList<Personal_Recipient>();
// Create an ArrayList object to store birthday log of each recipient.
static List<BirthdayHolder> birthdaylog = new ArrayList<BirthdayHolder>();
public Recipient_Handler() {} //Constructor of the class
/*This method is used to consider the content of the records of each recipient in Recipients_list
*and categorize them into separate groups
*Each recipient will get sorted and and it's object will be added to the relevant Array List. */
public void recipient_categorizing(String[] Recipients_list) {
//iterate ove the Recipients_list array
for(int recipient_index=0;(recipient_index<Recipients_list.length);recipient_index++) {
//split the each record into it's components
String Record = Recipients_list[recipient_index];
String[] Record_components = Record.split(",");
//get the number of components or data fields in a record
int datafield_count = Record_components.length;
//if there are only 3 name fields, the recipient is an Offcial_Recipient
//It's object will be created and added to the Officialrecipients array list
if(datafield_count == 3) {
//official recipient's data fields are in the order of <name>,<email_address>,designation
Official_Recipient official_recipient = new Official_Recipient
(Record_components[0],Record_components[1],Record_components[2]);
Officialrecipients.add(official_recipient);
}
/*Both Official_Friend and Personal_Recipient have only 4 data fields in a it's record.
*Therefore, the target will be the position of email_address component.
*It's it is the second element in the record, it belongs to Official_Friend category
*It's it is the third element in the record, it belongs to Personal_Recipient category */
//get the second item of the Record_components list to an character list.
String second_component = Record_components[1];
char[] chr_array = second_component.toCharArray();
//thus, the target would be "@" and "." that are essential two characters in an email_address
char target_1 = '@';
char target_2 = '.';
boolean test_1 = false;
boolean test_2 = false;
//test the character_array for the existence of both targets "@" and "."
for(int chr_array_index=0;(chr_array_index<chr_array.length);chr_array_index++) {
if (chr_array[chr_array_index]== target_1) {
test_1 =true;
}
if (chr_array[chr_array_index]== target_2) {
test_2 =true;
}
}
//result would be the logical AND of the both target existence
boolean result = test_1 && test_2;
/*if there are 4 date fields in a record and both "@" and "." are existed in second data field,
* the recipient is an Official_Friend*/
if(datafield_count == 4 && result) {
//Offcial_Friend object will be created and added to the Officialfriends array list
Official_Friend official_friend = new Official_Friend
(Record_components[0],Record_components[1],Record_components[2],Record_components[3]);
Officialfriends.add(official_friend);
}
/*if there are 4 date fields in a record and not both "@" and "." are existed in second data field,
* the recipient is an Personal_Recipient*/
if(datafield_count == 4 && (!result)) {
/*Personal_Recipient object will be created and added to the Personalrecipients array
list*/
Personal_Recipient personal_recipient = new Personal_Recipient
(Record_components[0],Record_components[1],Record_components[2],Record_components[3]);
Personalrecipients.add(personal_recipient);
}
}
}
/*This method is used to create birthday holder objects of each personal recipient
* and send birthday greetings to the recipients who have birthday today.
*/
public void personalrecipients_to_BirthdayGreetinghandler() throws IOException {
//iterate over the Personalrecipients array list
for (int array_index=0; array_index<Personalrecipients.size(); array_index++) {
//seperately assign the field components of the recipients to seperate variables
String name = Personalrecipients.get( array_index).getName();
String email = Personalrecipients.get( array_index).getEmail();
String birthday = Personalrecipients.get( array_index).getBirthday();
//create a birthday greeting handler object generate the birthday_codex
BirthdayGreetingHandler birthday_greeting_handler_1 = new
BirthdayGreetingHandler(email,birthday);
int bday_codex = birthday_greeting_handler_1.birthdayCode_generator();
/*get the todays's date from calling the getToday method from utilities object and get the
today's codex*/
Utilities utilities_1 = new Utilities();
String today = utilities_1.getToday();
int today_code = utilities_1.todayCode(today);
/*if the recipient's birthday codex equals to today's codex, call the
*call the personalRecipientGreeting method to send him/her a birthday wish*/
if (bday_codex == today_code) {
birthday_greeting_handler_1.personalRecipientGreeting();
}
//create a BirthdayHolder object for each recipient and add it to the birthdaylog array list
BirthdayHolder bh = new BirthdayHolder(name,bday_codex);
birthdaylog.add(bh);
}
}
/*This method is used to create birthday holder objects of each official friend
* and send birthday greetings to the recipients who have birthday today.
*/
public void officialfriends_to_BirthdayGreetinghandler() throws IOException {
//iterate over the Personalrecipients array list
for (int array_index=0;array_index<Officialfriends.size();array_index++) {
//seperately assign the field components of the recipients to seperate variables
String name = Officialfriends.get(array_index).getName();
String email = Officialfriends.get(array_index).getEmail();
String birthday = Officialfriends.get(array_index).getBirthday();
//create a birthday greeting handler object generate the birthday_codex
BirthdayGreetingHandler birthday_greeting_handler_2 = new
BirthdayGreetingHandler(email,birthday);
int bday_codex = birthday_greeting_handler_2 .birthdayCode_generator();
/*get the todays's date from calling the getToday method from utilities object and get the
today's codex*/
Utilities utilities_2 = new Utilities();
String today = utilities_2.getToday();
int today_codex = utilities_2.todayCode(today);
/*if the recipient's birthday codex equals to today's codex, call the
*call the officialFriendGreeting method to send him/her a birthday wish*/
if (bday_codex == today_codex) {
birthday_greeting_handler_2 .officialFriendGreeting();
}
//create a BirthdayHolder object for each recipient and add it to the birthdaylog array list
BirthdayHolder bh = new BirthdayHolder(name,bday_codex);
birthdaylog.add(bh);
}
}
/*This method is defined to get the size of birthday log array list*/
public int birthdaylog_count() {
return birthdaylog.size();
}
/*This method is used to traverse over the birthdaylog array list and check that whether any object contains
* it's birthday_codex equals to input dates codex means he/she has his/her birthday on that day.
*Those names will be printed to the display*/
public void birthdaylogTraverse(int datecodex) {
/*integer variable count is defined here to track that whether is no one having their birthdays on
the given date. If so, it will be notified on the display*/
int count = 0;
//iterate over the birthday log array list
for (int array_index=0; array_index<birthdaylog.size(); array_index++) {
if(birthdaylog.get( array_index).getDaycodex() == datecodex) {
/*names of the recipients who have their birthdays on the given date will be printed to the
display*/
System.out.println(birthdaylog.get( array_index).getName());
count += 1; //increment the count
}
}
//when there is no on having their birthdays on the given date. notify it
if (count == 0) {
System.out.println("None of recipients have birthdays on this day");
}
}
/*This method is defined to update any record of a recipient
*It considers the name of the recipient to find it's object and then call the setter method
* to change the relevant state
*Here type 01 : official recipient, type 02: official friend, type 03: personal recipient*/
public void record_updater(String type_toUpdate,String name_toUpdate,String component_toUpdate,String
value_toUpdate){
//if the input recipient type is 01 or it refers to an official recipient
if (type_toUpdate.equals("1")) {
//iterate over the officialrecipients array list
for (int array_index=0;array_index<Officialrecipients.size();array_index++) {
//get the name of each recipient in the array list
String name = Officialrecipients.get(array_index).getName();
if (name.equals(name_toUpdate)) {
/*get the relevant state and update it by calling it's setter
*Here 1 refers to name, 2 refers to Email and 3 refers to designation*/
if(component_toUpdate.equals("1")) {
Officialrecipients.get(array_index).setName(value_toUpdate);
}
if(component_toUpdate.equals("2")) {
Officialrecipients.get(array_index).setEmail(value_toUpdate);
}
if(component_toUpdate.equals("3")) {
Officialrecipients.get(array_index).setDesignation(value_toUpdate);
}
}
}
}
//if the input recipient type is 02 or it refers to an official friend
if (type_toUpdate.equals("2")) {
//iterate over the officialfriends array list
for (int array_index=0; array_index<Officialfriends.size(); array_index++) {
//get the name of each recipient in the array list
String name = Officialfriends.get( array_index).getName();
/*get the relevant state and update it by calling it's setter
*Here 1 refers to name, 2 refers to Email, 3 refers to designation, 4 refers to birthday*/
if (name.equals(name_toUpdate)) {
if(component_toUpdate.equals("1")) {
Officialfriends.get( array_index).setName(value_toUpdate);
}
if(component_toUpdate.equals("2")) {
Officialfriends.get( array_index).setEmail(value_toUpdate);
}
if(component_toUpdate.equals("3")) {
Officialfriends.get( array_index).setDesignation(value_toUpdate);
}
if(component_toUpdate.equals("4")) {
Officialfriends.get( array_index).setBirthday(value_toUpdate);
}
}
}
}
//if the input recipient type is 03 or it refers to a personal recipient
if (type_toUpdate.equals("3")) {
//iterate over the personalrecipients array list
for (int array_index=0; array_index<Personalrecipients.size(); array_index++) {
//get the name of each recipient in the array list
String name = Personalrecipients.get( array_index).getName();
/*get the relevant state and update it by calling it's setter
*Here 1 refers to name, 2 refers to Email,4 refers to birthday and 5 refers to
nickname*/
if (name.equals(name_toUpdate)) {
if(component_toUpdate.equals("1")) {
Personalrecipients.get( array_index).setName(value_toUpdate);
}
if(component_toUpdate.equals("2")) {
Personalrecipients.get( array_index).setEmail(value_toUpdate);
}
if(component_toUpdate.equals("4")) {
Personalrecipients.get( array_index).setBirthday(value_toUpdate);
}
if(component_toUpdate.equals("5")) {
Personalrecipients.get( array_index).setNickname(value_toUpdate);
}
}
}
}
//display the succession of updating
System.out.println("Updation done successfully !");
}
/*This method is created to record back the states of the objects created back to clientsList.txt file for
* further using.*/
public void Record_Merger() throws IOException {
//iterate over the Officialrecipients array list
for (int arraylist_index=0;arraylist_index<Officialrecipients.size();arraylist_index++) {
//get the name , email, designation field components to separate variables.
String name = Officialrecipients.get(arraylist_index).getName();
String email = Officialrecipients.get(arraylist_index).getEmail();
String designation = Officialrecipients.get(arraylist_index).getDesignation();
//writing format will be assigned in a particular order
String record_towrite = (name+","+email+","+designation);
//Utilities object will be created to obtain the functionality of fileReading and fileOverwriting
Utilities utilities_2 = new Utilities();
//first record will be overwrite the existing content of the file
if(arraylist_index == 0) {
utilities_2.fileOverwriting(clientList_filePath,record_towrite);
}
//rest of record will be appended to the after that
else {
utilities_2.fileWriting(clientList_filePath,record_towrite);
}
}
//iterate over the Officialfriends array list
for (int y=0;y<Officialfriends.size();y++) {
//get the name , email, birthday, designation field components to separate variables.
String name = Officialfriends.get(y).getName();
String email = Officialfriends.get(y).getEmail();
String birthday = Officialfriends.get(y).getBirthday();
String designation = Officialfriends.get(y).getDesignation();
//writing format will be assigned in a particular order
String record_towrite = (name+","+email+","+designation+","+birthday);
//Utilities object will be created to obtain the functionality of fileReading and fileOverwriting
Utilities utilities_3 = new Utilities();
//record content will get recorded to the file
utilities_3.fileWriting(clientList_filePath,record_towrite);
}
//iterate over the Personalrecipients array list
for (int g=0;g<Personalrecipients.size();g++) {
//get the name , email, birthday, nickname field components to separate variables.
String name = Personalrecipients.get(g).getName();
String email = Personalrecipients.get(g).getEmail();
String birthday = Personalrecipients.get(g).getBirthday();
String nickname = Personalrecipients.get(g).getNickname();
//writing format will be assigned in a particular order
String record_towrite = (name+","+email+","+nickname+","+birthday);
//Utilities object will be created to obtain the functionality of fileReading and fileOverwriting
Utilities utilities_4 = new Utilities();
//record content will get recorded to the file
utilities_4.fileWriting(clientList_filePath,record_towrite);
}
}
/*A method is defined to get the total count of Recipient's objects created in the application*/
public void recipient_count() {
//get the size of all 3 Recipient's array lists
int total_official_recipients = Officialrecipients.size();
int total_official_friends = Officialfriends.size();
int total_personal_recipients = Personalrecipients.size();
//Sum of the all sizes would be the total count of recipient objects created
//Hence print the result to the display
total_recipients = total_official_recipients + total_official_friends + total_personal_recipients;
System.out.println("Total Recipient Records : "+total_recipients);
}
}