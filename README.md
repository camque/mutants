Mutants Detector by camque
================================================


Ejecucion mediante SpringBoot
-----------------------------

    mvn spring-boot:run

Compilacion war y ejecucion en linea de comandos
-----------------------------

    mvn clean package
    java -jar target/mutants-0.0.1-SNAPSHOT.jar

Consumo API AWS
---------------
La aplicación expone 2 path: uno de verificación de cadenas de adn y otro para verificar las estadisticas generadas por el primer path

- Verificacion de cadenas de ADN
  - Metodo: POST
  - Path: http://ec2-18-191-38-150.us-east-2.compute.amazonaws.com:8080/mutant
  - Headers: Empty
  - Request: com.github.camque.mutants.dto.MutantRequest
  - Response: com.github.camque.mutants.dto.MutantResponse
  - Response codes:
    - 200 - Ok - Si la ejecucion detecta ADN mutante
    - 400 - Bad Request - En caso de error
    - 403 - Forbbiden - Si la ejecucion detecta ADN humano
  - Ejemplo request:
```json
{
	"dna":[
		"ATGCGA",
		"CAGTGC",
		"TTATGT",
		"AGAAGG",
		"CCCCTA",
		"TCACTG"
	]
}
```
  - Ejemplo response:
```json
{
    "message": "",
    "response": true
}
```  
  
- /stats
  - Metodo: GET
  - Path: http://ec2-18-191-38-150.us-east-2.compute.amazonaws.com:8080/stats
  - Headers: Empty
  - Request: Empty
  - Response: com.github.camque.mutants.dto.StatResponse
  - Response codes:
    - 200 - Ok - Exito de consulta de estadisticas
    - 400 - Bad Request - En caso de error
  - Ejemplo response:
```json
{
    "count_mutant_dna": 5,
    "count_human_dna": 3,
    "ratio": 1.6666666666666667
}
```  

License
-------
GNU General Public License v3
