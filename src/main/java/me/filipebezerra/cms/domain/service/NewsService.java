package me.filipebezerra.cms.domain.service;

import me.filipebezerra.cms.domain.exceptions.NewsNotFoundException;
import me.filipebezerra.cms.domain.models.News;
import me.filipebezerra.cms.domain.models.Review;
import me.filipebezerra.cms.domain.repository.NewsRepository;
import me.filipebezerra.cms.domain.vo.NewsRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News findOne(String id) {
        final Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            return news.get();
        } else {
            throw new NewsNotFoundException(id);
        }
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public List<News> revisedNews() {
        return newsRepository.findAll().stream().filter(News::revised).collect(Collectors.toList());
    }

    @Transactional
    public News create(NewsRequest newsRequest) {
        final News news = new News();
        news.setTitle(newsRequest.getTitle());
        news.setContent(newsRequest.getContent());
        news.setCategories(newsRequest.getCategories());
        news.setTags(newsRequest.getTags());
        return newsRepository.save(news);
    }

    @Transactional
    public News update(String id, NewsRequest newsRequest) {
        final Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            final News newsUpdated = news.get();
            newsUpdated.setTitle(newsRequest.getTitle());
            newsUpdated.setContent(newsRequest.getContent());
            newsUpdated.setCategories(newsRequest.getCategories());
            newsUpdated.setTags(newsRequest.getTags());
            return newsRepository.save(newsUpdated);
        } else {
            throw new NewsNotFoundException(id);
        }
    }

    @Transactional
    public Review review(final String id, final String userId) {
        final Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            final News newsRevised = news.get();
            final Review review = newsRevised.review(userId, "approved");
            newsRepository.save(newsRevised);
            return review;
        } else {
            throw new NewsNotFoundException(id);
        }
    }

    @Transactional
    public void delete(String id) {
        newsRepository.deleteById(id);
    }

}
