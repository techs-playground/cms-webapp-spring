package me.filipebezerra.cms.domain.vo;

import lombok.Data;
import me.filipebezerra.cms.domain.models.Category;
import me.filipebezerra.cms.domain.models.Tag;

import java.util.Set;

@Data
public class NewsRequest {

    String title;

    String content;

    Set<Category> categories;

    Set<Tag> tags;

}
