package uepb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import uepb.DTO.UserExampleDTO;
import uepb.Model.UserExample;
import uepb.Repository.UserExampleRepository;

@RestController
@CrossOrigin
@Api(value = "Usuarios")
public class UserExampleController {
    
    @Autowired
    private UserExampleRepository userRepository;

    @ApiOperation(value = "Cadastra um novo usuario")
    @PostMapping("/api/inscricao")
    @ResponseBody
    public ResponseEntity<UserExample> createUser(@RequestBody UserExampleDTO user) {
        try {
            return ResponseEntity.ok(
                userRepository.save(
                    new UserExample(user.getEmail(), user.getUsername(), user.getPassword())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}