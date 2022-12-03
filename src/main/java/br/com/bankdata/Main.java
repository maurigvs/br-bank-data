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
import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Bank> banks = getBankList();

        String[] menu = {"S", "A", "X"};
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

    private static List<Bank> getBankList() {

        String propertiesFile = "application.properties";
        String apiProperty = "brasilapi.uri";

        List<Bank> bankList = new ArrayList<>();
        Gson gson = new Gson();
        HttpResponse<String> response = callAPI(getURI(propertiesFile, apiProperty));
        bankList.addAll(gson.fromJson(response.body(), new TypeToken<List<Bank>>(){}.getType()));
        return bankList;
    }

    private static HttpResponse<String> callAPI(URI uri) {

        System.out.println(uri.getPath());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error calling " + uri.getPath() + ": " + e.getMessage());
        }

        return response;
    }

    private static URI getURI(String propertiesFile, String apiProperty) {

        URI URI = null;
        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream(propertiesFile);
            Properties properties = new Properties();

            properties.load(input);
            String uri = properties.getProperty(apiProperty);
            URI = new URI(uri);

        } catch (IOException e) {
            System.out.println("Error getting properties file " + propertiesFile + ": " + e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Error getting URI from " + apiProperty + ": " + e.getMessage());
        }
        return URI;
    }
}