# Teste mão na massa

Esta é uma aplicação de console desenvolvida em Java que armazena dados de funcionários em uma lista, permitindo futura escalabilidade para um banco de dados. Os dados são carregados a partir de um arquivo XML, tornando a aplicação flexível para diferentes fontes de dados.

---

## Tecnologias Utilizadas

- Java 21: Linguagem principal do projeto.
- Maven: Gerenciador de dependências e build.
- Jackson (Databind e Core): Para manipulação de dados XML e conversão para objetos Java.
- JUnit 5: Para testes automatizados.

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

## Dependências (Maven)

As dependências utilizadas no projeto estão no pom.xml:
```
    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.15.2</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.11.4</version>
        </dependency>
    </dependencies>
```

---

## Testes Automatizados

Os testes garantem a robustez da aplicação, verificando:

- Carregamento correto do arquivo XML
- Validação de atributos essenciais nos objetos Employee
- Comportamento esperado para arquivos XML inexistentes

---

## Como Executar

Clone o repositório para o seu ambiente local:

```
  git clone https://github.com/euvitorti/teste-Iniflex.git
```

- Abra o projeto na sua IDE, e execute a aplicação no seu ambiente de desenvolvimento
---

## Considerações Finais

A aplicação foi desenvolvida para ser simples e escalável. A utilização de XML para armazenar dados facilita futuras adaptações, como integrações com bancos de dados ou APIs externas.

Caso tenha sugestões de melhorias, fique à vontade para contribuir!


