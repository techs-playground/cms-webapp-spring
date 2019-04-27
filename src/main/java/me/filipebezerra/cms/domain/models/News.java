package me.filipebezerra.cms.domain.models;

import lombok.Data;

import java.util.Set;

@Data
public class News {

    String id;

    String title;

    String content;

    User author;

    Set<User> mandatoryReviewers;

    Set<Review> reviewers;

    Set<Category> categories;

    Set<Tag> tags;

    public Review review(String userId, String status) {
        final Review review = new Review(userId, status);
        reviewers.add(review);
        return review;
    }

    public Boolean revised() {
        return mandatoryReviewers.stream().allMatch(reviewer -> reviewers.stream()
                .anyMatch(review -> reviewer.id.equals(review.userId) && "approved".equals(review.status)));
    }

}
