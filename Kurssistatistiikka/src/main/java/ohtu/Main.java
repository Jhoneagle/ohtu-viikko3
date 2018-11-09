package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String urlSub = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String urlInfo = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String urlOhtu = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String urlRails = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        
        String subText = Request.Get(urlSub).execute().returnContent().asString();
        String infoText = Request.Get(urlInfo).execute().returnContent().asString();

        System.out.println("opiskelijanumero " + studentNr);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(subText, Submission[].class);
        Course[] infos = mapper.fromJson(infoText, Course[].class);
        
        String statsOhtu = Request.Get(urlOhtu).execute().returnContent().asString();
        String statsRails = Request.Get(urlRails).execute().returnContent().asString();
        
        JsonParser parser = new JsonParser();
        JsonObject parsittuOhtu = parser.parse(statsOhtu).getAsJsonObject();
        JsonObject parsittuRails = parser.parse(statsRails).getAsJsonObject();
        
        int sumExercises = 0;
        int sumHours = 0;
        int totalExercises = 0;
        
        System.out.println();
        
        Course rails = infos[0];
        System.out.println(rails);
        System.out.println();
        
        for (Submission submission : subs) {
            if (submission.getCourse().contains("rails")) {
                String exercise = submission.getExercises().size() + "/" + rails.getExercises().get(submission.getWeek());
                System.out.println("viikko " + submission.getWeek() + ":");
                System.out.println(" tehtyjä tehtäviä " + exercise + submission);
                
                sumExercises += submission.getExercises().size();
                sumHours += submission.getHours();
            }
        }
        
        totalExercises = rails.getExercises().stream().map((exercises) -> exercises).reduce(totalExercises, Integer::sum);
        
        System.out.println();
        System.out.println("yhteensä: " + sumExercises + "/" + totalExercises + " tehtävää " + sumHours + " tuntia");
        sumExercises = 0;
        sumHours = 0;
        totalExercises = 0;
        
        System.out.println();
        
        Iterator<String> keys = parsittuRails.keySet().iterator();
        int palautukset = 0;
        int tehtavat = 0;
        int aika = 0;
        
        while (keys.hasNext()) {
            JsonObject object = parsittuRails.get(keys.next()).getAsJsonObject();
            
            palautukset += object.get("students").getAsInt();
            tehtavat += object.get("exercise_total").getAsInt();
            aika += object.get("hour_total").getAsInt();
        }
        
        System.out.println("kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä " + tehtavat
                + " kpl, aikaa käytetty yhteensä " + aika + " tuntia");
        
        System.out.println();
        Course ohtu = infos[1];
        System.out.println(ohtu);
        System.out.println();
        
        for (Submission submission : subs) {
            if (submission.getCourse().contains(ohtu.getName())) {
                String exercise = submission.getExercises().size() + "/" + ohtu.getExercises().get(submission.getWeek());
                System.out.println("viikko " + submission.getWeek() + ":");
                System.out.println(" tehtyjä tehtäviä " + exercise + submission);
                
                sumExercises += submission.getExercises().size();
                sumHours += submission.getHours();
            }
        }
        
        totalExercises = ohtu.getExercises().stream().map((exercises) -> exercises).reduce(totalExercises, Integer::sum);
        
        System.out.println();
        System.out.println("yhteensä: " + sumExercises + "/" + totalExercises + " tehtävää " + sumHours + " tuntia");
        System.out.println();
        
        keys = parsittuOhtu.keySet().iterator();
        palautukset = 0;
        tehtavat = 0;
        aika = 0;
        
        while (keys.hasNext()) {
            JsonObject object = parsittuOhtu.get(keys.next()).getAsJsonObject();
            
            palautukset += object.get("students").getAsInt();
            tehtavat += object.get("exercise_total").getAsInt();
            aika += object.get("hour_total").getAsInt();
        }
        
        System.out.println("kurssilla yhteensä " + palautukset + " palautusta, palautettuja tehtäviä " + tehtavat
                + " kpl, aikaa käytetty yhteensä " + aika + " tuntia");
    }
}