package com.davidpoza.demo.models;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NotesRepository extends CrudRepository<Note, UUID> {
}