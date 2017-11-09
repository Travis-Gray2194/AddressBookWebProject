package me.travisgray.demo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;


//
//Validate as follows:
//
//        - Names should not be shorter than 2 digits
//
//        - Email addresses should be valid
//
//        - Cell phone numbers should be numbers.


@Entity
public class AddressBook {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Length(max=10,min=10,message="Phone number is not valid. Should be of length 10.")
    @NotEmpty(message="Phone field is mandatory.") @NumberFormat(style= NumberFormat.Style.NUMBER)
    private String phonenumber;



    //    @NotNull won’t allow a null value, which is what Spring MVC generates if the entry is empty
    @NotNull
//    @Size(min=2, max=30) will only allow names between 2 and 20 characters long
    @Size(min=2, max=30)
    private String name;

    @NotEmpty
    @Email
    private String email;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "id=" + id +
                ", phonenumber='" + phonenumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
