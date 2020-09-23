package accountingpoint.firsttest.service;

import accountingpoint.firsttest.entity.PersistingObject;

import java.util.List;

public interface PersistingObjectService {
    PersistingObject addObject(PersistingObject object);
    void delete(PersistingObject object);
    List<PersistingObject> getAll();
    List<PersistingObject> findByParentObjectId(long parentId);
}
