package accountingpoint.firsttest.service.impl;

import accountingpoint.firsttest.entity.PersistingObject;
import accountingpoint.firsttest.repository.PersistingObjectRepo;
import accountingpoint.firsttest.service.PersistingObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersisitingObjectServiceImpl implements PersistingObjectService {

    @Autowired
    private PersistingObjectRepo objectRepo;

    @Override
    public PersistingObject addObject(PersistingObject object) {
        return objectRepo.saveAndFlush(object);
    }

    @Override
    public void delete(PersistingObject object) {
        objectRepo.delete(object);
    }

    @Override
    public List<PersistingObject> getAll() {
        return objectRepo.findAll();
    }

    @Override
    public List<PersistingObject> findByParentObjectId(long parentId) {
        return objectRepo.findByParentObjectId(parentId);
    }
}
