package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.DishController;
import br.eng.jonathan.geriluh_api.dto.DishDTO;
import br.eng.jonathan.geriluh_api.model.Dish;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DishDTOAssembler {

    private final ModelMapper modelMapper;

    public DishDTOAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Dish mapToEntity(DishDTO dishDTO) {
        return modelMapper.map(dishDTO, Dish.class);
    }

    public DishDTO mapToDTO(Dish dish) {
        return modelMapper.map(dish, DishDTO.class);
    }

    public EntityModel<DishDTO> mapToEntityModelDTO(Dish dish) {
        return EntityModel.of(
                mapToDTO(dish),
                linkTo(methodOn(DishController.class)
                        .getDishById(dish.getDishId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(DishController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(DishController.class)
                        .createNewDish(null, null))
                        .withRel("create")
                        .withType("POST"),
                linkTo(methodOn(DishController.class)
                        .updateDish(dish.getDishId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(DishController.class)
                        .deleteDish(dish.getDishId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }
}