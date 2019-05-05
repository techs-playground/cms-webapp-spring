package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.models.News;
import me.filipebezerra.cms.domain.models.Review;
import me.filipebezerra.cms.domain.repository.NewsRepository;
import me.filipebezerra.cms.domain.vo.NewsRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Mono<News> findOne(String id) {
        return newsRepository.findById(id);
    }

    public Flux<News> findAll() {
        return newsRepository.findAll();
    }

    public Flux<News> revisedNews() {
        return newsRepository.findAll().filter(News::revised);
    }

    public Mono<News> create(NewsRequest newsRequest) {
        final News news = new News();
        news.setTitle(newsRequest.getTitle());
        news.setContent(newsRequest.getContent());
        news.setCategories(newsRequest.getCategories());
        news.setTags(newsRequest.getTags());
        return newsRepository.save(news);
    }

    public Mono<News> update(String id, NewsRequest newsRequest) {
        return newsRepository.findById(id).flatMap(news -> {
            news.setTitle(newsRequest.getTitle());
            news.setContent(newsRequest.getContent());
            news.setCategories(newsRequest.getCategories());
            news.setTags(newsRequest.getTags());
            return newsRepository.save(news);
        });
    }

    public Mono<Review> review(final String id, final String userId) {
        return newsRepository.findById(id).flatMap(news -> {
            final Review review = news.review(userId, "approved");
            newsRepository.save(news);
            return Mono.just(review);
        });
    }

    public void delete(String id) {
        newsRepository.deleteById(id);
    }

}
