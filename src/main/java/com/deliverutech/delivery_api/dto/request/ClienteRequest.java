package com.deliverutech.delivery_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {


    @NotBlank(message = "O nome do cliente é obrigatório")
    @Size(min=10, max=100, message = "O nome do cliente deve ter entre 10 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O telefone do cliente é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve ter de 10 a 11 digitos")
    private String telefone;

    @Email
    @Size(max = 255, message = "O email deve ter no máximo 255 caracteres")
    @NotBlank(message = "O email do cliente é obrigatório")
    private String email;

    @NotBlank(message = "O endereço do cliente é obrigatório")
    @Size(max=255, message = "O endereço do cliente não deve ter mais de 255 caracteres")
    private String endereco;

}
