package com.example.lab6employeemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Patient {

    @NotEmpty(message = "ID Cannot Be Empty")
    @Size(min = 10, max = 10, message = "ID Must Be 10 Digits Only")
    private String ID;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Size(min = 10, max = 25, message = "Name Must Be Between 10-25 Letters")
    private String fullName;

    @NotNull(message = "Age Cannot Be Empty")
    @Pattern(regexp = "^(0|[1-9][0-9]*m?|[1-9][0-9]*)$\n", message = "Enter a Valid Age (ends with m for babies in months)")
    private int age;

    @NotEmpty(message = "Symptoms Cannot Be Empty")
    @Size(min = 10, message = "Symptoms Must Be Longer Than 10 Letters")
    private String symptoms;

    @NotNull(message = "Date Cannot Be Empty")
    @FutureOrPresent(message = "Choose Valid Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime Date;

    @NotEmpty(message = "Phone Number Cannot Be Empty")
    @Pattern(regexp="^05\\d{8}$", message = "Phone Number Must Start with 05")
    @Size(min = 10, max = 10, message = "Phone Number Must be 10 digits")
    private String phoneNumber;

}
