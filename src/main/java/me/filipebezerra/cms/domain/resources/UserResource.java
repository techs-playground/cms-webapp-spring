package me.filipebezerra.cms.domain.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.filipebezerra.cms.domain.models.User;
import me.filipebezerra.cms.domain.service.UserService;
import me.filipebezerra.cms.domain.vo.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Api(tags = "user", description = "User API")
public class UserResource {

    private final UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find user", notes = "Find user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @GetMapping
    @ApiOperation(value = "List users", notes = "List all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    @ApiOperation(value = "Create user", notes = "It permits create a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<User> newUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove user", notes = "It permits to remove a user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User removed successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public void removeUser(@PathVariable("id") String id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user", notes = "It permits to update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.update(id, userRequest), HttpStatus.OK);
    }

}
