package finance.simply.asset.recommender.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private double affordability;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "customer_deal",
             joinColumns = {@JoinColumn(name = "customer_id")},
             inverseJoinColumns = {@JoinColumn(name = "deal_id")})
  private List<Deal> deals;

  public Customer() {
  }

  public Customer(Integer id, String name, double affordability, List<Deal> deals) {
    this.id = id;
    this.name = name;
    this.affordability = affordability;
    this.deals = deals;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getAffordability() {
    return affordability;
  }

  public void setAffordability(double affordability) {
    this.affordability = affordability;
  }

  public List<Deal> getDeals() {
    return deals;
  }

  public void setDeals(List<Deal> deals) {
    this.deals = deals;
  }

}
