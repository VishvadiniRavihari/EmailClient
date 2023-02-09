/*This is the class of Official_Friend who inherited from Recipient abstract class
*Only two extra attributes for designation and birthday are defined here and
* the states of name and email are inherited from abstract parent class of Recipient.
*Getters and setters are explicitly defined here to access the states of the designation and birthday.
*Extra method called display is defined to print details of the Official_Friend */
class Official_Friend extends Recipient {
    private String designation;
    private String birthday;
    /*Within the constructor, initial states for designation and birthday are assigned.
    *States of name and email are referred to the parent class's states via super keyword*/
    public Official_Friend(String name,String email,String designation,String birthday) {
    super(name,email);
    this.designation = designation;
    this.birthday = birthday;
    }
    //getter method to return Official_Friend's's birthday
    public String getBirthday() {
    return this.birthday;
    }
    //getter method to return Official_Friend's's designation
    public String getDesignation() {
    return this.designation;
    }
    //setter method to set Official_Friend's's birthday
    public void setBirthday(String new_birthday){
    this.birthday = new_birthday;
    }
    //setter method to set Official_Friend's's designation
    public void setDesignation(String new_designation){
    this.designation = new_designation;
    }
    //This method returns nothing but once called, it will print the details of the Offcial_Friend that it refers to.
    public void display() {
    System.out.println("You are calling an Official Friend");
    System.out.println("Name:"+this.getName());
    System.out.println("Email:"+this.getEmail());
    System.out.println("Designation:"+this.designation);
    System.out.println("Birthday:"+this.birthday);
    }
    }
