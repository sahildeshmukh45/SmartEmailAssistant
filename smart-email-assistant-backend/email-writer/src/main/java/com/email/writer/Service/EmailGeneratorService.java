package com.email.writer.Service;


import com.email.writer.Model.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){
        // build the prompt
        String prompt = buildPrompt(emailRequest);


        // craft  a request check the craft example for reference
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                               Map.of("text",prompt)
                        })

                }
                );

        // do request and get the response
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Extract the Response and then return that extracted Response 
        return extractResponseContent(response);
    }

    //extracting a response
    private String extractResponseContent(String response) {
        try{
            ObjectMapper  mapper = new ObjectMapper(); // this is object mapper it can read, write the json data it can
                                                       // convert java java object to json data or json data to java object

            JsonNode rootNode   =mapper.readTree(response);  //readTree convert json data into tree like structure
                                                             //  represent the entire json tree, using rootNode which is
                                                             //  object type of JsonNode we can navigate through the tree
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }
        catch (Exception e){
            return "Error processing request: " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest){
        StringBuilder prompt = new StringBuilder();

        prompt.append
                ("Generate a professional email reply for the following email content. please don't generate a subject line  ");

        if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\n original mail: \n ").append(emailRequest.getEmailContent());
        return prompt.toString();
    }

}
