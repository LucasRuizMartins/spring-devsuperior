# DESAFIO: CRUD de clientes

O desafio consiste em criar um CRUD de spring boot utilizando o padrão **REST** 

- Busca paginada de recursos
- Busca de recurso por id
- Inserir novo recurso
- Atualizar recurso
- Deletar recurso

Um cliente possui nome, CPF, renda, data de nascimento, e quantidade de filhos. A especificação da
entidade Client é mostrada a seguir:

![Client](https://github.com/LucasRuizMartins/spring-devsuperior/blob/main/Professional/modelo-dominio-orm/docs-estudos/Client.png?raw=true)

**O Projeto precisará ter pelo menos 10 clientes** 

Os clientes são upados para o H2 pelo import.sql

	- INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Jose Saramago', '123.456.001-01', 3000.0,'2001-04-03', 8);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Joao Tolkien', '234.567.002-02', 2800.0,'1980-02-07', 2);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Alanis Morissed', '345.678.003-03', 3800.0,'1990-12-05', 1);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Lucas SkyWalker', '456.789.004-04', 2200.0,'1980-12-01', 3);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Joao Senao', '789.101.103-05', 1800.0,'1993-12-11', 2);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('irineu nem eu', '101.121.001-03', 1900.0,'1968-01-01', 2);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Vivian Marci Ana', '124.672.001-07', 5000.0,'2001-10-01', 2);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Gabriel Rosha', '211.517.001-09', 4800.0,'1995-09-02', 1);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Jose Loro Silva', '241.555.021-09', 2550.0,'1995-09-02', 2);  
	INSERT INTO tb_client(name, cpf, income, birth_date,children) VALUES ('Maria Ana Braga', '217.522.022-01', 3800.0,'1995-09-02', 0);



** O projeto deverá tratar as seguintes exceções** 

- Id não encontrado (para GET por id, PUT e DELETE), retornando código 404.
- Erro de validação, retornando código 422 e mensagens customizada para cada campo inválido. As
regras de validação são:
- Nome: não pode ser vazio
- Data de nascimento: não pode ser data futura

Para o tratamento dessas exceções foram utilizadas as seguintes classes: 

- CustomError 
	- Um DTO para trafegar os dados dos erros entre as classes
- FieldMessage (para guardar o campo que houve o erro e qual a mensagem de erro)
- ValidationError
	-  usado para guardar uma lista de FieldMessage, para caso retorne mais de um erro
-  ControllerExceptionHandler 
	- usado para lançar as exceções customizadas com a Anotation **@ControllerAdvice**

**Adicional:**
  Acrescentei também uma validação de CPF que não foi solicitada, mas defini que o CPF deveria ser unico no sistema, para essa validação primeiro foi necessário fazer a validação da existencia do CPF, no Repository foi criada a seguinte função 

	`Client findByCpf(String cpf);`
Caso ela retorne um valor nulo é permitido cadastrar o novo cliente, caso contrário será lançada uma exceção customizada. 


Retorno de erros

- 404 - Cliente não encontrado
- 422 - Dados inválidos
- 400 - CPF já cadastrado  