package com.cartservice.app.dto;

import com.cartservice.app.model.ConfirmationStatus;
import lombok.Data;


@Data
public class UpdatedCartDTO {

    public String userId;
    public ConfirmationStatus confirmationStatus;
}
