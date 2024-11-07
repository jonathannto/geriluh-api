package br.eng.jonathan.geriluh_api.controller.open_api;

import br.eng.jonathan.geriluh_api.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import static br.eng.jonathan.geriluh_api.utils.APIConstants.PAGE_NUMBER;
import static br.eng.jonathan.geriluh_api.utils.APIConstants.PAGE_SIZE;

@Tag(name = "Users", description = "Manages the API users.")
public interface UserControllerOpenApi {

    @Operation(summary = "List users", description = "Retrieves all operational records within the system",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: userId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<UserDTO>>> list(
            @Parameter(hidden = true) Pageable pageable
    );
//
//    @Operation(summary = "Get user by ID", description = "Retrieves a single user by their ID")
//    ResponseEntity<EntityModel<UserDTO>> getUserById(@PathVariable Long userId);

}
