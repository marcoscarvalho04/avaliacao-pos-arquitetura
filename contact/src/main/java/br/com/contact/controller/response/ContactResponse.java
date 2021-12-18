package br.com.contact.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ContactResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
}
