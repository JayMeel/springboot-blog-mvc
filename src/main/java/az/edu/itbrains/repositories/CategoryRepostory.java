package az.edu.itbrains.repositories;

import az.edu.itbrains.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepostory extends JpaRepository<Category, Long> {
}
