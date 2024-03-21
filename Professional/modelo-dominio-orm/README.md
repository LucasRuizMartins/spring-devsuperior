  

## Modelo de domínio e ORM.

  

O mapeamento do modelo de Dominio e relacional é uma técnica utilizada para converter um modelo seguindo o padrão UML (*Ou objetos*) para o modelo de ER (*Entidade Relacionamento*) 

esse processo pode ser muito custoso, em modelos antigos boa parte do tempo do desenvolvedor era utilizado para fazer essa adaptação e mapeamento. Hoje em dia com frameworks como o spring esse trabalho é minimizado possibilitando ao programador dedicar seu tempo as regras especificas e não em configurações mecânicas. 

**Revisão Álgebra Relacional e SQL na DevSuperior**

[OPERAÇÕES BÁSICAS SQL.](https://www.youtube.com/watch?v=GHpE5xOxXXI)

[OO e SQL COM JAVA E JDBC](https://www.youtube.com/watch?v=xC_yKw3MYX4)


[ORM - JPA - HIBERNATE](https://www.youtube.com/watch?v=CAP1IPgeJkw)


### sobre o Spring

**Relacionamentos muitos-para-um**

	public class Order {
	...
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;

---

	public class User {
	...
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();



**Relacionamento um-para-um**

	public class Payment {
	...
	@OneToOne
	@MapsId
	private Order order;
	
---

	public class Order {
	...
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

**Relacionamento muitos-para-muitos**

	public class Product {
	...
	@ManyToMany
	@JoinTable(name = "tb_product_category",
	joinColumns = @JoinColumn(name = "product_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

---
	public class Category {
	...
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();

**Muitos-para-muitos com classe de associação**
*exemplo com classe*
	
	@Embeddable
	public class OrderItemPK {
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	...
---
	@Entity
	@Table(name = "tb_order_item")
	public class OrderItem {
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	private Double price;
	public OrderItem() {
	}
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
	id.setOrder(order);
	id.setProduct(product);
	this.quantity = quantity;
	this.price = price;
	}
	public Order getOrder() {
	return id.getOrder();
	}
	public void setOrder(Order order) {
	id.setOrder(order);
	}
	...

---
**aplicação generica**

	public class Order {
	...
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	...
	public Set<OrderItem> getItems() {
	return items;
	}
	public List<Product> getProducts() {
	return items.stream().map(x -> x.getProduct()).toList();
	}
---
	public class Product {
	...
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	...
	public Set<OrderItem> getItems() {
	return items;
	}
	public List<Order> getOrders() {
	return items.stream().map(x -> x.getOrder()).toList();
	}
--- 

# DESAFIO 

**ESPECIFICAÇÃO - Sistema EVENTO:**

Deseja-se construir um sistema para gerenciar as informações dos participantes das atividades de um
evento acadêmico. As atividades deste evento podem ser, por exemplo, palestras, cursos, oficinas
práticas, etc. Cada atividade que ocorre possui nome, descrição, preço, e pode ser dividida em vários
blocos de horários (por exemplo: um curso de HTML pode ocorrer em dois blocos, sendo necessário
armazenar o dia e os horários de início de fim do bloco daquele dia). Para cada participante, deseja-se
cadastrar seu nome e email.

![uml-1](/docs-estudos/UML-1.png)

![uml-2](/docs-estudos/UML-2.png)