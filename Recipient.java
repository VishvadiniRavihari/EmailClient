/*This is the abstract class of the recipient.
*The name and the email of the recipient is defined here since these two
* states are common to the all 3 types of recipients.
*There is only setters and getters are defined here to access name and email states. */
abstract class Recipient {
private String name;
private String email;
//Within the constructor,initial state assignments are defined.
public Recipient(String name,String email) {
this.name = name;
this.email = email;
}
//getter method for recipient's name
public String getName() {
return this.name;
}
//getter method for recipient's email
public String getEmail() {
return this.email;
}
//setter method for recipient's name
public void setName(String new_name){
this.name = new_name;
}
//setter method for recipient's email
public void setEmail(String new_email){
this.email = new_email;
}
}