/*This class is defined to create objects which have it's states as the name of
* the recipient and the specific code generated for his/her birth date */
class BirthdayHolder {
    private String name;
    private int birthday_codex;
    //within the constructor, initial state assignment will be carried out
    public BirthdayHolder(String name, int daycodex) {
    this.name = name;
    this.birthday_codex = daycodex;
    }
    //a getter method is defined to return the name
    public String getName() {
    return this.name;
    }
    //a getter method is defined to return the specific code of birthday
    public int getDaycodex() {
    return this.birthday_codex;
    }
    //no setters are defined since there is no need
    }