## API - Application Programming Interface

é o conjunto de funcionalidades que são expostas por uma aplicação/Módulo para ser acessada por outra aplicação/Modulo. 

**API WEB** é uma API disponibilizada via WEB. Suas funcionalidades são acessasdas por meio de endpoints (endereços) web 
Por exemplo: `https:facebook.com/user/100`

**[API REST](https://www.redhat.com/pt-br/topics/api/what-is-a-rest-api)** ➞ O Padrão REST é referente a algumas regras 
- Cliente servidor HTTP/HTTPS
- Comunicação Stateless (Sem armazenamento de status, ou seja cada uma é isolada)
- Pode armazenar Cache 
- Interface uniforme, formatado padronizado
- Possui um sistema de camadas
- Código sobre demanda (estudar posteriormente) 


Padrões:
		**GET:** host:port/products    ➞  Obter Lista de Produtos 
		**GET:** host:port/products?page=3 ➞  Obter a lista de produtos na pagina 3
		**GET:** host:port/products/1 ➞ Produto ID 1
		**GET:** host:port/products/1/categories  ➞ Categoria do Produto ID 1

"?" ➞  Parametro de consulta (Route Params ou path variables opcional)
"/" ➞ Parametro Obrigatório (query params)

### [Verbos HTTP Mais utilizados](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Methods)

- GET - obter recurso
- POST - criar novo recurso
- PUT - salvar recurso de forma idempotente
- DELETE - deletar recurso

### [Códigos de resposta HTTP](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status)
- Respostas de informação (100-199) 
-  Respostas de sucesso (200-299) 
-  Redirecionamentos (300-399) 
-  Erros do cliente (400-499) 
-  Erros do servidor (500-599)

erros mais comuns

- 400 - Bad Request (erro Genérico)
- 401 - Unauthorized (falha naautenticação)
- 403 - Forbidden (acesso negado)
- 404 - Not Found
- 409 - Conflict 
- 415 - Unsupported Media Type 
- 422 - Unprocessable entity  



## Padrão Camadas

é um padrão de arquitetura para organização do código visando facilitar a manutenção.

- consistem em organizar os **componentes (services)** do sistema em partes denominadas **camadas**
- Cada camada possui uma responsabilidade especifica
- Componentes de uma camada só podem depender de componentes da **mesma camada**, ou de uma camada mais abaixo

![Padrão de camadas](https://raw.githubusercontent.com/tarcnux/projeto-sds3/main/images/camadas.png)

### Responsabilidades:

- **CONTROLER** ➞ responder interações do usuario
	- No caso de uma **API REST** essas "interações" são as requisições do front-end/ Client
- **SERVICE**  ➞ realizar operações de negócio
	- Um método chamado service deve ter um significado relacionado ao negócio, podendo executar varias operações. Exemplo: registrarPedido
- **REPOSITORY**  ➞ realizar operações "individuais" de acesso ao banco de dados. 




# Controller

- **@Controller**: Indica que a classe é um controlador no padrão MVC (Model-View-Controller) do Spring, ou seja, ela gerencia o fluxo de solicitações HTTP.
    
- **@ResponseBody:** Indica que o valor retornado por um método de um controlador deve ser serializado diretamente para o corpo da resposta HTTP, em vez de ser interpretado como o nome de uma visualização a ser renderizada.
    
- **@RestController:** Uma combinação dessas duas funcionalidades, geralmente utilizada para APIs RESTful, onde você geralmente retorna objetos serializados como JSON ou XML para o cliente.


---
**Exemplo: Básico**

	@RestController  
	@RequestMapping(value = "/products")  
	public class ProductController {  

	@GetMapping  
		public String teste(){
		return "Olá Mundo";}
	}


**exemplo com Query Params (@PathVariable Long id)**

	@GetMapping(value = "/{id}")  
		public ProductDTO findById(@PathVariable Long id){  
		return service.findById(id);  
	}



### Tratamento de erros 

Criar um customError como o abaixo (é o corpo do error) 	
	
	private Instant timestamp;  
	private Integer status;  
	private String error;  
	private String path;



Criar um Handler a nivel de controlador como o exemplo a baixo  
	
	@ControllerAdvice  
	public class ControllerExceptionHandler {  
	@ExceptionHandler(ResourceNotFundException.class)  
		public ResponseEntity<CustomError> customName(ResourceNotFundException e, HttpServletRequest request) {  
			HttpStatus status = HttpStatus.NOT_FOUND;  
			CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI() );  
			return ResponseEntity.status(status).body(err);  
		}  
	}

**delete** 
Para fazer o tratamento do delete primeiro precisa verificar se o id existe através do existsById
também é necessario tratar a falha de integridade referencial "DataIntegrityViolationException" para isso funcionar precisa-se usar o propagation.supports, para que se propague para o BDD. 

	@Transactional(propagation = Propagation.SUPPORTS)  
	public void delete(Long id) {  
		if (!repository.existsById(id)) {  
			throw new ResourceNotFundException("Recurso não encontrado");  
		}  
		try {  
			repository.deleteById(id);  
		}  
		catch (DataIntegrityViolationException e) {  
			throw new DatabaseException("Falha de integridade referencial");  
		}  
	}



# Repository

criar um repository no spring é através de uma interface JpaRepository<Product,Long>



	public interface ProductRepository extends JpaRepository<Product,Long> {  
	}


Retorna um tipo Otional 

	public String teste(){  
	Optional<Product> result = repository.findById(1L);  
		Product product = result.get();  
		return product.getName();  
	}

### DTO Data Transfer Object 

Um objeto simplificado não gerenciado por uma LIB de orm e usado para transferência de dados e pode ser alinhado com outros **DTO's**

**UTILIZAÇÃO ➞**  Projeção de dados, retornando apenas os dados que você quer utilizar. 
Ao restringir os dados é possível econimizar tráfego de dados e garantir a segurança, além de ter mais opções de representações dos dados



# Service

A Camada Service é a que irá fazer os acessos aos BD's e fazer a manipulação dos dados 



**Exemplo de Service findById**

	@Transactional(readOnly = true)  
	public ProductDTO findById(Long id){  
		Optional<Product> result = repository.findById(id);  
		Product product= result.get();  
		ProductDTO dto = new ProductDTO(product);  
		return dto;  
	} 

**Simplificado:** 
	
	@Transactional(readOnly = true)  
	public ProductDTO findById(Long id){  
		Product product= repository.findById(id).get();  
		return new ProductDTO(product);  
	}
--- 
### Outras referencias 

**Biblioteca que auxilia a Criar um DTO com muitas atributos** 
[MODEL MAPPER](https://www.youtube.com/watch?v=2Iqo7rzNm-o)


### --- Retornar Paginas : < Pageable pageable > ---

`http://localhost:8080/products?size=12&page=0&sort=name,desc`

**CONTROLLER**

	@GetMapping  
	public Page<ProductDTO> findById(Pageable pageable){  
		return service.findAll(pageable);  
	}

**SERVICE**

	@Transactional(readOnly = true)  
	public Page<ProductDTO> findAll(Pageable pageable){  
		Page<Product> products = repository.findAll(pageable);  
		return products.map(x -> new ProductDTO(x));  
	}
	
---

###  --- INSERT ---

O @RequestBody é utilizado para a aplicação receber um JSON como parametro 

	{
		"name":  "caneta azul",
		"description":  "azul caneta, caneta azul, tem até musica propria a caneta azul",
		"imgUrl":"https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
		"price":  1110.00
	}

**CONTROLLER** 

	@PostMapping  
	public ProductDTO insert(@RequestBody ProductDTO dto){  
		return service.insert(dto);  
	}

**SERVICE** 

	@Transactional  
	public ProductDTO insert(ProductDTO dto){   
		Product entity = new Product();  
		entity.setName(dto.getName());  
		entity.setDescription(dto.getDescription());  
		entity.setPrice(dto.getPrice());  
		entity.setImgUrl(dto.getImgUrl());  
		entity = repository.save(entity);  
		return new ProductDTO((entity));  
	}



---
### --- Customizando respostas ---

**---200--- SUCESS**

Para obter uma resposta HTTP deve ser utilizado o Objeto ResponseEntity no Controller

	@GetMapping(value = "/{id}")  
		public ResponseEntity<ProductDTO> findById(@PathVariable Long id){  
		ProductDTO dto = service.findById(id);  
		return ResponseEntity.ok(dto);  
	}

**Em caso de Listas**

	@GetMapping  
	public ResponseEntity<Page<ProductDTO>> findById(Pageable pageable){  
		Page<ProductDTO> dto = service.findAll(pageable);  
		return ResponseEntity.ok(dto);  
	}

**---201--- INSERT** 

para retornar o uri já na requisição do insert deve se usar o ServletUriComponentsBuilder

	@PostMapping  
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){  
		dto = service.insert(dto);  
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")  
				  .buildAndExpand(dto.getId()).toUri();  
		return ResponseEntity.created(uri).body(dto);  
	}
**204 --DELETE--**
caso nao tenha corpo, como no caso do delete o http é 204 nesse caso será o .noContent().build

	@DeleteMapping(value ="/{id}")  
	public ResponseEntity<Void> delete(@PathVariable Long id){  
		service.delete(id);  
		return ResponseEntity.noContent().build();  
	}


### Validações 


[Bean Validation](https://jakarta.ee/specifications/bean-validation/3.0/) 
[Constraints](https://jakarta.ee/specifications/bean-validation/3.0/apidocs/)
[Tutorial](https://javaee.github.io/tutorial/bean-validation.html)

Dependencias 

	<dependency>
		<groupId>jakarta.validation</groupId>
			<artifactId>jakarta.validation-api</artifactId>
		<version>3.0.2</version>
	</dependency>
	
	<dependency> 
		<groupId> 
			org.springframework.boot
		</groupId> 
		<artifactId>
			spring-boot-starter-validation
		</artifactId> 
	</dependency>

	
	<dependency>
		<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
		<version>8.0.0.CR2</version>
	</dependency>

As validações devem ser feitas no DTO uma vez que ele que é trafegado via web necessario implantar a anotation @Valid no Controller

Para passar os erros de forma dinamica é necessario criar uma classe de mensagem de erro e uma Classe de erro para ter uma lista dessas mensagens, no caso do projeto DSCOmerce é a Validation Error 

**FieldMessage** 

	 class FieldMessage {  
	  
		private String fieldName;  
		private String message;  
		  
		public FieldMessage(String fieldName, String message) {  
			this.fieldName = fieldName;  
			this.message = message;  
		}  
		  
		public String getFieldName() {  
			return fieldName;  
		}  
		  
		public String getMessage() {  
			return message;  
		}  
	}




**Validation Error**

	public class ValidationError extends CustomError{  
  
		private List<FieldMessage> errors = new ArrayList<>();  
		public ValidationError(Instant timestamp, Integer status, String error, String path) {  
			super(timestamp, status, error, path);  
			}  
		  
			public List<FieldMessage> getErrors() {  
				return errors;  
			}  
		  
			public void addError(String fieldName, String message) {  
				errors.add(new FieldMessage(fieldName,message));  
			}  
		}


**ExceptionHandler** 
	
		@ExceptionHandler( MethodArgumentNotValidException.class)  
		public ResponseEntity<CustomError> methodArgumentNotValid( MethodArgumentNotValidException e, HttpServletRequest request) {  
			HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;  
			ValidationError err = new ValidationError(Instant.now(), status.value(),"Dados inválidos", request.getRequestURI() );  
			
			for(FieldError f : e.getBindingResult().getFieldErrors()) {  
				err.addError(f.getField(), f.getDefaultMessage());  
			}  
			
			return ResponseEntity.status(status).body(err);  
		} 