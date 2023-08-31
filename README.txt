Para construir esta app precisei de um backend Java Spring Boot e frontend em React

Existe uma base de dados Postgres onde existe uma tabela City que contem o nome e cordenadas da cidade
Quando o user adiciona uma nova cidade:
    - Verifico se a cidade ja existe na base de dados
    - Faço uma api call para o openweathermap com a cidade e é me retornado uma lista de cidades(com as respetivas cordenadas)
        relacionadas com a pesquisa, onde seleciono a primeira cidade da lista como resposta. Caso a api retorne uma lista vazia
        entao a cidade nao existe

Para listar as cidades simplesmente retorno a tabela inteira da base de dados
Para eliminar uma cidade recebo um DELETE request com o id da cidade a eliminar

Para ver a metereologia de cada cidade, recebo um POST request com o id da cidade, depois:
    - Faço uma api call para openweathermap onde recebo um Objecto Json com detalhes da metereologia
    para os proximos 5 dias com um intervalo de tempo de 3h.
    - Depois para cada dia calculo:
        - Temperatura Minima
        - Temperatura Maxmia
        - Probabilidade de Percepitacao (Media)
        - Descricao do tempo (Descricao mais frequente durante o dia)
    - Retorno um lista de objectos em que o Objecto é composto por esses dados calculados mais a data do dia.

A implementção do backend segue o padrao MVC. Os controllers apenas sao responsaveis por receber os requests e darem a resposta.
Para obter a resposta o controller chama o service que toma as ações necessarias, podendo aceder aos models para acessar a base de dados,
e retorna a resposta ao controller.

Usei ainda o docker, como tal para correr a app apenas e necessario ter o docker instalado e correr:
 - docker-compose up --build