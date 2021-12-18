package br.com.contact.controller.request;

import br.com.contact.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {



    private String name;
    private String email;
    private String phone;



}
