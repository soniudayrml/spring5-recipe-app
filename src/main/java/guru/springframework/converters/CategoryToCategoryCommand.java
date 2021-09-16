package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {
    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category==null) {
            return null;
        }
        final CategoryCommand cat=new CategoryCommand();
        cat.setId(category.getId());
        cat.setDescription(category.getDescription());
        return  cat;
    }
}
