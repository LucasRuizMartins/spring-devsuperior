package com.ruizlucas.desafioinjecaodependencia;

import com.ruizlucas.desafioinjecaodependencia.entities.Order;
import com.ruizlucas.desafioinjecaodependencia.services.OrderService;
import com.ruizlucas.desafioinjecaodependencia.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DesafioInjecaoDependenciaApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ShippingService shippingService;


	public static void main(String[] args) {
		SpringApplication.run(DesafioInjecaoDependenciaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("DIGITE CODIGO");
		int code = sc.nextInt();
		System.out.println("DIGITE SALARIO BASICO");
		double basic = sc.nextDouble();
		System.out.println("DIGITE DESCONTO");
		double discount = sc.nextDouble();

		Order order = new Order(code, basic, discount);
		System.out.println("Pedido código " + order.getCode());
		System.out.printf("Valor total: R$ %.2f" , orderService.total(order));

	}
}
