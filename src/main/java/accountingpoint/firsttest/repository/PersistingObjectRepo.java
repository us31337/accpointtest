package accountingpoint.firsttest.repository;

import accountingpoint.firsttest.entity.PersistingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersistingObjectRepo extends JpaRepository<PersistingObject, Long> {

    @Query(value = "SELECT * FROM objects o WHERE o.parent_object_id = :parentId",
            nativeQuery = true)
    List<PersistingObject> findByParentObjectId(long parentId);

}
