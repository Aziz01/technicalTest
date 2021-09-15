package com.info.microdetails.webController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.info.microdetails.dao.UserDAO;
import com.info.microdetails.exception.ServiceInfoException;
import com.info.microdetails.model.User;
import com.info.microdetails.common.Utils;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.ws.*;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserDAO userDaoService;

    //Display all the users
    @RequestMapping(value="/Users", method=RequestMethod.GET)
    public List<User>getAllUsers() {
        return userDaoService.findAll();
    }

    //Displays the details of a registered user
    @GetMapping("/Users/{id}")
    public Optional<User> getRegistredUser(@PathVariable long id) {
        return userDaoService.findUserById(id);
    }

     //Add a new user to the database
    @PostMapping(value = "/AddUser")
    public Response addUser(@RequestBody String params) throws ServiceInfoException {

        String message = "";

        User user = new User();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {

            jsonObject = (JSONObject) jsonParser.parse(params);
            String userName = (String) jsonObject.get("userName") != null ? (String) jsonObject.get("userName") : "";
            String birthDay = (String) jsonObject.get("birthDay") != null ? (String) jsonObject.get("birthDay") : "";;
            String countryOfResidence = (String) jsonObject.get("countryOfResidence") != null ? (String) jsonObject.get("countryOfResidence") : "";;
            String phoneNumber = (String) jsonObject.get("phoneNumber") != null ? (String) jsonObject.get("phoneNumber") : "";
            String gender = (String) jsonObject.get("gender") != null ? (String) jsonObject.get("gender") : "";

            if (userName.equals("")) {
                message = "The username must not be null or empty";
                throw new ServiceInfoException(message);
            }
            else if (birthDay.equals("")) {
                message = "The birthday must be in this format \"dd/MM/yyyy\"";
                throw new ServiceInfoException(message);
            }
            else if (countryOfResidence.equals("")) {
                message = "The country of residence must not be null or empty";
                throw new ServiceInfoException(message);
            }
            else if (Utils.isNotAdult(Utils.stringToDate(birthDay)) && !countryOfResidence.equals("France")) {
                message = "Only adult French residents are allowed to create an account!";
                throw new ServiceInfoException(message);
            }

            user.setUserName(userName);
            user.setBirthDay(Utils.stringToDate(birthDay));
            user.setCountryOfResidence(countryOfResidence);
            user.setPhoneNumber(phoneNumber);
            user.setGender(gender);

            return Response.status(Response.Status.ACCEPTED).entity(user).header("Access-Control-Allow-Origin","*").build();

        } catch (ServiceInfoException e){
            log.error(message);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).header("Access-Control-Allow-Origin","*").build();
        } catch(ParseException e){
            log.error(String.valueOf(e));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).header("Access-Control-Allow-Origin","*").build();
        } catch (DateTimeParseException e) {
            log.error("The birthday must be in this format \"dd/MM/yyyy\"");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).header("Access-Control-Allow-Origin","*").build();
        }
    }

}