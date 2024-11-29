package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.UserController;
import br.eng.jonathan.geriluh_api.dto.UserDTO;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.Address;
import br.eng.jonathan.geriluh_api.model.User;
import br.eng.jonathan.geriluh_api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserDTOAssembler {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService service;

    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    public User mapToEntity(UserDTO userDTO) throws NotFoundException {
        User user = modelMapper.map(userDTO, User.class);

        if(userDTO.getUserId() != null) {
            User previousUser = service.findUserById(userDTO.getUserId());
            user.setUserId(previousUser.getUserId());
            user.setName(userDTO.getName() != null ? userDTO.getName() : previousUser.getName());
            user.setCpf(userDTO.getCpf() != null ? userDTO.getCpf() : previousUser.getCpf());
            user.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : previousUser.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber() != null ? userDTO.getPhoneNumber() : previousUser.getPhoneNumber());

            Address address = user.getAddress() != null ? user.getAddress() : previousUser.getAddress();
            if (address == null) {
                address = new Address();
            }

            address.setStreetAddress(userDTO.getAddress().getStreetAddress() != null ?
                    userDTO.getAddress().getStreetAddress() : previousUser.getAddress().getStreetAddress());
            address.setAddressNumber(userDTO.getAddress().getAddressNumber() != null ?
                    userDTO.getAddress().getAddressNumber() : previousUser.getAddress().getAddressNumber());
            address.setCity(userDTO.getAddress().getCity() != null ?
                    userDTO.getAddress().getCity() : previousUser.getAddress().getCity());
            address.setState(userDTO.getAddress().getState() != null ?
                    userDTO.getAddress().getState() : previousUser.getAddress().getState());
            address.setZipCode(userDTO.getAddress().getZipCode() != null ?
                    userDTO.getAddress().getZipCode() : previousUser.getAddress().getZipCode());

            user.setAddress(address);

            user.setBirthDate(userDTO.getBirthDate() != null ? userDTO.getBirthDate() : previousUser.getBirthDate());
            user.setUsername(userDTO.getUsername() != null ? userDTO.getUsername() : previousUser.getUsername());
            user.setPassword(userDTO.getPassword() != null ? userDTO.getPassword() : previousUser.getPassword());
            user.setSecurityQuestion(userDTO.getSecurityQuestion() != null ? userDTO.getSecurityQuestion() : previousUser.getSecurityQuestion());
            user.setSecurityAnswer(userDTO.getSecurityAnswer() != null ? userDTO.getSecurityAnswer() : previousUser.getSecurityAnswer());
            user.setUserType(userDTO.getUserType() != null ? userDTO.getUserType() : previousUser.getUserType());
            user.setCreatedAt(previousUser.getCreatedAt());  // O campo `createdAt` normalmente não é atualizado
            user.setStatus(userDTO.getStatus() != null ? userDTO.getStatus() : previousUser.getStatus());
            user.setProfilePicture(userDTO.getProfilePicture() != null ? userDTO.getProfilePicture() : previousUser.getProfilePicture());
            user.setAdditionalNotes(userDTO.getAdditionalNotes() != null ? userDTO.getAdditionalNotes() : previousUser.getAdditionalNotes());
        }
        return user;
    }

    /**
     * Uses {@link ModelMapper} to convert an entity into a DTO
     * while also transforming the class into an {@link EntityModel} and adding links for HATEOAS
     * @author Jonathan Nascimento
     * @since 1.0
     * @serialData 2024-11-07
     * @param user {@link br.eng.jonathan.geriluh_api.model.User}
     * @return {@link EntityModel} <{@link br.eng.jonathan.geriluh_api.dto.UserDTO}>
     */
    public EntityModel<UserDTO> mapToEntityModelDTO(User user) {
        return EntityModel.of(mapToDTO(user),
                linkTo(methodOn(UserController.class)
                        .getUserById(user.getUserId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(UserController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(UserController.class)
                        .createNewUser(null, null))
                        .withRel("create")
                        .withType("POST")
//                linkTo(methodOn(OperationalFactController.class)
//                        .updateOperationalFact(operationalFact.getOperationalFactId(), null))
//                        .withRel("update")
//                        .withType("PUT"),
//                linkTo(methodOn(OperationalFactController.class)
//                        .deleteOperationalFact(operationalFact.getOperationalFactId()))
//                        .withRel("delete")
//                        .withType("DELETE")
        );
    }

}
