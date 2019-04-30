package me.filipebezerra.cms.domain.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.filipebezerra.cms.domain.models.News;
import me.filipebezerra.cms.domain.models.Review;
import me.filipebezerra.cms.domain.service.NewsService;
import me.filipebezerra.cms.domain.vo.NewsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@Api(tags = "news", description = "News API")
public class NewsResource {

    private final NewsService newsService;

    public NewsResource(final NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find news", notes = "Find news by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "News found"),
            @ApiResponse(code = 404, message = "News not found")
    })
    public ResponseEntity<News> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(newsService.findOne(id));
    }

    @GetMapping
    @ApiOperation(value = "List news", notes = "List all news")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "News found"),
            @ApiResponse(code = 404, message = "News not found")
    })
    public ResponseEntity<List<News>> findAll() {
        return ResponseEntity.ok(newsService.findAll());
    }

    @PostMapping
    @ApiOperation(value = "Create news", notes = "It permits create a new news")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "News found"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<News> newNews(@RequestBody NewsRequest newsRequest) {
        return new ResponseEntity<>(newsService.create(newsRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove news", notes = "It permits to remove a news")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "News removed successfully"),
            @ApiResponse(code = 404, message = "News not found")
    })
    public void removeNews(@PathVariable("id") String id) {
        newsService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update news", notes = "It permits to update a news")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "News updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "News not found")
    })
    public ResponseEntity<News> updateNews(@PathVariable("id") String id, @RequestBody NewsRequest newsRequest) {
        return new ResponseEntity<>(newsService.update(id, newsRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/review/{userId}")
    @ApiOperation(value = "Find review", notes = "Find a review from a news by user ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Review found"),
            @ApiResponse(code = 404, message = "Review not found")
    })
    public ResponseEntity<Review> review(@PathVariable("id") String id, @PathVariable("userId") String userId) {
        return ResponseEntity.ok(newsService.review(id, userId));
    }

    @GetMapping(value = "/revised")
    @ApiOperation(value = "List revised news", notes = "List all revised news")
    @ApiResponses(value = @ApiResponse(code = 200, message = "News found"))
    public ResponseEntity<List<News>> revisedNews() {
        return new ResponseEntity<>(newsService.revisedNews(), HttpStatus.OK);
    }

}
