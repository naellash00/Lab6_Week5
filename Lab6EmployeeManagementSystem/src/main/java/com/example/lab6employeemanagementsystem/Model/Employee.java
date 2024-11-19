package com.example.lab6employeemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Employee {
    @NotEmpty(message = "ID Cannot Be Empty")
    @Size(min = 2, message = "ID Must Be Longer Than 2")
    private String id;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Size(min = 4, message = "Name Must Be Longer Than 4")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name Must Contains Characters Only")
    private String name;

    @NotEmpty(message = "Email Cannot Be Empty")
    @Email(message = "Enter A Valid Email")
    private String email;

    @NotEmpty(message = "Phone Number Cannot Be Empty")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$", message = "Phone Number Must Start With '05'")
    @Size(min = 10, max = 10, message = "Phone Number Must Be 10 Digits")
    private String phoneNumber;

    //@NotNull(message = "Age Cannot Be Empty")
    @Min(value = 25, message = "Age Must Be Older Than 25")
    @Positive(message = "Age Cannot Be Empty")
    private int age;

    @NotEmpty(message = "Position Cannot Be Empty")
    @Pattern(regexp = "^(supervisor|coordinator)$", message = "Position Must Be Either 'Supervisor' or 'Coordinator'")
    private String position;

    @AssertFalse(message = "Employee Must Not Be On Leave")
    private boolean onLeave = false;

    @NotNull(message = "Date Cannot Be Null")
    @PastOrPresent(message = "Enter A Valid Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime hireDate;

    @NotNull(message = "Annual Leave Cannot Be Null")
    @PositiveOrZero
    private int annualLeave;

}
