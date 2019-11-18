package com.course.phonedir.consoleclient;

import com.course.phonedir.consoleclient.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

public class ClientController {

    static final String URL_GET_ALL_USERS = "http://localhost:8080/rest/getallusers";
    static final String URL_GET_USER_BY_ID = "http://localhost:8080/rest/getuserbyid/";
    static final String URL_ADD_USER = "http://localhost:8080/rest/adduser";
    static final String URL_GENERATE_PDF_FOR_ALL_USERS = "http://localhost:8080/rest/pdf";
    static final String USER_FOR_TEST = "{\n" +
            "  \"name\" : \"Yaroslav\",\n" +
            "  \"password\" : \"$2a$10$S6yhh0iXcRAMemqxOUNhgufX9l8Qql1lv7rGMgSMqHi4VHUBZ0V7y\",\n" +
            "  \"roles\" : \"ROLE_REGISTERED_USER,ROLE_BOOKING_MANAGER\",\n" +
            "  \"userAccounts\" : [ {\n" +
            "    \"id\" : 8,\n" +
            "    \"cash\" : 500,\n" +
            "    \"phoneNumber\" : {\n" +
            "      \"number\" : \"099808124\",\n" +
            "      \"companyName\" : \"ROBCODE\"\n" +
            "    }\n" +
            "  }]\n" +
            "}";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public String getAllUsers() throws JsonProcessingException {
        User[] users = restTemplate.getForObject(URL_GET_ALL_USERS, User[].class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
    }

    public String getUserById(int id) throws JsonProcessingException {
        User user = restTemplate.getForObject(URL_GET_USER_BY_ID+id, User.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
    }

    //Response 405 METHOD_NOT_ALLOWED (
    public String generatePdfForAllUsers() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<byte[]> result =
                restTemplate.exchange(URL_GENERATE_PDF_FOR_ALL_USERS, HttpMethod.GET, entity, byte[].class);
        return result.getStatusCode().toString();
    }

    public HttpStatus add(String jsonUser) throws IOException {
        User user = convertFromJsonToUser(jsonUser);
        HttpEntity<User> httpEntity = new HttpEntity<>(user);
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity(URL_ADD_USER, httpEntity, User.class);
        return userResponseEntity.getStatusCode();
    }

    private  User convertFromJsonToUser(String user) throws IOException {
        return mapper.readValue(user, User.class);
    }

    public static void main(String[] args) throws IOException {
        ClientController clientController = new ClientController();

//        System.out.println(clientController.generatePdfForAllUsers());

        System.out.println(clientController.add(USER_FOR_TEST));
        System.out.println(clientController.getAllUsers());
    }
}
