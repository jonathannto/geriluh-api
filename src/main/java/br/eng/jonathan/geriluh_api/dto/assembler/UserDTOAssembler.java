package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.UserController;
import br.eng.jonathan.geriluh_api.dto.UserDTO;
import br.eng.jonathan.geriluh_api.model.User;
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

    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
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
//                linkTo(methodOn(UserController.class)
//                        .getOperationalFactByCode(operationalFact.getOperationalFactId()))
//                        .withSelfRel()
//                        .withType("GET"),
                linkTo(methodOn(UserController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET")
//                linkTo(methodOn(OperationalFactController.class)
//                        .createOperationalFact(null, null))
//                        .withRel("create")
//                        .withType("POST"),
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
