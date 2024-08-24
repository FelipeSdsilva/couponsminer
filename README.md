# CupomMiner

## Descrição do Projeto.

O projeto consiste em um teste para a empresa HUB desenvolver um sistema de processamento de cupons utilizando Java 17,
Docker, Maven, PostgreSQL, RabbitMQ e Postman.



### Tarefas do projeto:

- [x] Verificar o código do cupom (code44), a data da compra, o valor total, o documento da empresa (CNPJ), o estado e
  os produtos associados.
- [x] Validar o formato do code44 (44 caracteres numéricos).
- [x] Validar o CNPJ.
- [x] Verificar se o valor total do cupom corresponde à soma dos valores dos produtos.
- [ ] Validar o EAN e os preços dos produtos via uma API mock.
- [x] Persistir os dados válido e salvar os dados do cupom no banco de dados PostgreSQL.
- [ ] Criar filas no RabbitMQ.
- [ ] Publicar no serviço de pub/sub e enviar as informações do cupom válido para um serviço de pub/sub usando RabbitMQ.
- [ ] Criar um listener: Receber dados do comprador via pub/sub e atualizar o banco de dados com essas informações.
- [x] Implementação do Swagger e Bibliotecas de Validação
  Para melhorar o projeto.
- [x] Criar Dockerfile do projeto.
- [ ] Escrever testes unitários.


## Exemplo

```json
Sera enviado um json com esses dados abaixo.

{
  "code44": "456454165161561561165165151616515616515",
  "purchaseDate": "2024-02-01",
  "totalValue": 42.5,
  "companyDocument": 68733752000103,
  "State": "SP",
  "products": [
    {
      "name": "Sabão Líquido OMO Lavagem Perfeita 500ml",
      "ean": 4565156131,
      "unitaryPrice": 10.20,
      "quantity": 5
    },
    {
      "name": "CIF Multiuso Cremoso 450ml",
      "ean": 4565156131,
      "unitaryPrice": 8.20,
      "quantity": 5
    }
  ]
}

Caso o campo 'code44' seja enviado com valor inferior ou vázio (null)
será retornado uma resposta do servidor com o seguinte corpo (body).

{
  "timestamp": "2024-08-24T14:14:08.788153300Z",
  "status": 422,
  "error": "Validation exception",
  "message": "Validation failed for argument [0] in public org.springframework.http.ResponseEntity<com.felipesousa.cupomminer.dto.CouponMinDTO> com.felipesousa.cupomminer.controllers.CouponController.postNewCoupon(com.felipesousa.cupomminer.dto.CouponDTO) with 2 errors: [Field error in object 'couponDTO' on field 'code44': rejected value [4564541651615615611651651516165156]; codes [Pattern.couponDTO.code44,Pattern.code44,Pattern.java.lang.String,Pattern]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [couponDTO.code44,code44]; arguments []; default message [code44],[Ljakarta.validation.constraints.Pattern$Flag;@1710648c,\\d{44}]; default message [code44 must contain exactly 44 digits]] [Field error in object 'couponDTO' on field 'companyDocument': rejected value [54616153156]; codes [ValidDoc.couponDTO.companyDocument,ValidDoc.companyDocument,ValidDoc.java.lang.Long,ValidDoc]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [couponDTO.companyDocument,companyDocument]; arguments []; default message [companyDocument]]; default message [Document invalid!]] ",
  "path": "/coupons",
  "errors": [
    {
      "fieldName": "code44",
      "message": "code44 must contain exactly 44 digits"
    },
    {
      "fieldName": "code44",
      "message": "not have blank this value!"
    }
  ]
}
```

# Documentação do funcionamento da API.

```
Quando for executado o comando docker compose up -d ele rodara 
automaticamente para isso é necessario que tenha 'Docker' instalado.

Caso não tenha é só seguir esses passos do video abaixo.


```


