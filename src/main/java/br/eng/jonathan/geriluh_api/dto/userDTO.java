package br.eng.jonathan.geriluh_api.dto;

import br.eng.jonathan.geriluh_api.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {

    private Long idUser;

    @NotNull(message = "{USER.NAME_NOT_BLANK}")
    @Size(min = 6, max = 255, message = "{USER.NAME_SIZE}")
    private String name;

    @Pattern(regexp = "^(|\\d{11})$", message = "{USER.CPF_PATTERN.regexp}")
    private String cpf;

    @NotBlank(message = "{USER.EMAIL_NOT_BLANK}")
    @Email(message = "{USER.EMAIL_EMAIL}")
    private String email;

    @NotBlank(message = "{USER.PHONE_NUMBER_NOT_BLANK}")
    @Size(min = 10, max = 14, message = "{USER.PHONE_NUMBER_SIZE}")
    private String phoneNumber;

    @Embedded
    private Address address;

    @Column(name = "birth_date_usr")
    private OffsetDateTime birthDate;

    @Column(name = "user_nam_usr")
    private String username;

    @Column(name = "pass_usr")
    private String password;

    @Column(name = "sec_quest_usr")
    private String securityQuestion;

    @Column(name = "sec_answ_usr")
    private String securityAnswer;

    @Column(name = "user_type_usr")
    private String userType;

    @Column(name = "crt_at_dat_usr", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "sts_usr")
    private String status;

    @Lob
    @Column(name = "pro_pic_user")
    private byte[] profilePicture;

    @Column(name = "addit_notes_usr", columnDefinition = "TEXT")
    private String additionalNotes;
}
