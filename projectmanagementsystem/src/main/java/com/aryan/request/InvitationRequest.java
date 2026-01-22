package com.aryan.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InvitationRequest {

    private long projectId;

    private String email;

}
