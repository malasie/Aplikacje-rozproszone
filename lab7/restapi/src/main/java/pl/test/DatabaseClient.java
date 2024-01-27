package pl.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DatabaseClient {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/users")
    public String getUsers() throws JsonProcessingException {
        List users = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));//mapowanie pobranych danych na klasę:
        return objectMapper.writeValueAsString(users);
    }

    @GetMapping("/users/{username}")
    public String getUser(@PathVariable("username") String username) throws JsonProcessingException {
        List users = jdbcTemplate.query("SELECT * FROM users WHERE username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username), new BeanPropertyRowMapper<>(User.class));

        return objectMapper.writeValueAsString(users.get(0));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) {
        String sql = "INSERT into USERS(username, age, name) VALUES(:username, :age, :name)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("age", user.getAge())
                .addValue("name", user.getName());

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        String sql = "DELETE FROM users where username = :username";
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("username", username);

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/post")
    public String getPosts() throws JsonProcessingException {
        List posts = jdbcTemplate.query("SELECT * FROM post", new BeanPropertyRowMapper<>(Post.class));//mapowanie pobranych danych na klasę:
        return objectMapper.writeValueAsString(posts);
    }

    @GetMapping("/post/{username}")
    public String getPost(@PathVariable("username") String username) throws JsonProcessingException {
        List posts = jdbcTemplate.query("SELECT * FROM post WHERE username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username), new BeanPropertyRowMapper<>(Post.class));

        return objectMapper.writeValueAsString(posts);
    }

    @GetMapping("/post/id={id}")
    public String getPost(@PathVariable("id") int id) throws JsonProcessingException {
        List posts = jdbcTemplate.query("SELECT * FROM post WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("id", id), new BeanPropertyRowMapper<>(Post.class));

        return objectMapper.writeValueAsString(posts.get(0));
    }

    @PostMapping("/post")
    public ResponseEntity addPost(@RequestBody Post post) {
        String sql = "INSERT into POST(username, content) VALUES(:username, :content)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", post.getUsername())
                .addValue("content", post.getContent());

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity deletePost(@PathVariable("id") int id) {
        String sql = "DELETE FROM POST where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
