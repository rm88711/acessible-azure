# GS - 2TDSJ Cloud Computing & DevOps Tools

## Integrantes
 - Gabriel Sun Gonçalo da Silva - RM: 88316
 - Kleber Albert de Sousa Monteiro - RM: 88711
 - Renato Miranda Esmail - RM: 86701


## EMPACOTAR

```

./mvnw package

```

## CRIAR IMAGEM NO DOCKER: precisar esta logado no docker

```

docker build -t acessible -f Dockerfile .

```

## Executar o docker :

```

docker container run -it -d -p 8080:8080 --name acessible-01 acessible

```
Caso queira ver o WebApp ou Api elas estão disponível para teste no heroku até o dia 27 de Novembro :
API : 
```

https://acessible.herokuapp.com/localacessibilidade

```

WebApp:

```

https://acessible.herokuapp.com/localacessilidade

```


