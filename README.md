# ANP-Fuel-Price-History-BackEnd

API desenvolvida com Spring Boot para consumir e gerenciar dados sobre preços de combustíveis disponibilizados pela Agência Nacional do Petróleo.

Aqui você vai encontrar uma solução de como utilizar Generics do java e ver uma solução de um Generic Controller para realizar o Crud de dados da API. 


# Endpoints

Todos os endpoints podem ser encontrados nas urls abaixo:

 1. Para acessar via heroku: [https://anp-project.herokuapp.com/swagger-ui/](https://anp-project.herokuapp.com/swagger-ui/)
 2. Para acessar via localhost: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
 
## Como acessar os Endpoints?

A API foi construída utilizando Spring Security como estrutura de autenticação e controle de acesso aos recursos disponibilizados. Grande parte dos endpoints necessitam de um token de autorização que é disponibilizado no path /login da API através do header authorization obtido na resposta dessa requisição.
Existe tbm um usuário com nível de administrador já cadastrado. Você pode seguramente realizar o login com os seus dados para obter o token de acesso aos endpoints:

    {
		"username":  "ADMIN@EMAIL",
		"password":  "PASSWORD"
	}
De toda forma, caso queira cadastrar um usuário antes de realizar o login, acesse o /users/sign-up da API. Abaixo deixo um exemplo de cadastro:

    {
		"name":  "name",
		"username":  "email@email.com",
		"password":  "password",
		"authorities":  [
			{
				"id":  1,
				"authority":  "ROLE_ADMIN"
			},
			{
				"id":  2,
				"authority":  "ROLE_USER"
			}
		]
	}
Perceba que existe dois tipos de authoridades a "ROLE_ADMIN" e a "ROLE_USER" ambas já estão cadastradas na API. 

Manda aí os seus pull requests! 
Att.