package net.maritimeconnectivity.mrnservice;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class MRNValidationController  {
    @Autowired
    private MRNValidationService mrnValidationService;

    @GetMapping(path="/validate", produces = "application/json")
    public Result validateMRN(@RequestParam(value = "mrn") String mrn)
    {
        return mrnValidationService.getResult(mrn, "");
    }

    @PostMapping(path="/validate", consumes = "application/json", produces = "application/json")
    public Result validateMRNWithScheme(@RequestBody Map<String, String> payload) throws Exception
    {
        return mrnValidationService.getResult(payload.get("mrn"), payload.get("regex"));
    }

    /*
    @PostMapping(path="/generate", consumes = "application/json", produces = "application/json")
    public Result generateMRNWithScheme(@RequestBody Map<String, String> payload) throws Exception
    {
        return mrnValidationService.generate(payload);
    }

     */
}
