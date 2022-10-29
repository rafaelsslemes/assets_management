package com.spexcode.asset_management.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get() {
        return "Get!";
    }

    @PostMapping
    public String post() {
        return "Post!";
    }

    @PutMapping
    public String put() {
        return "Put!";
    }

    @DeleteMapping
    public String delete() {
        return "Delete";
        
    }

    @GetMapping("/login")
    public String login(@RequestParam("login") String login,@RequestParam("senha") String senha) {
        return "Login: " + login + "  Senha: " +senha;

        //Leitura na URL http://localhost:8080/login?login=lemes&senha=123456
    }

    @PostMapping("/loginPost")
    public String loginPost(@RequestParam("login") String login,@RequestParam("senha") String senha) {
        return "Login: " + login + "  Senha: " +senha;

        //Não disponibiliza Leitura na URL, somente pelo corpo da requisição
    }

    @GetMapping("/getItem/{id}")
    public String getId(@PathVariable("id") String id) {
        return "Item de id = " + id;

        //Leitura na URL http://localhost:8080/login?login=lemes&senha=123456
    }
    
}
