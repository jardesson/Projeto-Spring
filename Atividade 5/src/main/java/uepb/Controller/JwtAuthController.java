package uepb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import uepb.DTO.JwtResponseDTO;
import uepb.DTO.UserExampleDTO;
import uepb.Security.JwtTokenUtil;
import uepb.Service.JwtUserDetailsService;

@RestController
@CrossOrigin
@Api(value = "Autenticacao")
public class JwtAuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtService;

    @ApiOperation(value = "Autentica um usuario")
    @PostMapping("/api/autenticar")
    @ResponseBody
    public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@RequestBody UserExampleDTO authenticationRequest) throws Exception {        
        final UserDetails userDetails = jwtService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}