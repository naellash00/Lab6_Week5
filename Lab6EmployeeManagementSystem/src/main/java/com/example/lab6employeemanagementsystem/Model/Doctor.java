package com.example.lab6employeemanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Doctor {
   @NotEmpty(message = "ID Cannot Be Empty")
   @Size(min = 10, max = 10, message = "ID Must Be 10 Digits")
    private String id;

   @NotEmpty(message = "Name Cannot Be Empty")
   @Size(min = 10, max = 25, message = "Name Must Be Between 10 and 25 Letters")
   private String fullName;

   @NotEmpty(message = "Specialization Cannot Be Empty")
   @Pattern(regexp = "^(Cardiology|Dermatology|Pediatrics|Neurology)$", message = "Enter a Valid Specialization")
   private String specialization;

   @NotNull(message = "Experience Cannot Be Empty")
   @Min(value = 2, message = "Experience Must Be More Than 1 Year")
   @Max(value = 40, message = "Experience Cannot Be More Than 40")
   private int experienceYears;

   @NotEmpty(message = "Phone Number Cannot Be Empty")
   @Pattern(regexp = "^05\\d{8}$", message = "Phone Number Must Start with '05'")
   @Size(min=10, max=10, message = "Phone Number Must Be 10 Digits")
   private String phoneNumber;

   @NotEmpty(message = "Schedule Cannot Be Null")
   @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)-(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday),\\s\\d{1,2}(AM|PM)-\\d{1,2}(AM|PM)$")
   private String schedule;

}
