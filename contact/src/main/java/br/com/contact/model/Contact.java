package br.com.contact.model;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String email;
    private String phone;

    public static Contact convertRequestToEntity(ContactRequest request) {
        return request == null ? null : Contact.builder().email(request.getEmail()).name(request.getName()).phone(request.getPhone()).build();
    }

    public static ContactResponse convertEntityToResponse(Contact contact) {
        return contact == null ? null : ContactResponse.builder().id(
                                         contact.getId())
                                        .name(contact.getName())
                                        .email(contact.getEmail())
                                        .phone(contact.getPhone())
                                        .build();
    }


}
