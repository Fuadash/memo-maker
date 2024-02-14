package main.classes;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

//TODO: way way better error handling once this is all set up
public class GPTSummary {
    private static final Properties properties = new Properties();

    public static String getSummary() {
        //TODO: Make the prompt actually the content of the memos, before that I guess just make the tech for printing whole memo files
        String prompt = "Print a summary of the happenings of the world in 1942";

        HttpURLConnection connection = null;

        // Load in the .env file which the user has hopefully set up
        try (InputStream env = GPTSummary.class.getClassLoader().getResourceAsStream("resources/.env")) {
            properties.load(env);

            //TODO: I guess some settings thing that lets you choose what language model you want to use
            String apiKey = properties.getProperty("GPT3.5_API_KEY");
            String url = "https://api.openai.com/v1/chat/completions";
            String model = "gpt-3.5-turbo";

            //TODO: Make this not all one big try catch block maybe
            try {
                // Request props
                URL obj = new URL(url);
                connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + apiKey);

                // Request body
                String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";

                // Make the request
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(body);
                writer.flush();
                writer.close();

                // Process the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return response.toString(); // TODO: Format the response in a different method before returning it
            } catch (IOException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("Bad things happened");
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("API Key probably wasn't set up, read the readme (assuming I updated it)");
            return null;
        }
    }
}
