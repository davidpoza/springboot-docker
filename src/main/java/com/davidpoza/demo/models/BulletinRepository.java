package com.davidpoza.demo.models;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface BulletinRepository extends CrudRepository<Bulletin, UUID> {

}
