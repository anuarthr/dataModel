package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.User;

import java.util.List;
import java.util.Set;

public record SignupRequest (String username, String password, String email, Set<String> roles){

}