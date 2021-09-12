1 - Introdução e Definição da Arquitetura

Partindo do princípio da ideia de janela inteligente integrada com um aplicativo mobile, fica evidente a necessidade de um arquitetura que contenha uma comunicação entre os sistemas heterogêneos que abrangem este projeto. Para tanto, usaremos a plataforma Node-red para atuar nesta parte de interligação dos sistemas (Arduino, Mobile, Banco de Dados) juntamente com a arquitetura REST por oferecer uma maior flexibilidade em relação ao SOAP no que diz respeito a regras e padrões de desenvolvimento.
Além disso, vale ressaltar que o REST tem como principal característica o statelessness, ou seja, o estado da aplicação não persiste entre sucessivos resquests e responses, consequentemente temos uma maior facilidade para chamar a API que irá trazer informações do clima para o aplicativo.  

Serviços
Dentre os serviços que serão implementados no aplicativo, vale destacar as três funcionalidades fundamentais que são o diferencial deste projeto, sendo elas: Fechamento da janela automaticamente por detecção de umidade, Fechamento da janela automaticamente por meio de um horário pré-definido pelo usuário e, por fim, Verificação dos logs gerados pelo aplicativo de um determinado componente.

Fechamento automático por detecção de umidade: 
Path: fenestra/fecharPorSensor
Método: POST
Quando o sensor capta uma uma chuva repentina ou até mesmo umidade do ambiente, haverá a comunicação direta entre os componentes IoT e o aplicativo, este que por sua vez acionará o fechamento da janela através de um servo motor.

Fechamento automático por um horário pré-definido: 
Path: fenestra/fecharPorHorario
Método: POST
Quanto ao fechamento automático por meio de um horário pré-definido, o usuário poderá estabelecer horários em diferentes períodos do dia e deixá-los programados no aplicativo para que o aplicativo envie a informação para o componente fechar a janela no momento programado.


Verificação de Logs do Componente: 
Path: fenestra/verficarLog
Método: GET

Na verificação de logs, o usuário poderá verificar todos os relatos que o sistema irá registrar de todos os comandos efetuados naquele determinado período de pesquisa determinado no aplicativo. Os Logs conterão informações como data e hora do evento, motivo e o componente específico que realizou aquela ação. Por exemplo, se o comando fechar ou abrir a janela ocorrer por uma ação do usuário, ficará registrado aquela ação no sistema.  O mesmo acontecerá caso algum acionamento seja feito por detecção de umidade, por estar programado no aplicativo pelo usuário ou até mesmo caso ocorra do sistema ser desligado caso ocorra alguma falha ou corte de energia no local. Esses Logs ficarão gravados e disponibilizados no aplicativo para consulta de histórico por parte do usuário.

1.2 - Imagem da arquiteruta :https://github.com/NextStepFIAP/Fenestra-API/blob/d3013efc7be08534b223aaf26543da4a680dc3dd/Fenestra/Arquitetura.png


2 - IMPLEMENTAÇÃO DO BACK-END
Para o Back-End da nossa solução, iremos utilizar como linguagem de desenvolvimento o React-Native pois além de ter uma fácil comunicação com o Front-End de sistemas mobile podemos, por meio dele, disponibilizar nossa aplicação para diversas plataformas. No que diz respeito ao Banco de Dados, será utilizado o MySQL por ser uma opção gratuita e oferecer um excelente serviço na persistência de dados. Por fim, para integrar e permitir a comunicação entre todos os nossos sistemas e componentes (Arduino, Aplicativo e Banco de Dados) iremos utilizar o Node-Red como nosso servidor intermediário.


4 - Tabela de Endpoints
[/user] - Endpoint de transações relacionadas ao usuário ()
[/componente] - Endpoint de transações relacionadas ao componente ()
[/log] - Endpoint de transações relacionadas ao log ()


3 - PRINCIPAIS FUNCIONALIDADES DO BACK-END
Dentre as principais funções que o back-end será responsável, podemos citar as que são acionadas pela aplicação Mobile, estas que abordam, efetivamente, as ações que o sistema executará no componente mecânico do Arduino, como por exemplo: Definir um horário para fechamento da(s) janela(s), fechar a(s) janela(s) automaticamente ao iniciar uma chuva e/ou por meio deste horário, exibir e gerar logs das ações do Arduino para o usuário (Ex: A janela foi fechada automaticamente às 02:41 no dia 30/05/2021), atuar no sincronismo entre o Componente e o aplicativo e, por fim, funções mais abrangentes e genéricas, como cadastro, autenticação, persistência de dados e controle da sessão do usuário.
