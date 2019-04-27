package me.filipebezerra.cms.domain.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.filipebezerra.cms.domain.models.Category;
import me.filipebezerra.cms.domain.vo.CategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@Api(tags = "category", description = "Category API")
public class CategoryResource {

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find category", notes = "Find the category by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category found"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Category> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(new Category());
    }

    @GetMapping
    @ApiOperation(value = "List categories", notes = "List all categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categories found"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(Arrays.asList(new Category(), new Category()));
    }

    @PostMapping
    @ApiOperation(value = "Create category", notes = "It permits to create a new category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category created successfully"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<Category> newCategory(CategoryRequest category) {
        return new ResponseEntity<>(new Category(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove category", notes = "It permits to remove a category")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Category removed successfully"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public void removeCategory(@PathVariable("id") String id) {
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update category", notes = "It permits to update a category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Category not found")
    })
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, CategoryRequest category) {
        return new ResponseEntity<>(new Category(), HttpStatus.OK);
    }

}
