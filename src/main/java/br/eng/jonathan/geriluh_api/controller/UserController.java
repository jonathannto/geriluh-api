package br.eng.jonathan.geriluh_api.controller;

import br.eng.jonathan.geriluh_api.controller.open_api.UserControllerOpenApi;
import br.eng.jonathan.geriluh_api.dto.UserDTO;
import br.eng.jonathan.geriluh_api.dto.assembler.UserDTOAssembler;
import br.eng.jonathan.geriluh_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/users", produces = "application/json")
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserService service;

    @Autowired
    private UserDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<UserDTO>>> list(Pageable pageable) {
        var users = service.listAllUsers(pageable)
                .map(user -> assembler.mapToEntityModelDTO(user));

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<EntityModel<UserDTO>> getUserById(Long userId) {

        var user = service.findUserById(userId);

        return ResponseEntity.ok()
                .body(assembler.mapToEntityModelDTO(user));
    }
}
