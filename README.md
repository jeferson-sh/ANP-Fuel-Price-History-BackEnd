# API de consulta de preços disponibilizada por meio de arquivos .csv pela ANP

API desenvolvida com Spring Boot para consumir e gerenciar dados sobre preços de combustíveis disponibilizados pela Agência Nacional do Petróleo através deste [link](http://www.anp.gov.br/conteudo-do-menu-superior/31-dados-abertos/conteudo-do-menu-superior/31-dados-abertos/5542-serie-historica-de-precos-de-combustiveis): .

Aqui você vai encontrar uma solução de como utilizar Generics do java e ver uma solução de um Generic Controller para realizar o Crud de dados da API. 


# Endpoints

Todos os endpoints podem ser encontrados nas urls abaixo:

 1. Para acessar via heroku: [https://anp-project.herokuapp.com/swagger-ui/](https://anp-project.herokuapp.com/swagger-ui/)
 2. Para acessar via localhost: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)
 
## Como acessar os Endpoints?

A API foi construída utilizando Spring Security como estrutura de autenticação e controle de acesso aos recursos disponibilizados. Grande parte dos endpoints necessitam de um token de autorização que é disponibilizado no path /login da api através do header authorization obtido na resposta dessa requisição.
Existe tbm um usuário com nível de administrador já cadastrado. Você pode seguramente realizar o login com os seus dados para obter o token de acesso aos endpoints:

    {
		"username":  "ADMIN@EMAIL",
		"password":  "PASSWORD"
	}
De toda forma, caso queira cadastrar um usuário antes de realizar o login, acesse o /users/sign-up da api. Abaixo deixo um exemplo de cadastro:

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
Perceba que existe dois tipos de autoridades a "ROLE_ADMIN" e a "ROLE_USER" ambas já estão cadastradas na API. 

## Como importar dados para a api?
Siga os passos abaixo:
 1. acesse o link de download dos arquivos disponibilizados pela ANP através do link: 
 2. [http://www.anp.gov.br/conteudo-do-menu-superior/31-dados-abertos/conteudo-do-menu-superior/31-dados-abertos/5542-serie-historica-de-precos-de-combustiveis](http://www.anp.gov.br/conteudo-do-menu-superior/31-dados-abertos/conteudo-do-menu-superior/31-dados-abertos/5542-serie-historica-de-precos-de-combustiveis)
 3. Realize o download dos arquivos que vc quer importar para o seu banco de dados da seção **Combustíveis automotivos**;
 4. Acesse o endpoint da api: /fuelPriceHistory/import;
 5. Importe o arquivo que você realizou o download;
 6. Insira o token de autorização  da api;
 7. Realize a request; :)

**Observações importantes!**

 1. A request de importação de dados **só pode ser feita em ambiente Local**. O plano free do Heroku não possui hardware capaz de gerenciar cerca de 50 mil linhas de dados contidos nos arquivos;
 2. Se estiver rodando em ambiente local lembre-se que o banco utilizados nesse projeto é o **H2 Database**, pois este é um projeto apenas de aprendizado das tecnologias utilizadas. Por este motivo, **se você reiniciar o servidor irá perder dos os dados salvos anteriormente no banco de dados;**

Continue estudando e me manda aí os seus pull requests! 
Att.