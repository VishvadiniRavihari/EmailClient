/*This is the class of Official_Recipient who inherited from Recipient abstract class
*Only an extra attribute for designation is defined here and
* the states of name and email are inherited from abstract parent class of Recipient.
*Getters and setters are explicitly defined here to access the state of the designation.
*Extra method called display is defined to print details of the Official_Recipient */
class Official_Recipient extends Recipient {
    private String designation;
    /*Within the constructor, an initial state for designation is assigned.
    *States of name and email are referred to the parent class's states via super keyword*/
    public Official_Recipient(String name,String email,String designation) {
    super(name,email);
    this.designation = designation;
    }
    //getter method to return Official_Recipient's designation
    public String getDesignation() {
    return this.designation;
    }
    //setter method to set Official_Recipient's designation
    public void setDesignation(String new_designation){
    this.designation = new_designation;
    }
    //This method returns nothing but once called, it will print the details of the Official_Recipient that it refers to.
    public void display() {
    System.out.println("You are calling an Official_Recipient");
    System.out.println("Name:"+this.getName());
    System.out.println("Email:"+this.getEmail());
    System.out.println("Designation:"+this.designation);
    }
    }