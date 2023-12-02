package com.nidaonder.library.dao;

import com.nidaonder.library.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {


}
