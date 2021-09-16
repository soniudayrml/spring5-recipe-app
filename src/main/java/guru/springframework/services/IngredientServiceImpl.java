package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository,IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe=recipeRepository.findById(recipeId);
        if(! recipe.isPresent())
        {
            log.error("Recipe is not present");
        }
        Recipe gotRecipe=recipe.get();

        Optional<IngredientCommand> opIngredientCommand=gotRecipe.getIngredients().stream().
                filter(ingredient -> ingredient.getId().equals(ingredientId)).
                map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if(!opIngredientCommand.isPresent())
        {
            log.error("Ingredient not present");
        }
        return opIngredientCommand.get();
    }
}
