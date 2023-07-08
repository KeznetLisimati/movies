package zw.co.kez.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.kez.movies.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
