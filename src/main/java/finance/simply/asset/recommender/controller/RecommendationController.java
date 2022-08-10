package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.model.Customer;
import finance.simply.asset.recommender.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

  private final RecommendationService recommendationService;

  @Autowired
  public RecommendationController(RecommendationService recommendationService) {
    this.recommendationService = recommendationService;
  }

  @GetMapping
  public ResponseEntity<Map<Integer, List<Asset>>> getRecommendations(
          @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    Map<Customer, List<Asset>> recommendations = recommendationService.getRecommendations(date);
    return ResponseEntity.ok(recommendations.entrySet()
                                            .stream()
                                            .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey().getId(),
                                                                                        entry.getValue()))
                                            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey,
                                                                      AbstractMap.SimpleEntry::getValue)));
  }
}