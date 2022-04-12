package com.example.accessingdatajpa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {


    private Long id;

    @NotNull (message = "firstName Cannot be empty")
    @NotEmpty (message = "firstName required")
    @Size(max = 255)
    private String firstName;

    @NotNull (message = "lastName cannot be empty")
    @NotEmpty (message = "lastName required")
    @Size(max = 255)
    private String lastName;

}
