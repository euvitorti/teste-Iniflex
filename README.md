# Teste mão na massa

A aplicação é executada no console e armazena os dados em uma lista, permitindo futura escalabilidade para um banco de dados. Os dados dos funcionários são carregados a partir de um arquivo XML, garantindo que a aplicação seja fácil de atualizar e adaptar para diferentes fontes de dados. Algumas funcionalidades já estão incorporadas em outras, como a listagem de funcionários, que já apresenta os dados em ordem alfabética.

---

## Regras de negócio

1. Classe Pessoa com os atributos: nome (String) e data nascimento (LocalDate).
2. Classe Funcionário que estenda a classe Pessoa, com os atributos: salário (BigDecimal) e função (String).
3. Deve conter uma classe Principal para executar as seguintes ações:
    - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
    – Remover o funcionário “João” da lista.
    – Imprimir todos os funcionários com todas suas informações, sendo que:
      - informação de data deve ser exibido no formato dd/mm/aaaa;
      - informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
    - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
    - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
    - Imprimir os funcionários, agrupados por função.
    - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
    - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
    - Imprimir a lista de funcionários por ordem alfabética.
    - Imprimir o total dos salários dos funcionários.
    - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

---

## Requisitos
- Java 21 ou superior
- Maven

---

## Como Executar

Clone o repositório para o seu ambiente local:

```
  git clone https://github.com/euvitorti/teste-Iniflex.git
```

- Execute a aplicação no seu ambiente de desenvolvimento
---

## Considerações Finais

A aplicação foi projetada para ser simples, mas escalável. A carga de dados dos funcionários a partir de um XML facilita futuras modificações e ampliações, como a integração com um banco de dados real. Fique à vontade para sugerir melhorias ou ajustes conforme necessário.
