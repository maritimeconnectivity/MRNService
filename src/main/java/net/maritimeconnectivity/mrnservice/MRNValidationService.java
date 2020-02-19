package net.maritimeconnectivity.mrnservice;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MRNValidationService {
    String mrnScheme = "^[Uu][Rr][Nn]\\:[Mm][Rr][Nn]\\:([A-Za-z0-9]([A-Za-z0-9]|\\-){0,20}[A-Za-z0-9])\\:([A-Za-z0-9][-A-Za-z0-9]{0,20}[A-Za-z0-9])\\:((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/)*)((\\?\\+((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/|\\?)*))?(\\?\\=((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/|\\?)*))?)?(#(((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/|\\?)*))?$";

    public Result getResult(String mrn, String regex){
        if(regex.isEmpty())
            regex = mrnScheme;
        return new Result(mrn, regex);
    }

    public Result generate(Map<String, String> payload){
        String mrn = "";
        String regex = "";
        return new Result(mrn, regex);
    }
}
