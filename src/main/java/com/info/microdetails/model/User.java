package com.info.microdetails.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "userName")
    @Getter
    @Setter
    @Nullable
    private String userName;

    @Column(name = "birthDay")
    @Getter
    @Setter
    @Nullable
    private LocalDate birthDay;

    @Column(name = "countryOfResidence")
    @Getter
    @Setter
    @Nullable
    private String countryOfResidence;

    @Column(name = "phoneNumber")
    @Getter
    @Setter
    @Nullable
    private String phoneNumber;

    @Column(name = "gender")
    @Getter
    @Setter
    @Nullable
    private String gender;

    //default constructor
    public User() {

    }

    public User(String userName, LocalDate birthDay, String countryOfResidence) {
        this.userName = userName;
        this.birthDay = birthDay;
        this.countryOfResidence = countryOfResidence;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", birthDay=" + birthDay +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
