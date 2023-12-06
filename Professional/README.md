
## Inversão de Controle. 

*(IOC) A inversão de Controle  é uma estratégia que consiste em ao em vez de usar um componente para controlar o fluxo de execução,  usar um framework, container para gerenciar o ciclo de vida e as dependências dos objetos. Isso reduz o acoplamento entre os módulos do sistema, facilitando a substituição de implementações e a manutenção do código.*.

***Módulos de alto nível** não devem depender de **módulos de baixo nível.***
*devem depender de **abstrações;***  
*Abstrações não devem depender de detalhes.* 
*Detalhes devem depender de abstrações. **(MARTIN 1996)**"*



Antes vamos a definições: 
-   **Módulos de alto nível:**  são os *módulos* que contém as regras de negócio 
-   **Módulos de baixo nível:** são os *módulos* que contém implementação de infraestrutura.
-   **Detalhes/Abstração:**  são os artefatos que não deveriam fazem parte da arquitetura de forma acoplada, mas que, no entanto, são necessários para um sistema funcionar. O acesso ao banco de dados, por exemplo, é um detalhe; assim como o gerenciamento de arquivos.

---

### **Injeção de Dependência**

A Injeção de Dependência é um padrão de design no qual as dependências de um objeto são fornecidas por meio de um mecanismo externo, em vez de serem criadas internamente. Em outras palavras, um componente não é responsável por criar suas próprias dependências, mas sim recebe essas dependências de uma fonte externa. Isso promove a desacoplagem e a reutilização de código, tornando as classes mais flexíveis e fáceis de testar.
Para decidir qual dependencia você irá instanciar em uma classe nós utilizamos o principio de injeção de dependencia 
Uma das formas de instanciar essa injeção de dependencia é por meio de um construtor, conforme o código abaixo: 

----
 
``` java
public Class Main {
(...)
	Employee employee = new Employee (name, grossSalary); 
	TaxService taxService = new TaxService(); 
	PensionService pensionService = new PensionService; 
	SalaryService salaryService = new SalaryService(taxService,pensionService );
(...)
}
``` 



``` java
	public Class SalaryService {
		private TaxService taxService; 
		private PensionService pensionService;

	
	public SalaryService (TaxService taxServic, PensionService pensionService){
		this.taxServic = taxServic;
		this.pensionService = pensionService;
}


		public netSalary(Employee emp) {
			return emp.getGrossSalary() 
			- taxService.tax(emp.getGrossSalary()) 
			- pensionService.discount(emp.getGrossSalary());
	} 
}

``` 

---
### fontes :

[Artigo - Medium.com](https://medium.com/contexto-delimitado/o-princ%C3%ADpio-da-invers%C3%A3o-de-depend%C3%AAncia-d52987634fa9)
[Artigo - Dio.me](dio.me/articles/injecao-de-dependencia-e-inversao-de-controle-fundamentos-e-implementacoes-em-java-e-c)

---