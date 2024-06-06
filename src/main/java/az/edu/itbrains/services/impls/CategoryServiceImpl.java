package az.edu.itbrains.services.impls;

import az.edu.itbrains.dtos.CategoryDtos.CategoryAddDto;
import az.edu.itbrains.dtos.CategoryDtos.CategoryHomeDto;
import az.edu.itbrains.dtos.CategoryDtos.CategoryUpdateDto;
import az.edu.itbrains.models.Category;
import az.edu.itbrains.repositories.CategoryRepostory;
import az.edu.itbrains.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepostory categoryRepostory;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createCategory(CategoryAddDto categoryAddDto) {
        Category category = new Category();
        category.setName(categoryAddDto.getName());
        categoryRepostory.save(category);
    }

    @Override
    public List<CategoryHomeDto> getHomeCategories() {
        List<CategoryHomeDto> categories = categoryRepostory.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryHomeDto.class))
                .toList();

        return categories;
    }

    @Override
    public void updateCategory(CategoryUpdateDto categoryUpdateDto, Long id) {
        Category findCategory = categoryRepostory.findById(id).orElseThrow();
        findCategory.setName(categoryUpdateDto.getName());
        categoryRepostory.save(findCategory);
    }

    @Override
    public CategoryUpdateDto findUpdateCategory(Long id) {
        Category findCategory = categoryRepostory.findById(id).orElseThrow();
        CategoryUpdateDto result = modelMapper.map(findCategory, CategoryUpdateDto.class);
        return result;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepostory.findById(id).orElseThrow();
        categoryRepostory.deleteById(id);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepostory.findById(id).orElseThrow();
    }
}
