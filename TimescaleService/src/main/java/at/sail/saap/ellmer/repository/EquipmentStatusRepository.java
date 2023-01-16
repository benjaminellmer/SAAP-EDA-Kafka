package at.sail.saap.ellmer.repository;

import at.sail.saap.ellmer.model.db.EquipmentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentStatusRepository extends CrudRepository<EquipmentStatus, Long> {
}
