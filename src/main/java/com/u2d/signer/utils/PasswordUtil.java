package com.u2d.signer.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    public static String encoder(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passEncod = encoder("415782@U2d");
        System.out.println(passEncod);
        boolean match = passwordEncoder.matches("415782@Dv", passEncod);
        System.out.println(match);

    }
}