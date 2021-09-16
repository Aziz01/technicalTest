package com.info.microdetails.webController;

import com.info.microdetails.common.Utils;
import com.info.microdetails.dao.UserDAO;
import com.info.microdetails.exception.ServiceInfoException;
import com.info.microdetails.model.User;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserDAO userDaoService;


    //Display all the users
    @RequestMapping(value = "/Users", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUsers() {

        List<User> users = userDaoService.findAll();
        if (!users.isEmpty()) {
            log.info("List of users: " + users.toString());
            return ResponseEntity.ok().body(users.toString());
        } else {
            log.info("The database is empty, no users to display");
            return ResponseEntity.ok().body("The database is empty, no users to display");
        }
    }

    //Displays the details of a registered user by id
    @GetMapping("/User/{id}")
    public ResponseEntity<String> getRegistredUser(@PathVariable long id) {

        Optional<User> user = userDaoService.findUserById(id);
        if (user.isPresent()) {
            log.info("The user was found, " + user.get().toString());
            return ResponseEntity.ok().body(user.get().toString());

        } else {
            log.error("The user does not exist you can check by another ID");
            return ResponseEntity.badRequest().body("The user does not exist you can check by another ID");

        }
    }

    //Add a new user to the database
    @PostMapping(value = "/AddUser")
    public ResponseEntity<String> addUser(@RequestBody String params) throws ServiceInfoException {

        String message = "";

        User user = new User();
        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(params);
            String userName = (String) jsonObject.get("userName") != null ? (String) jsonObject.get("userName") : "";
            String birthDay = (String) jsonObject.get("birthDay") != null ? (String) jsonObject.get("birthDay") : "";
            ;
            String countryOfResidence = (String) jsonObject.get("countryOfResidence") != null ? (String) jsonObject.get("countryOfResidence") : "";
            ;
            String phoneNumber = (String) jsonObject.get("phoneNumber") != null ? (String) jsonObject.get("phoneNumber") : "";
            String gender = (String) jsonObject.get("gender") != null ? (String) jsonObject.get("gender") : "";

            if (userName.equals("")) {
                throw new ServiceInfoException("The username is missing");
            } else if (birthDay.equals("")) {
                throw new ServiceInfoException("The birthday is missing and must be in this format dd/MM/yyyy");
            } else if (countryOfResidence.equals("")) {
                throw new ServiceInfoException("The country of residence is missing");
            }

            if (!Utils.isNotAdult(Utils.stringToDate(birthDay)) || !countryOfResidence.equals("France")) {
                message = "Only adult French residents are allowed to create an account!";
                throw new ServiceInfoException(message);
            }

            user.setUserName(userName);
            user.setBirthDay(Utils.stringToDate(birthDay));
            user.setCountryOfResidence(countryOfResidence);
            user.setPhoneNumber(phoneNumber);
            user.setGender(gender);
            userDaoService.save(user);
            log.info("The user was created successfully!");
            return ResponseEntity.ok().body("The user was created successfully!");

        } catch (ServiceInfoException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        } catch (ParseException e) {
            log.error(String.valueOf(e));
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (DateTimeParseException e) {
            log.error("The birthday must be in this format \"dd/MM/yyyy\"");
            return ResponseEntity.badRequest().body("The birthday must be in this format \"dd/MM/yyyy\"");

        }
    }

}