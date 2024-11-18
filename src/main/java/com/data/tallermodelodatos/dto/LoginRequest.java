package com.data.tallermodelodatos.dto;

import lombok.Data;


public record LoginRequest (
        String username,
        String password){

}