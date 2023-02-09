/*This is the class of Personal_Recipient who inherited from Recipient abstract class
*Only the extra attributes for nickname and birthday are defined here and
* the states of name and email are inherited from abstract parent class of Recipient.
*Getters and setters are explicitly defined here to access nickname and birthday states.
*Extra method called display is defined to print details of the Personal_Recipient */
class Personal_Recipient extends Recipient {
    private String nickname;
    private String birthday;
    /*Within the constructor, initial states for nickname and birthday are assigned.
    *States of name and email are referred to the parent class's states via super keyword*/
    public Personal_Recipient(String name,String nickname,String email,String birthday) {
    super(name,email);
    this.nickname = nickname;
    this.birthday = birthday;
    }
    //getter method to return Personal_Recipient's birthday
    public String getBirthday(){
    return this.birthday;
    }
    //getter method to return Personal_Recipient's nickname
    public String getNickname(){
    return this.nickname;
    }
    //setter method to set Personal_Recipient's birthday
    public void setBirthday(String new_birthday){
    this.birthday = new_birthday;
    }
    //setter method to set Personal_Recipient's nickname
    public void setNickname(String new_nickname){
    this.nickname = new_nickname;
    }
    //This method returns nothing but once called, it will print the details of the Personal_Recipient that it refers to.
    public void display() {
    System.out.println("You are calling a Personal_Recipient");
    System.out.println("Name:"+this.getName());
    System.out.println("Nickname:"+this.nickname);
    System.out.println("Email:"+this.getEmail());
    System.out.println("Birthday:"+this.birthday);
    }
    }
