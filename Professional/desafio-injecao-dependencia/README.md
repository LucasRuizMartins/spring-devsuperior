
# desafio DevSuperior - SpringBoot Professional 
**DESAFIO: Componentes e injeção de dependência** 

O desafio consiste em criar um sistema para calcular o valor total de um pedido, considerando uma porcentagem
de desconto e o frete. O cálculo do valor total do pedido consiste em aplicar o desconto ao valor
básico do pedido, e adicionar o valor do frete. 

*A regra para cálculo do frete é a seguinte:*

Valor básico do pedido (sem desconto)
* Abaixo de R$ 100.00 
		**Frete : R$ 20.00**
* De R$ 100.00 até R$ 200.00 
* **Frete: R$ 12.00** 
* R$ 200.00 ou mais 
* **Frete: Grátis**
* 
A solução deverá seguir as seguintes especificações:
Um pedido deve ser representado por um objeto **Order:**
E a lógica do cálculo do valor total do pedido deve ser implementada por **componentes** (serviços), cada
um com sua responsabilidade conforme **OrderService e ShippingService:**


### Order
```Java
public class Order {
 private int code;
 private double basic; 
 private double discount
}
```
### OrderService & ShippingService
```Java
public class OrderService {
 public Double total (order Order) {
}

public class ShippingService{
 public Double shipment(order Order) {
}

```

Serviço OrderService: responsável por operações referentes a pedidos.
Serviço ShippingService: responsável por operações referentes a frete.

A solução deverá ser implementada em Java com Spring Boot. A saída deverá ser mostrada no log
do terminal da aplicação. (implements CommandLineRunner).
Cada serviço deve ser implementado como um componente registrado com @Service.

---
Plug-in do Maven para evitar erro no pom.xml:

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>3.1.0</version> <!--$NO-MVN-MAN-VER$ -->
</plugin>