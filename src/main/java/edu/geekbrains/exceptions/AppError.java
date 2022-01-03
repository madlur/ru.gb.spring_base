package edu.geekbrains.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AppError {
    private String message;
    private Date date;

    public AppError(String message) {
        this.message = message;
        this.date = new Date();
    }

}
