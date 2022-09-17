package br.com.bankdata;

import br.com.bankdata.entities.brasilapi.Bank;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(input);
        String URI = properties.getProperty("brasilapi.uri");

        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        String[] menu = {"S", "A", "X"};

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(URI)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Bank> banks = gson.fromJson(response.body(), new TypeToken<List<Bank>>(){}.getType());

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Press option: [S] Search - [A] List All - [X] Exit - ");
            String option = sc.next();

            if(option.toUpperCase().equals(menu[0])){
                System.out.print("Bank code: ");
                String bankCode = sc.next();
                for (Bank bank : banks) {
                    if(bank.getCode() != null && bank.getCode().equals(bankCode)){
                        System.out.print("Bank name: ");
                        System.out.println(bank.getFullName());
                        break;
                    }
                }
            }

            if(option.toUpperCase().equals(menu[1])){
                for (Bank bank : banks) {
                    System.out.println(bank.getCode() + " - " + bank.getFullName());
                }
            }

            if(option.toUpperCase().equals(menu[2])){
                break;
            }

            if(!Arrays.asList(menu).contains(option.toUpperCase())){
                System.out.println("Select a option from the menu.");
            }

            System.out.println();
        }
    }
}