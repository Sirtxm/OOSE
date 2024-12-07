package Atm;

public abstract class Person {
    private String id;
    private String name;
    private String gender;

    public Person(String id, String name, String gender){
        validateId(id);
        this.id = id;
        validateName(name);
        this.name = name;
        validateGender(gender);
        this.gender = gender;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    private void validateId(String id){
        if (!id.matches("\\d{13}")) {
            throw new IllegalArgumentException("Account ID must be 13 characters long.");
        }
    }

    private void validateName(String name){
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must not exceed 50 characters.");
        }
    }

    private void validateGender(String gender){
        if (!gender.equalsIgnoreCase("Male") &&
        !gender.equalsIgnoreCase("Female")) {
        throw new IllegalArgumentException("Gender must be 'Male', 'Female'.");
    }
    }
}
