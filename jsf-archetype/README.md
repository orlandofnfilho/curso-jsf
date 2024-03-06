# JSF + PRIMEFACES TEMPLATE

### Passo a Passo

1. **Criar projeto maven**
2. **Configurar Java no Build path e maven**
3. **Adicionar dependências no pom.xml disponível em resources/..**
4. **Adicionar o project facets**
5. **Criar pastas WEB-INF e META-INF**
6. **Adicionar o context.xml disponível em resources/.. na src/webapp/META-INF**
7. **Adicionar o web.xml disponível em resources/.. na src/webapp/WEB-INF**
7. **Adicionar o faces-config.xml disponível em resources/.. na src/webapp/WEB-INF**
8. **Adicionar o beans.xml disponível em resources/.. na src/resources/META-INF**
9. **Adicionar o persistence.xml em resources/.. na src/resources/META-INF**
10. **Adicionar o javax.enterprise.inject.spi.Extension disponível em resources/.. na src/resources/META-INF/services**
11. **Adicionar o ValidationMessages.properties disponível em resources/.. na src/resources/..**
12. **Criar um pacote em resources para adicionar o arquivo Messages.properties**
13. **Adicionar o arquivo Messages.properties disponível em resources/.. na src/resources/{package}..**
14. **Adicionar o arquivo log4j.properties disponível em resources/.. na src/resources/..**
15. **Criar o package util.cdi e adicionar as classes disponíveis em resources/util/cdi..**
16. **Criar o package util.jsf e adicionar as classes disponíveis em resources/util/jsf..**
17. **Criar o package util.jpa e adicionar as classes disponíveis em resources/util/jpa..**
18. **Criar a Classe AppContextListener.java disponível em resrouces/util..**

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