package com.davidpoza.demo.models;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface FeedRepository extends CrudRepository<Feed, UUID> {

}