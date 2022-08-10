package finance.simply.asset.recommender.model;

import javax.persistence.*;

@Entity
@Table(name = "asset")
public class Asset {

  public enum Type {
    AGRICULTURE,
    TRANSPORT,
    CONSTRUCTION,
    WASTE
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "cost")
  private double cost;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private Type type;

  @ManyToOne
  @JoinColumn(name = "deal_id")
  private Deal deal;

  public Asset() {
  }

  public Asset(Integer id, String name, double cost, Type type, Deal deal) {
    this.id = id;
    this.name = name;
    this.cost = cost;
    this.type = type;
    this.deal = deal;
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

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }

}
