package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.News;
import me.filipebezerra.cms.domain.repository.NewsRepository;
import me.filipebezerra.cms.domain.vo.NewsRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News update(String id, NewsRequest newsRequest) {
        final News news = newsRepository.findOne(id);
        news.setTitle(newsRequest.getTitle());
        news.setContent(newsRequest.getContent());
        news.setCategories(newsRequest.getCategories());
        news.setTags(newsRequest.getTags());
        return news;
    }

    public News create(NewsRequest newsRequest) {
        final News news = new News();
        news.setId(UUID.randomUUID().toString());
        news.setTitle(newsRequest.getTitle());
        news.setContent(newsRequest.getContent());
        news.setCategories(newsRequest.getCategories());
        news.setTags(newsRequest.getTags());
        return news;
    }

    public void delete(String id) {
        final News news = newsRepository.findOne(id);
        newsRepository.delete(news);
    }

    public News findOne(String id) {
        return newsRepository.findOne(id);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

}
