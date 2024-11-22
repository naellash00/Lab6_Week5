package com.example.lab6employeemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OnlineServiceAdmin {
    @NotEmpty(message = "ID Cannot Be Empty")
    @Size(min = 8, max = 8, message = "ID Must Be 8 Characters")
    private String adminID;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Size(min = 8, max = 25, message = "Name Must Be Between 8 to 25 Letters")
    private String fullName;

    @NotEmpty(message = "Email Cannot Be Empty")
    @Email(message = "Enter A Valid Email")
    private String email;

    @NotEmpty(message = "Role Cannot Be Empty")
    @Pattern(regexp = "^(Manager|Support Staff)$", message = "Role Must Be Either 'Manager' or 'Staff'")
    private String role;

    @NotEmpty(message = "Contact Number Cannot Be Empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone Number Must Start with '05'")
    @Size(min=10, max=10, message = "Phone Number Must Be 10 Digits")
    private String contactNumber;

    @NotNull(message = "Date Cannot Be Empty")
    @PastOrPresent(message = "Enter A Valid Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime employmentDate;
}
