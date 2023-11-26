package com.nttdata.clientservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "client")
public class Client {

	@Id
	private String id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String createdAt;
    private String address;
    private String city;
    private String country;
    private String telephone;


}
