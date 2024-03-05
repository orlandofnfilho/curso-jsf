# JSF + PRIMEFACES TEMPLATE

### Passo a Passo Eclipse

1. **Criar projeto maven**
2. **Configurar Java no Build path e maven**
2. **Adiciona dependências no pom.xml disponível em resources/..**
3. **Adicionar o project facets**
4. **Criar pastas WEB-INF e META-INF**
5. **Adicionar o context.xml disponível em resources/.. na META-INF**
6. **Adicionar o web.xml disponível em resources/.. na META-INF**
````
    2.1 \TABLES\TB_EMAIL.sql
    2.2 \STOREDPROCEDURES\DEL_EMAIL_BY_ID.sql
    2.3 \STOREDPROCEDURES\INS_EMAIL.sql
    2.4 \STOREDPROCEDURES\SEL_EMAIL_DESTINATARIO.sql
    2.5 \STOREDPROCEDURES\SEL_EMAIL_REMETENTE.sql
    2.6 \SCRIPT\INSERT_TB_EMAIL.sql (Opcional)
````

3. **Fazer o deploy do EAR na pasta Build no TIBCO Administrator**

````
TIB_EMAIL_SERVICE.ear
````

4. **Alterar valores das variaveis globais após o deploy**

![global variables](Documentacao/imgs/gv.jpg)

5. **Iniciar a instância criada a partir do deploy no TIBCO Administrator**

![service instance](Documentacao/imgs/service-instance.jpg)