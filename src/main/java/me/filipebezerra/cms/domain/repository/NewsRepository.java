package me.filipebezerra.cms.domain.repository;

import me.filipebezerra.cms.domain.models.News;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NewsRepository extends ReactiveMongoRepository<News, String> {}