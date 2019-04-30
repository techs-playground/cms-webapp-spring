package me.filipebezerra.cms.domain.models;

import lombok.Data;

import java.util.Set;

@Data
public class News {

    private String id;

    private String title;

    private String content;

    private User author;

    private Set<User> mandatoryReviewers;

    private Set<Review> reviewers;

    private Set<Category> categories;

    private Set<Tag> tags;

    public Review review(String userId, String status) {
        final Review review = new Review(userId, status);
        reviewers.add(review);
        return review;
    }

    public Boolean revised() {
        return mandatoryReviewers.stream().allMatch(reviewer -> reviewers.stream()
                .anyMatch(review -> reviewer.getId().equals(review.userId) && "approved".equals(review.status)));
    }

}
