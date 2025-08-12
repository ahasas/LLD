package org.example.URLShortener.UrlGenerator;

public class BaseCodeURLGenerator implements URLGenerator {

        private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private int counter = 10000; // for simplicity

        public synchronized String generate(String longUrl) {
            int current = counter++;

            StringBuilder sb = new StringBuilder();
            while (current > 0) {
                sb.append(CHAR_SET.charAt(current % 62));
                current /= 62;
            }
            return sb.reverse().toString();
        }
    }


