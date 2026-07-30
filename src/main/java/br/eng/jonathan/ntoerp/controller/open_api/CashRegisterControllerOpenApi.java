package br.eng.jonathan.ntoerp.controller.open_api;

import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.dto.CashRegisterInDTO;
import br.eng.jonathan.ntoerp.dto.CashRegisterOutDTO;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

import static br.eng.jonathan.ntoerp.utils.APIConstants.PAGE_NUMBER;
import static br.eng.jonathan.ntoerp.utils.APIConstants.PAGE_SIZE;

@Tag(name = "Cash Register", description = "Manages API's Cash Registers.")
public interface CashRegisterControllerOpenApi {

    @Operation(summary = "List cashes registers", description = "Retrieves all cashes registers within the system",
            parameters = {
                    @Parameter(name = "page", description = "Page to be loaded", schema = @Schema(type = "integer", defaultValue = PAGE_NUMBER)),
                    @Parameter(name = "size", description = "Number of records", schema = @Schema(type = "integer", defaultValue = PAGE_SIZE)),
                    @Parameter(name = "sort", description = "Record sorting. Use [fieldName],[order (asc/desc)] e.g.: cashRegisterId,desc", schema = @Schema(type = "string"))
            }
    )
    ResponseEntity<Page<EntityModel<CashRegisterDTO>>> list(
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Get cash register by ID", description = "Retrieves a single cash register by their ID")
    ResponseEntity<EntityModel<CashRegisterDTO>> getCashRegisterById(
            @Parameter(description = "ID of the cash register", example = "1") @PathVariable Long cashRegisterId
    );

    @Operation(summary = "New cash register", description = "Registers a new cash register (generic CRUD).")
    ResponseEntity<EntityModel<CashRegisterDTO>> createNewCashRegister(
            @Valid @RequestBody CashRegisterDTO cashRegisterDTO,
            HttpServletResponse response
    ) throws NotFoundException;

    @Operation(summary = "Open cash register", description = "Opens a new cash register for a specific user. Checks if user already has an active register.")
    ResponseEntity<EntityModel<CashRegisterOutDTO>> openCashRegister(
            @Valid @RequestBody CashRegisterInDTO openCashRegisterInDTO
    );

    @Operation(summary = "Close cash register", description = "Closes an active cash register by setting end balance and closing timestamp.")
    ResponseEntity<EntityModel<CashRegisterDTO>> closeCashRegister(
            @Parameter(description = "ID of the cash register to close", example = "1") @PathVariable Long cashRegisterId,
            @Parameter(description = "Final balance at closing", example = "150.50") @RequestParam BigDecimal endBalance,
            @Parameter(description = "Closing notes or observation", example = "Shift closed without discrepancies") @RequestParam(required = false) String notes
    );

    @Operation(summary = "Updates a cash register", description = "Updates a cash register using its ID")
    ResponseEntity<EntityModel<CashRegisterDTO>> updateCashRegister(
            @Parameter(description = "ID of the cash register", example = "1") @PathVariable Long idCashRegister,
            @Valid @RequestBody CashRegisterDTO cashRegisterDTO
    );

    @Operation(summary = "Deletes a cash register", description = "Deletes a cash register using its ID")
    ResponseEntity<Void> deleteCashRegister(
            @Parameter(description = "ID of the cash register", example = "1") @PathVariable Long cashRegisterId
    );

}