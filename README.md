# gestao-auto-api

1-Use o wildfly 11

2-Na pasta modules, efetue deploy do jar do driver mysql
    /modules/com/mysql/main  (jar e module.xml)

3-Configure no standalone.xml o datasource apontando para o mysql da sua máquina.

4-Na pasta standalone/deployments insira o war do projeto.

5-Inicie o standalone.bat
