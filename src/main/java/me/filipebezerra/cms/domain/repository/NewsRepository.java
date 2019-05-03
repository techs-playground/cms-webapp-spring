package me.filipebezerra.cms.domain.repository;

import me.filipebezerra.cms.domain.models.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News, String> {}