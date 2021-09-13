1 - Introdução e Definição da Arquitetura<br>

Partindo do princípio da ideia de janela inteligente integrada com um aplicativo mobile, fica evidente a necessidade de um arquitetura que contenha uma comunicação entre os sistemas heterogêneos que abrangem este projeto. Para tanto, usaremos a plataforma Node-red para atuar nesta parte de interligação dos sistemas (Arduino, Mobile, Banco de Dados) juntamente com a arquitetura REST por oferecer uma maior flexibilidade em relação ao SOAP no que diz respeito a regras e padrões de desenvolvimento.
Além disso, vale ressaltar que o REST tem como principal característica o statelessness, ou seja, o estado da aplicação não persiste entre sucessivos resquests e responses, consequentemente temos uma maior facilidade para chamar a API que irá trazer informações do clima para o aplicativo.  

1.2 - Imagem da arquiteruta:<br>
https://github.com/NextStepFIAP/Fenestra-API/blob/d3013efc7be08534b223aaf26543da4a680dc3dd/Fenestra/Arquitetura.png


2 - IMPLEMENTAÇÃO DO BACK-END<br>
Para o Back-End da nossa solução, iremos utilizar como linguagem de desenvolvimento o React-Native pois além de ter uma fácil comunicação com o Front-End de sistemas mobile podemos, por meio dele, disponibilizar nossa aplicação para diversas plataformas. No que diz respeito ao Banco de Dados, será utilizado o MySQL por ser uma opção gratuita e oferecer um excelente serviço na persistência de dados. Por fim, para integrar e permitir a comunicação entre todos os nossos sistemas e componentes (Arduino, Aplicativo e Banco de Dados) iremos utilizar o Node-Red como nosso servidor intermediário.


3 - Tabela de Endpoints<br>
API RESTFul que retorna JSON:<br>
[/user] - Endpoint de transações relacionadas ao usuário (GET - Todos os usuários do sistema e um usuário específico com base no ID; POST - Criar um usuário; DELETE - Remover um usuário com base no ID; PUT - Editar um usuário com base no ID)<br><br>
[/componente] - Endpoint de transações relacionadas ao componente (GET - Todos os componentes do sistema e um componente específico com base no ID; POST - Criar um componente; DELETE - Remover um componente com base no ID; PUT - Editar um componente com base no ID)<br><br>
[/log] - Endpoint de transações relacionadas ao log (GET - Todos os logs do sistema e um log em específico com base no ID; POST - Criar um log; DELETE - Remover um log com base no ID; PUT - Editar um log com base no ID)<br>


4 - PRINCIPAIS FUNCIONALIDADES DO BACK-END<br>
Dentre as principais funções que o back-end será responsável, podemos citar as que são acionadas pela aplicação Mobile, estas que abordam, efetivamente, as ações que o sistema executará no componente mecânico do Arduino, como por exemplo: Definir um horário para fechamento da(s) janela(s), fechar a(s) janela(s) automaticamente ao iniciar uma chuva e/ou por meio deste horário, exibir e gerar logs das ações do Arduino para o usuário (Ex: A janela foi fechada automaticamente às 02:41 no dia 30/05/2021), atuar no sincronismo entre o Componente e o aplicativo e, por fim, funções mais abrangentes e genéricas, como cadastro, autenticação, persistência de dados e controle da sessão do usuário.
