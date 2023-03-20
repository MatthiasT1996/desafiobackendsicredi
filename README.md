# Desafio Backend Sicredi - Matthias

# Linguagens/Tecnologias #

Java

Swagger para documentação da API

# Links #

[API](https://desafio-sicredi-matthias.herokuapp.com/)

[Documentação API](https://desafio-sicredi-matthias.herokuapp.com/swagger-ui.html#)

[Repositório](https://github.com/MatthiasT1996/desafiobackendsicredi)

# Solução #
## Entidades ##
Pauta - Representa cada Pauta;

Associados - Representa os Associados;

VotacaoPauta - Representa os votos para determinada pauta;

Logs - Representa os logs gerados pela aplicação;

# Funcionalidades #

## Criação de Pauta ##
Ao criar uma nova pauta é informado o nome, se a mesma está aberta, e o tempo para fechamento de pauta, caso o tempo não esteja informado, o padrão é 60 segundos para o fechamento;

## Cadastro de Associado ##
Para cadastro de associado basta informar o CPF e o Nome, caso o CPF seja inválido a aplicação retorna a mensagem informando que o CPF é inválido;
*Como não funcionou o link para validação de CPF, fiz por conta a validação do mesmo*

## Computação de voto para pauta##
Cada associado tem direito a um voto por pauta, basta informar o id do associado, o id da pauta e se aprova ou não a pauta;
*Os votos só são computados se a pauta estiver aberta*

# Mensageria #
Nunca havia trabalhado com mensageria, mas desenvolvi uma função para ao terminar a pauta é lançada uma mensagem informando a pauta fechada, assim como também é mostrado no console e salvo na tabela de logs.

# Versionamento #
Utilizei o git, e caso mais desenvolvedor ajudassem no desenvolvimento, continuaria usando, porém utilizando os princípios de SCRUM, com sprints com determinadas demandas, e sempre sendo criada uma branch por demanda, ao fim da sprint seriam realizados os pull requests e então seria gerada a versão ao final da sprint.
